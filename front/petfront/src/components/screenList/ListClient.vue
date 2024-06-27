<template>

    <div class="d-flex justify-content-between">
        <h2>Clientes</h2>
        <div class="d-flex align-items-center">
            <router-link to="/cliente/cadastro" class="btn btn-primary btn-m">Adicionar cliente</router-link>
        </div>
    </div>

    <div class="d-flex justify-content-between mt-3">
       <div class="position-relative">
           <input type="text" v-model="filter.name" placeholder="Filtrar por raça ou espécie" class="form-control input-with-icon" />
       </div>
    </div>

    <table class="table mt-3">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Status</th>
                <th width="157">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="customer in filteredCustomers" :key="customer.id">
                <td>{{ customer.name }}</td>
                <td>{{ formatPhoneNumber(customer.phone) }}</td>
                <td>{{ customer.active ? "Ativo" : "Inativo" }}</td>
                <td class="btn-group text-end">
                    <button class="btn btn-icon btn-sm btn-success me-1" @click="addPet(customer.id)">
                        <span class="material-symbols-rounded">add_circle</span>
                        <span class="material-symbols-rounded">pets</span>
                    </button>
                    <button class="btn btn-icon btn-sm btn-primary me-1" @click="editCustomer(customer.id)">
                        <span class="material-symbols-rounded">edit</span>
                    </button>
                    <button class="btn btn-icon btn-sm btn-danger" @click="confirmDelete(customer.id)">
                        <span class="material-symbols-rounded">delete</span>
                    </button>
                </td>
            </tr>
        </tbody>
    </table>

    <div class="modal fade" id="deleteCustomerModal" tabindex="-1" role="dialog"
        aria-labelledby="deleteCustomerModalLabel" aria-hidden="true">
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

    <div class="modal fade" id="errorDeleteCustomer" tabindex="-1" aria-labelledby="errorDeleteModalLabel"
        aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="errorDeleteModalLabel">Erro</h5>
                    <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Cliente não pode ser excluído pois está associado a agendamentos.
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
            filter: {
                name: ''
            },
        };
    },
    created() {
        this.loadCustomers();
    },
    methods: {
        formatPhoneNumber(phoneNumber) {
            phoneNumber = phoneNumber.replace(/\D/g, '');
            if (phoneNumber.length === 9) {
                return phoneNumber.replace(/(\d{5})(\d{4})/, '$1-$2');
            } else if (phoneNumber.length === 11) {
                return phoneNumber.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
            } else {
                return phoneNumber;
            }
        },
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
                    $('#deleteCustomerModal').modal('hide');
                    $('#errorDeleteCustomer').modal('show');
                    setTimeout(function () {
                        $('#errorDeleteCustomer').modal('hide');
                    }, 3000);
                });
        },
        closeDeleteModal() {
            $('#deleteCustomerModal').modal('hide');
        }
    },
    computed: {
        filteredCustomers() {
            return this.customers.filter(customer => {
               const filterText = this.filter.name.toLowerCase();
               const matchesName = customer.name.toLowerCase().includes(filterText);
               //Retira os caracteres não numéricos do telefone
               const sanitizedPhone = customer.phone.replace(/\D/g, '');
               const matchesPhone = sanitizedPhone.includes(filterText);
               //Responde somente true e false
               const matchesActive = customer.active.toString().toLowerCase().includes(filterText);
               
               return matchesName || matchesPhone || matchesActive;
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