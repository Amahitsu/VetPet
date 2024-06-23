<template>
    <div class="modal fade" id="modalMedicine" tabindex="-1" aria-labelledby="modalMedicine" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="modalMedicine" v-if="!medicineId">Cadastro de Medicamento</h1>
                    <h1 class="modal-title fs-5" id="modalMedicine" v-if="medicineId">Editar Medicamento</h1>
                    <button type="button" class="btn-close" aria-label="Close" @click="closeModal"></button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-8">
                            <label for="medicineName">Medicamento</label>
                            <input type="text" class="form-control" id="medicineName" v-model="medicineName">
                        </div>

                        <div class="col-md-4">
                            <label for="medicinePrice">Valor</label>
                            <input type="text" class="form-control" id="medicinePrice" v-model="medicinePrice">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" @click="closeModal">Cancelar</button>
                    <button type="button" class="btn btn-primary me-2" @click="saveMedicine">Salvar</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import * as bootstrap from 'bootstrap';

export default {
    emits: ['reloadMedicines'],
    data() {
        return {
            medicineId: null,
            medicineName: '',
            medicinePrice: '',
            selectedMedicineId: ''
        };
    },
    methods: {
        loadMedicine(medicineId) {
            axios.get(`http://localhost:8080/api/v1/medicines/${medicineId}`)
                .then(response => {
                    let data = response.data.data;
                    
                    this.medicineName = data.name;
                    this.medicinePrice = data.price;
                })
                .catch(error => {
                    console.error('Erro ao listar os medicamentos:', error);
                });
        },
        saveMedicine() {
            if (this.medicineId) {
                this.editMedicine(this.medicineId);
            } else {
                this.addMedicine();
            }
        },
        addMedicine() {
            axios.post("http://localhost:8080/api/v1/medicines", {
                name: this.medicineName,
                price: this.medicinePrice
            })
                .then(response => {
                    this.closeModal();
                    this.$emit('reloadMedicines');
                    console.log('Medicamento criado com sucesso:', response.data);
                })
                .catch(error => {
                    console.error('Erro ao criar medicamento:', error);
                });
        },
        editMedicine(medicineId) {
            axios.put(`http://localhost:8080/api/v1/medicines/${medicineId}`, {
                id: medicineId,
                name: this.medicineName,
                price: this.medicinePrice
            })
                .then(response => {
                    this.closeModal();
                    this.$emit('reloadMedicines');
                    console.log('Medicamento editado com sucesso:', response.data);
                })
                .catch(error => {
                    console.error('Erro ao editar medicamento:', error);
                });
        },
        closeModal() {
            let modal = bootstrap.Modal.getInstance(document.getElementById("modalMedicine"));
            modal.hide();
            this.reset();
        },
        reset() {
            this.medicineId = null;
            this.medicineName = '';
            this.medicinePrice = '';
        }
    }
}
</script>
