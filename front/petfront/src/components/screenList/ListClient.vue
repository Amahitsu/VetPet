<template>
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Status</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="customer in customers" :key="customer.id">
                <td>{{ customer.id }}</td>
                <td>{{ customer.name }}</td>
                <td>{{ customer.phone }}</td>
                <td>{{ customer.active ? "Ativo" : "Inativo"}}</td>
                <td>
                    <button class="btn btn-primary" @click="goToAddAnimalPage(customer.id)">Add animal</button>
                    <button class="btn btn-danger" @click="deletarCliente(customer)">Deletar</button>
                </td>
            </tr>
        </tbody>
    </table>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            customers: [],
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
                .then((response ) => {
                    console.log(response.data.data)
                    this.customers = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar o cliente:', error);
                });
        },
        goToAddAnimalPage(id) {
            this.$router.push({ path:`/cliente/${id}/adicionar-pet`});
        },
    }
}
</script>

<style></style>