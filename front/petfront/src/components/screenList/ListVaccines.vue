<template>
    <RegisterVaccines ref="modalVaccine" @reloadVaccines="loadVaccines" />
    <div class="d-flex justify-content-between">
        <h2>Vacinas</h2>
        <div class="d-flex align-items-center">
            <button type="button" class="btn btn-m btn-primary" @click="addVaccine">
                Adicionar vacina
            </button>
        </div>
    </div>

    <div class="d-flex justify-content-between mt-3">
       <div class="position-relative">
           <input type="text" v-model="filter.name" placeholder="Buscar" class="form-control input-with-icon" />
       </div>
    </div>

    <table class="table mt-3">
        <thead>
            <tr>
                <th>Vacina</th>
                <th>Preço</th>
                <th width="96">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="vaccine in filteredVaccines" :key="vaccine.id">
                <td>{{ vaccine.name }}</td>
                <td>{{ formatCurrency(vaccine.price) }}</td>
                <td class="btn-group text-end">
                    <button class="btn btn-icon btn-sm btn-primary me-1" @click="updateVaccine(vaccine.id)">
                        <span class="material-symbols-rounded">edit</span>
                    </button>
                    <button class="btn btn-icon btn-sm btn-danger" @click="confirmDelete(vaccine.id)">
                        <span class="material-symbols-rounded">delete</span>
                    </button>
                </td>
            </tr>
        </tbody>
    </table>

    <div class="modal fade" id="deleteVaccineModal" tabindex="-1" aria-labelledby="deleteVaccineModalLabel"
        aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteVaccineModalLabel">Confirmar Exclusão</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"
                        @click="closeDeleteModal"></button>
                </div>
                <div class="modal-body">
                    Tem certeza que deseja excluir esta vacina?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" @click="closeDeleteModal">Cancelar</button>
                    <button type="button" class="btn btn-danger" @click="deleteVaccine">Confirmar</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="errorDeleteVaccine" tabindex="-1" aria-labelledby="deleteVaccineModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="deleteVaccineModalLabel">Erro</h5>
                    <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Vacina não pode ser excluída.
                </div>
            </div>
        </div>
    </div>

</template>

<script>
import axios from 'axios';
import RegisterVaccines from '../screensRegister/RegisterVaccines.vue';
import * as bootstrap from 'bootstrap';

export default {
    components: {
        RegisterVaccines
    },
    data() {
        return {
            vaccines: [],
            filter: {
                name: ''
            },
            editedVaccine: {
                id: null,
                name: '',
                price: ''
            }
        };
    },
    created() {
        this.loadVaccines();
    },
    methods: {
        formatCurrency(value) {
            if (!value) return '';
            return parseFloat(value).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
        },
        loadVaccines() {
            axios({
                method: "GET",
                url: "http://localhost:8080/api/v1/vaccines",
            })
                .then((response) => {
                    this.vaccines = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar as vacinas:', error);
                });
        },
        addVaccine() {
            this.vaccineId = null;
            this.openModal();
        },
        updateVaccine(vaccineId) {
            this.vaccineId = vaccineId;
            this.openModal(vaccineId);
        },
        openModal(vaccineId) {
            let modal = bootstrap.Modal.getOrCreateInstance(document.getElementById("modalVaccine"))

            if (vaccineId) {
                this.$refs.modalVaccine.loadVaccine(vaccineId);
                this.$refs.modalVaccine.vaccineId = vaccineId;
            }

            modal.show();
        },
        confirmDelete(vaccineId) {
            this.selectedVaccine = vaccineId;
            $('#deleteVaccineModal').modal('show');
        },
        deleteVaccine() {
            let vaccineId = this.selectedVaccine;

            axios.delete(`http://localhost:8080/api/v1/vaccines/${vaccineId}`)
                .then(response => {
                    console.log('Vacina excluída com sucesso:', response.data);
                    this.closeDeleteModal();
                    this.loadVaccines();
                })
                .catch(error => {
                    $('#errorDeleteVaccine').modal('show');
                    $('#deleteVaccineModal').modal('hide');
                    setTimeout(function(){
                $('#errorDeleteVaccine').modal('hide');
            }, 3000);
                });
        },
        closeDeleteModal() {
            $('#deleteVaccineModal').modal('hide');
        }
    },
    computed: {
        filteredVaccines() {
            return this.vaccines.filter(vaccine => {
                const matchesName = vaccine.name.toLowerCase().includes(this.filter.name.toLowerCase());
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
