<template>
    <RegisterSpecie />
    <div class="d-flex justify-content-between">
        <h2>Espécies</h2>
        <div class="d-flex align-items-center">
            <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#modalSpecie">
                Adicionar espécie
            </button>
        </div>
    </div>


    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Espécie</th>
                <th width="150">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="specie in species" :key="specie.id">
                <td>{{ specie.id }}</td>
                <td>{{ specie.name }}</td>
                <td>
                    <button class="btn btn-sm btn-primary">Editar</button>
                    <button class="btn btn-sm btn-danger" @click="confirmDelete(specie.id)">Deletar</button>
                </td>
            </tr>
        </tbody>
    </table>

</template>

<script>
import axios from 'axios';
import RegisterSpecie from '../screensRegister/RegisterSpecie.vue';

export default {
    components: {
        RegisterSpecie
    },
    data() {
        return {
            species: [],
        };
    },
    created() {
        this.loadSpecies();
    },
    methods: {
        loadSpecies() {
            axios({
                method: "GET",
                url: "http://localhost:8080/api/v1/species",
            })
                .then((response) => {
                    console.log(response.data.data)
                    this.species = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar as espécies:', error);
                });
        },
        confirmDelete(id) {
            if (confirm("Tem certeza que deseja excluir esta espécie?")) {
                this.deleteSpecie(id);
            }
        },
        deleteSpecie(id) {
            axios.delete(`http://localhost:8080/api/v1/species/${id}`)
                .then(response => {
                    console.log('Espécie excluída com sucesso:', response.data);
                    location.reload();
                })
                .catch(error => {
                    console.error('Erro ao excluir espécie:', error);
                });
        }
    }
}
</script>

<style>
</style>