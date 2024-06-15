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
            <tr v-for="customerPet in customerPets" :key="customerPet.id">
                <td>{{ customerPet.name }}</td>
                <td>{{ customerPet.specie.name }}</td>
                <td>{{ customerPet.race.name }}</td>
                <td class="btn-group text-end">
                    <button class="btn btn-icon btn-sm btn-primary me-1" @click="updatePetClient(customerPet.id)">
                        <span class="material-symbols-rounded">edit</span>
                    </button>
                    <button class="btn btn-icon btn-sm btn-danger" @click="confirmDelete(customerPet.id)">
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
            customerPetId: null,
            customerPets: []
        };
    },
    methods: {
        loadCustomerPets(customerId) {
            this.customerPets = [{
                id: 1,
                name: 'Belinha',
                specie: {
                    id: 1,
                    name: 'Cachorro'
                },
                race: {
                    id: 1,
                    name: 'Pitbull'
                }
            }]

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
}
</script>