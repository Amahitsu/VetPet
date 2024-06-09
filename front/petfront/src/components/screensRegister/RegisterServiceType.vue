<template>
    <div class="modal fade" id="modalService" tabindex="-1" aria-labelledby="modalService" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="modalService" v-if="!serviceId">Cadastro de Serviço</h1>
                    <h1 class="modal-title fs-5" id="modalService" v-if="serviceId">Editar Serviço</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-8">
                            <label for="serviceName">Nome do serviço</label>
                            <input type="text" class="form-control" id="serviceName" v-model="serviceName">
                        </div>

                        <div class="col-md-4">
                            <label for="servicePrice">Valor do serviço</label>
                            <input type="text" class="form-control" id="servicePrice" v-model="servicePrice">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-bs-dismiss="modal" class="btn btn-secondary" @click="closeModal">Cancelar</button>
                    <button type="button" class="btn btn-primary me-2" @click="saveService">Salvar</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    emits: ['reloadServices'],
    data() {
        return {
            serviceId: null,
            serviceName: '',
            servicePrice: '',
            selectedServiceId: ''
        };
    },
    methods: {
        loadService(serviceId) {
            axios.get(`http://localhost:8080/api/v1/services/${serviceId}`)
                .then(response => {
                    let data = response.data.data;
                    
                    this.serviceName = data.name;
                    this.servicePrice = data.price;
                })
                .catch(error => {
                    console.error('Erro ao listar os serviços:', error);
                });
        },
        saveService() {
            if (this.serviceId) {
                this.editService(this.serviceId);
            } else {
                this.addService();
            }
        },
        addService() {
            axios.post("http://localhost:8080/api/v1/services", {
                name: this.serviceName,
                price: this.servicePrice
            })
                .then(response => {
                    this.closeModal();
                    this.$emit('reloadServices');
                    console.log('Serviço criado com sucesso:', response.data);
                })
                .catch(error => {
                    console.error('Erro ao criar serviço:', error);
                });
        },
        editService(serviceId) {
            axios.put(`http://localhost:8080/api/v1/services/${serviceId}`, {
                id: serviceId,
                name: this.serviceName,
                price: this.servicePrice
            })
                .then(response => {
                    this.closeModal();
                    this.$emit('reloadServices');
                    console.log('Serviço editado com sucesso:', response.data);
                })
                .catch(error => {
                    console.error('Erro ao editar serviço:', error);
                });
        },
        closeModal() {
            let modal = bootstrap.Modal.getInstance(document.getElementById("modalService"));
            modal.hide();
            this.reset();
        },
        reset() {
            this.serviceId = null;
            this.serviceName = '';
            this.servicePrice = ''; 
        }
    }
}
</script>
