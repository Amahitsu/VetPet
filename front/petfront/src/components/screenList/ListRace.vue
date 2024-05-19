<template>
    <RegisterRace />
    <div class="d-flex justify-content-between">
        <h2>Raças</h2>
        <div class="d-flex align-items-center">
            <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#modalRace">
                Adicionar raça
            </button>
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
import RegisterRace from '../screensRegister/RegisterRace.vue';

export default {
    components: {
        RegisterRace
    },
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

<style></style>