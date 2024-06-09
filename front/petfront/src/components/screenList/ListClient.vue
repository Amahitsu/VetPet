<template>

    <div class="d-flex justify-content-between">
        <h2>Clientes</h2>
        <div class="d-flex align-items-center">
            <router-link to="/cliente/cadastro" class="btn btn-primary btn-sm">Adicionar cliente</router-link>
        </div>
    </div>

    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Status</th>
                <th width="200">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="customer in customers" :key="customer.id">
                <td>{{ customer.id }}</td>
                <td>{{ customer.name }}</td>
                <td>{{ customer.phone }}</td>
                <td>{{ customer.active ? "Ativo" : "Inativo" }}</td>
                <td>
                    <button class="btn btn-sm btn-primary" @click="addPet(customer.id)">Add pet</button>
                    <button class="btn btn-sm btn-primary" @click="editCustomer(customer.id)">Editar</button>
                    <button class="btn btn-sm btn-danger" @click="confirmDelete(customer.id)">Deletar</button>
                </td>
            </tr>
        </tbody>
    </table>

    <div class="modal fade" id="deleteCustomerModal" tabindex="-1" role="dialog" aria-labelledby="deleteCustomerModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalLabel">Confirmar Exclusão</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"
                        @click="closeDeleteModal"></button>
                </div>
                <div class="modal-body">
                    Tem certeza que deseja excluir este cliente?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" @click="closeDeleteModal">Cancelar</button>
                    <button type="button" class="btn btn-danger" @click="deleteCustomer">Confirmar</button>
                </div>
            </div>
        </div>
    </div>

</template>


<script>
import axios from 'axios';

export default {
    data() {
        return {
            customers: [],
            customerIdToDelete: null,
        };
    },
    created() {
        this.loadCustomers();
    },
    methods: {
        loadCustomers() {
            axios({
                method: "GET",
                url: "http://localhost:8080/api/v1/customers",
            })
                .then((response) => {
                    console.log(response.data.data)
                    this.customers = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar o cliente:', error);
                });
        },
        addPet(customerId) {
            this.$router.push({ path: `/cliente/${customerId}/adicionar-pet` });
        },
        editCustomer(customerId) {
            this.$router.push({ path: `/cliente/${customerId}` });
        },
        confirmDelete(customerId) {
            this.customerIdToDelete = customerId;
            $('#deleteCustomerModal').modal('show');
        },
        deleteCustomer() {
            let customerId = this.customerIdToDelete;
            axios.delete(`http://localhost:8080/api/v1/customers/${customerId}`)
                .then(response => {
                    console.log('Cliente excluído com sucesso:', response.data);
                    this.closeDeleteModal();
                    this.loadCustomers();
                })
                .catch(error => {
                    console.error('Erro ao excluir cliente:', error);
                });
        },
        closeDeleteModal() {
            $('#deleteCustomerModal').modal('hide');
        }
    }
}
</script>

<style></style>