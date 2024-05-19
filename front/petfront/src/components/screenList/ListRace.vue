<template>
    <div class="d-flex justify-content-between">
        <h2>Raças</h2>
        <div class="d-flex align-items-center">
            <router-link to="/raca/cadastro" class="btn btn-primary btn-sm">Adicionar raça</router-link>
        </div>
    </div>


    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Raça</th>
                <th>Espécie</th>
                <th>Status</th>
                <th width="150">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="race in races" :key="race.id">
                <td>{{ race.id }}</td>
                <td>{{ race.name }}</td>
                <td>{{ race.specie }}</td>
                <td>{{ race.active ? "Ativo" : "Inativo" }}</td>
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
            races: [],
        };
    },
    created() {
        this.loadRaces();
    },
    methods: {
        loadRaces() {
            this.races = [
                { id: 1, name: 'Pincher', specie: 'Cachorro', active: true },
                { id: 2, name: 'Chihuahua', specie: 'Cachorro', active: true }
            ]
        }
    }
}
</script>

<style>
</style>