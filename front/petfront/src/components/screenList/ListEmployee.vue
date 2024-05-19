<template>
    <div class="d-flex justify-content-between">
        <h2>Funcionários</h2>
        <div class="d-flex align-items-center">
            <router-link to="/funcionario/cadastro" class="btn btn-primary btn-sm">Adicionar funcionário</router-link>
        </div>
    </div>


    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Status</th>
                <th width="150">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="employee in employees" :key="employee.id">
                <td>{{ employee.id }}</td>
                <td>{{ employee.name }}</td>
                <td>{{ employee.phone }}</td>
                <td>{{ employee.active ? "Ativo" : "Inativo" }}</td>
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
            employees: [],
        };
    },
    created() {
        this.loadEmployees();
    },
    methods: {
        loadEmployees() {
            this.employees = [
                { id: 1, name: 'Amanda', phone: '(47) 3336 5522', active: true },
                { id: 2, name: 'Pedro', phone: '(47) 3336 5544', active: true }
            ]
        }
    }
}
</script>

<style></style>