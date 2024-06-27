<template>
    <div class="card">
        <div class="card-body">
            <div class="d-flex justify-content-between">
                <h4>Pets do Cliente: {{ customer.name }}</h4>
                <div class="d-flex align-items-center">
                    <button type="button" class="btn btn-icon btn-success btn-m" @click="goToAddPet">
                        <span class="material-symbols-rounded">add_circle</span>
                        <span class="material-symbols-rounded">pets</span>
                    </button>
                </div>
            </div>

            <div v-if="loading">
                Carregando...
            </div>
            <div v-else>
                <table class="table my-4" v-if="customerAnimals.length > 0">
                    <thead>
                        <tr>
                            <th>Nome pet</th>
                            <th>Espécie</th>
                            <th>Raça</th>
                            <th width="96">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="animal in customerAnimals" :key="animal.id">
                            <td>{{ animal.name }}</td>
                            <td>{{ animal.race.specie.name }}</td>
                            <td>{{ animal.race.name }}</td>
                            <td class="btn-group text-end">
                                <button type="button" class="btn btn-icon btn-sm btn-primary me-1"
                                    @click="updatePetClient(animal.id)">
                                    <span class="material-symbols-rounded">edit</span>
                                </button>
                                <button type="button" class="btn btn-icon btn-sm btn-danger" @click="confirmDelete(animal.id)">
                                    <span class="material-symbols-rounded">delete</span>
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div v-else>
                    Não há animais cadastrados para este cliente.
                </div>
            </div>

            <div class="modal fade" id="deleteAnimalModal" tabindex="-1" role="dialog"
                aria-labelledby="deleteAnimalModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteModalLabel">Confirmar Exclusão</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"
                                @click="closeDeleteModal"></button>
                        </div>
                        <div class="modal-body">
                            Tem certeza que deseja excluir este pet?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" @click="closeDeleteModal">Cancelar</button>
                            <button type="button" class="btn btn-danger" @click="deleteAnimal">Confirmar</button>
                        </div>
                    </div>
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
            customerId: null,
            customer: {},
            customerAnimals: [],
            loading: true,
        };
    },
    created() {
        this.customerId = this.$route.params.customerId;
        this.loadCustomerById(this.customerId);
        this.loadCustomerAnimals(this.customerId);
    },
    methods: {
        goToAddPet() {
            this.$router.push({ path: `/cliente/${this.customerId}/adicionar-pet` });
        },
        loadCustomerById(id) {
            axios.get(`http://localhost:8080/api/v1/customers/${id}`)
                .then((response) => {
                    this.customer = response.data.data;
                })
                .catch(error => {
                    this.loading = false;
                    if (error.response && error.response.status === 404) {
                        alert('Cliente não encontrado');
                    }
                    console.error('Erro ao carregar cliente:', error);
                });
        },
        loadCustomerAnimals(customerId) {
            axios.get(`http://localhost:8080/api/v1/animals?customerId=${customerId}`)
                .then((response) => {
                    ;
                    this.customerAnimals = response.data.data;
                    this.loading = false;
                })
                .catch(error => {
                    this.loading = false;
                    console.error('Erro ao carregar animais do cliente:', error);
                });
        },
        updatePetClient(animalId) {
            this.$router.push({ path: `/cliente/${animalId}/editar-pet` });
        },
        confirmDelete(animalId) {
            this.animalIdToDelete = animalId;
            $('#deleteAnimalModal').modal('show');
        },
        deleteAnimal() {
            let animalId = this.animalIdToDelete;
            axios.delete(`http://localhost:8080/api/v1/animals/${animalId}`)
                .then(response => {
                    console.log('Pet excluído com sucesso:', response.data);
                    this.closeDeleteModal();
                    this.loadCustomerAnimals(this.customerId);
                })
                .catch(error => {
                    console.error('Erro ao excluir pet:', error);
                });
        },
        closeDeleteModal() {
            $('#deleteAnimalModal').modal('hide');
        }
    },
};
</script>