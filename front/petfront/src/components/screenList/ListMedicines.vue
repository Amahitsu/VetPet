<template>
    <RegisterMedicines ref="modalMedicine" @reloadMedicines="loadMedicines" />
    <div class="d-flex justify-content-between">
        <h2>Medicamentos</h2>
        <div class="d-flex align-items-center">
            <button type="button" class="btn btn-m btn-primary" @click="addMedicine">
                Adicionar medicamento
            </button>
        </div>
    </div>
    
    <div class="d-flex justify-content-between mt-3">
       <div class="position-relative">
           <input type="text" v-model="filter.name" placeholder="Filtrar por vacina" class="form-control input-with-icon" />
       </div>
    </div>

    <table class="table mt-3">
        <thead>
            <tr>
                <th>Medicamento</th>
                <th>Valor</th>
                <th width="96">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="medicine in filteredMedicines" :key="medicine.id">
                <td>{{ medicine.name }}</td>
                <td>{{ formatCurrency(medicine.price) }}</td>
                <td class="btn-group text-end">
                    <button class="btn btn-icon btn-sm btn-primary me-1" @click="updateMedicine(medicine.id)">
                        <span class="material-symbols-rounded">edit</span>
                    </button>
                    <button class="btn btn-icon btn-sm btn-danger" @click="confirmDelete(medicine.id)">
                        <span class="material-symbols-rounded">delete</span>
                    </button>
                </td>
            </tr>
        </tbody>
    </table>

    <div class="modal fade" id="deleteMedicineModal" tabindex="-1" aria-labelledby="deleteMedicineModalLabel"
        aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteMedicineModalLabel">Confirmar Exclusão</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"
                        @click="closeDeleteModal"></button>
                </div>
                <div class="modal-body">
                    Tem certeza que deseja excluir este medicamento?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" @click="closeDeleteModal">Cancelar</button>
                    <button type="button" class="btn btn-danger" @click="deleteMedicine">Confirmar</button>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
import axios from 'axios';
import RegisterMedicines from '../screensRegister/RegisterMedicines.vue';
import * as bootstrap from 'bootstrap';

export default {
    components: {
        RegisterMedicines
    },
    data() {
        return {
            medicines: [],
            filter: {
                name: ''
            },
        };
    },
    created() {
        this.loadMedicines();
    },
    methods: {
        formatCurrency(value) {
            if (!value) return '';
            return parseFloat(value).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
        },
        loadMedicines() {
            axios({
                method: "GET",
                url: "http://localhost:8080/api/v1/medicines",
            })
                .then((response) => {
                    this.medicines = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar os medicamentos:', error);
                });
        },
        addMedicine() {
            this.openModal();
        },
        updateMedicine(medicineId) {
            this.openModal(medicineId);
        },
        openModal(medicineId) {
            let modal = bootstrap.Modal.getOrCreateInstance(document.getElementById("modalMedicine"))

            if (medicineId) {
                this.$refs.modalMedicine.loadMedicine(medicineId);
                this.$refs.modalMedicine.medicineId = medicineId;
            }

            modal.show();
        },
        confirmDelete(medicineId) {
            this.selectedMedicine = medicineId;
            $('#deleteMedicineModal').modal('show');
        },
        deleteMedicine() {
            let id = this.selectedMedicine;

            axios.delete(`http://localhost:8080/api/v1/medicines/${id}`)
                .then(response => {
                    console.log('Medicamento excluído com sucesso:', response.data);
                    this.closeDeleteModal();
                    this.loadMedicines();
                })
                .catch(error => {
                    console.error('Erro ao excluir medicamento:', error);
                });
        },
        closeDeleteModal() {
            $('#deleteMedicineModal').modal('hide');
        }
    },
    computed: {
        filteredMedicines() {
            return this.medicines.filter(medicine => {
                const matchesName = medicine.name.toLowerCase().includes(this.filter.name.toLowerCase());
                return matchesName;
            });
        }
    }
}
</script>

<style>
.input-with-icon {
    padding-left: 30px; 
    background: url('@/assets/lupa.png') no-repeat 8px center;
    background-size: 16px 16px;
}
</style>