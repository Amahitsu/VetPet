<template>
    <h4>Pets</h4>
    <table class="table my-4">
        <thead>
            <tr>
                <th>Nome pet</th>
                <th>Espécie</th>
                <th>Raça</th>
                <th width="96">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="animals in animals" :key="animals.id">
                <td>{{ animals.name }}</td>
                <td>{{ animals.specie.name }}</td>
                <td>{{ animals.race.name }}</td>
                <td class="btn-group text-end">
                    <button class="btn btn-icon btn-sm btn-primary me-1" @click="updatePetClient(customer.id)">
                        <span class="material-symbols-rounded">edit</span>
                    </button>
                    <button class="btn btn-icon btn-sm btn-danger" @click="confirmDelete(customer.id)">
                        <span class="material-symbols-rounded">delete</span>
                    </button>
                </td>
            </tr>
        </tbody>
    </table>
</template>

<script>

export default {
    data() {
        return {
            customer: []
        };
    },
    created() {
        this.loadCustomerById(this.$route.params.customerId);
    },

    methods: {
        loadCustomerById(id) {
            axios({
                method: "GET",
                url: `http://localhost:8080/api/v1/customers/${id}`,
            })
                .then((response) => {
                    console.log(response.data.data);
                    this.customer = response.data.data;
                })
                .catch(error => {
                    if (error.response.status === 404) {
                        alert('Cliente não encontrado');
                    }
                    console.error('Erro ao listar o cliente:', error);
                });
        },

            /*
            Faltou endpoint para buscar pets de um cliente
            axios.get(`http://localhost:8080/api/v1/animals/${customerId}`)
                .then(response => {
                    console.error('Sucesso ao consultar pets do cliente:', error);
                    let data = response.data.data;

                    this.customerPets = data;
                })
                .catch(error => {
                    console.error('Erro ao consultar pets do cliente:', error);
                })*/
        },
        updatePetClient() {

        },
        confirmDelete() {

        }
    }

</script>