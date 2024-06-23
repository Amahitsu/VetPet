<template>
    <div class="modal fade" id="modalVaccine" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" v-if="!vaccineId">Cadastro de Vacinas</h1>
                    <h1 class="modal-title fs-5" v-if="vaccineId">Editar Vacinas</h1>
                    <button type="button" class="btn-close" aria-label="Close" @click="closeModal"></button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-8">
                            <label for="inputName" class="form-label">Vacina</label>
                            <input type="text" class="form-control" v-model="vaccineName">
                        </div>
                        <div class="col-md-4">
                            <label for="inputPrice" class="form-label">Preço</label>
                            <input type="text" class="form-control" v-model="vaccinePrice">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" @click="closeModal">Cancelar</button>
                    <button type="button" class="btn btn-primary me-2" @click="saveVaccine">Salvar</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import * as bootstrap from 'bootstrap';

export default {
    data() {
        return {
            vaccineId: null,
            vaccineName: '',
            vaccinePrice: '',
        };
    },
    methods: {
        loadVaccine(vaccineId) {
            if (vaccineId == null) {
                this.reset();
                return;
            }

            axios.get(`http://localhost:8080/api/v1/vaccines/${vaccineId}`)
                .then(response => {
                    let data = response.data.data;

                    this.vaccineName = data.name;
                    this.vaccinePrice = data.price;
                })
                .catch(error => {
                    console.error('Erro ao buscar vacinas:', error);
                });
        },
        saveVaccine() {
            if (this.vaccineId)
                this.editVaccine(this.vaccineId)
            else
                this.addVaccine()
        },
        addVaccine() {
            axios.post("http://localhost:8080/api/v1/vaccines", {
                name: this.vaccineName,
                price: this.vaccinePrice
            })
                .then(response => {
                    this.closeModal();
                    this.$emit('reloadVaccines');
                    console.log('Vacina criada com sucesso:', response.data);
                })
                .catch(error => {
                    console.error('Erro ao criar vacina:', error);
                });
        },
        editVaccine(vaccineId) {
            axios.put(`http://localhost:8080/api/v1/vaccines/${vaccineId}`, {
                id: vaccineId,
                name: this.vaccineName,
                price: this.vaccinePrice
            })
                .then(response => {
                    this.closeModal();
                    this.$emit('reloadVaccines');
                    console.log('Vacina editada com sucesso:', response.data);
                })
                .catch(error => {
                    console.error('Erro ao editar vacina:', error);
                });
        },
        closeModal() {
            let modal = bootstrap.Modal.getInstance(document.getElementById("modalVaccine"))
            modal.hide();
            this.reset();
        },
        reset() {
            this.vaccineId = null;
            this.vaccineName = '';
            this.vaccinePrice = '';
        }
    }
}
</script>

<style></style>