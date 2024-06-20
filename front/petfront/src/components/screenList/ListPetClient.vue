<template>
    <section>
        <h4>Pets do Cliente: {{ customer.name }}</h4>
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
                        <td>{{ animal.specie ? animal.specie.name : 'Carregando...' }}</td>
                        <td>{{ animal.breed ? animal.breed.name : 'Carregando...' }}</td>
                        <td class="btn-group text-end">
                            <button class="btn btn-icon btn-sm btn-primary me-1" @click="updatePetClient(animal.id)">
                                <span class="material-symbols-rounded">edit</span>
                            </button>
                            <button class="btn btn-icon btn-sm btn-danger" @click="confirmDelete(animal.id)">
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
    </section>
</template>
<script>
import axios from 'axios';

export default {
    data() {
        return {
            customer: {},
            customerAnimals: [],
            loading: true, // Indicador de carregamento inicial
        };
    },
    created() {
        const customerId = this.$route.params.customerId;
        this.loadCustomerById(customerId);
        this.loadCustomerAnimals(customerId);
    },
    methods: {
        loadCustomerById(id) {
            axios.get(`http://localhost:8080/api/v1/customers/${id}`)
                .then((response) => {
                    console.log('Cliente carregado:', response.data.data);
                    this.customer = response.data.data;
                })
                .catch(error => {
                    this.loading = false; // Parar o indicador de carregamento em caso de erro
                    if (error.response && error.response.status === 404) {
                        alert('Cliente não encontrado');
                    }
                    console.error('Erro ao carregar cliente:', error);
                });
        },
        loadCustomerAnimals(customerId) {
            axios.get(`http://localhost:8080/api/v1/animals?customerId=${customerId}`)
                .then((response) => {
                    console.log('Animais do cliente carregados:', response.data);
                    this.customerAnimals = response.data; // Assumindo que a resposta é um array de animais
                    this.loading = false; // Parar o indicador de carregamento após carregar os animais
                })
                .catch(error => {
                    this.loading = false; // Parar o indicador de carregamento em caso de erro
                    console.error('Erro ao carregar animais do cliente:', error);
                });
        },
        updatePetClient(animalId) {
            // Lógica para atualizar o animal do cliente
        },
        confirmDelete(animalId) {
            // Lógica para confirmar a exclusão do animal do cliente
        },
    },
};
</script>