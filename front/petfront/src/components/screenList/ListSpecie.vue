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
                <th>Status</th>
                <th width="150">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="specie in species" :key="specie.id">
                <td>{{ specie.id }}</td>
                <td>{{ specie.name }}</td>
                <td>{{ specie.active ? "Ativo" : "Inativo" }}</td>
                <td>
                    <button class="btn btn-sm btn-primary">Editar</button>
                    <button class="btn btn-sm btn-danger">Deletar</button>
                </td>
            </tr>
        </tbody>
    </table>

</template>

<script>
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
            this.species = [
                { id: 1, name: 'Cachorro', active: true },
                { id: 2, name: 'Gato', active: true }
            ]
        }
    }
}
</script>

<style>
</style>