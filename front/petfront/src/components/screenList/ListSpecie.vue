<template>
    <div class="d-flex justify-content-between">
        <h2>Espécies</h2>
        <div class="d-flex align-items-center">
            <router-link to="/especie/cadastro" class="btn btn-primary btn-sm">Adicionar espécie</router-link>
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
export default {
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