<template>
    <RegisterServiceType />
    <div class="d-flex justify-content-between">
        <h2>Serviços</h2>
        <div class="d-flex align-items-center">
            <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#modalService">
                Adicionar serviço
            </button>
        </div>
    </div>


    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Serviço</th>
                <th>Valor</th>
                <th>Status</th>
                <th width="150">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="service in services" :key="service.id">
                <td>{{ service.id }}</td>
                <td>{{ service.name }}</td>
                <td>R$ {{ service.value }}</td>
                <td>{{ service.active ? "Ativo" : "Inativo" }}</td>
                <td>
                    <button class="btn btn-sm btn-primary">Editar</button>
                    <button class="btn btn-sm btn-danger">Deletar</button>
                </td>
            </tr>
        </tbody>
    </table>

</template>

<script>
import RegisterServiceType from '../screensRegister/RegisterServiceType.vue';

export default {
    components: {
        RegisterServiceType
    },
    data() {
        return {
            services: [],
        };
    },
    created() {
        this.loadServices();
    },
    methods: {
        loadServices() {
            this.services = [
                { id: 1, name: 'Consulta', value: 150, active: true },
                { id: 2, name: 'Banho e tosa', value: 80,  active: true }
            ]
        }
    }
}
</script>

<style>
</style>