<template>
    <RegisterServiceType ref="modalService" @reloadServices="loadServices" />
    <div class="d-flex justify-content-between">
        <h2>Serviços</h2>
        <div class="d-flex align-items-center">
            <button type="button" class="btn btn-m btn-primary" data-bs-toggle="modal" data-bs-target="#modalService">
                Adicionar serviço
            </button>
        </div>
    </div>


    <table class="table mt-3">
        <thead>
            <tr>
                <th>Serviço</th>
                <th>Valor</th>
                <th width="96">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="service in services" :key="service.id">
                <td>{{ service.name }}</td>
                <td>{{ formatCurrency(service.price) }}</td>
                <td class="btn-group text-end">
                    <button class="btn btn-icon btn-sm btn-primary me-1" @click="updateService(service.id)">
                        <span class="material-symbols-rounded">edit</span>
                    </button>
                    <button class="btn btn-icon btn-sm btn-danger" @click="confirmDelete(service.id)">
                        <span class="material-symbols-rounded">delete</span>
                    </button>
                </td>
            </tr>
        </tbody>
    </table>

    <div class="modal fade" id="deleteServiceModal" tabindex="-1" aria-labelledby="deleteServiceModalLabel"
        aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteServiceModalLabel">Confirmar Exclusão</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"
                        @click="closeDeleteModal"></button>
                </div>
                <div class="modal-body">
                    Tem certeza que deseja excluir este serviço?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" @click="closeDeleteModal">Cancelar</button>
                    <button type="button" class="btn btn-danger" @click="deleteService">Confirmar</button>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
import axios from 'axios';
import RegisterServiceType from '../screensRegister/RegisterServiceType.vue';

export default {
    components: {
        RegisterServiceType
    },
    data() {
        return {
            services: []
        };
    },
    created() {
        this.loadServices();
    },
    methods: {
        formatCurrency(value) {
            if (!value) return '';
            return parseFloat(value).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
        },
        loadServices() {
            axios({
                method: "GET",
                url: "http://localhost:8080/api/v1/services",
            })
                .then((response) => {
                    this.services = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar os serviços:', error);
                });
        },
        addService() {
            this.openModal();
        },
        updateService(serviceId) {
            this.openModal(serviceId);
        },
        openModal(serviceId) {
            let modal = bootstrap.Modal.getOrCreateInstance(document.getElementById("modalService"))

            if (serviceId) {
                this.$refs.modalService.loadService(serviceId);
                this.$refs.modalService.serviceId = serviceId;
            }

            modal.show();
        },
        confirmDelete(serviceId) {
            this.selectedService = serviceId;
            $('#deleteServiceModal').modal('show');
        },
        deleteService() {
            let id = this.selectedService;

            axios.delete(`http://localhost:8080/api/v1/services/${id}`)
                .then(response => {
                    console.log('Serviço excluído com sucesso:', response.data);
                    this.closeDeleteModal();
                    this.loadServices();
                })
                .catch(error => {
                    console.error('Erro ao excluir serviço:', error);
                });
        },
        closeDeleteModal() {
            $('#deleteServiceModal').modal('hide');
        }
    },
}
</script>

<style></style>