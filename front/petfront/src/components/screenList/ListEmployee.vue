<template>
    <div>
        <div class="d-flex justify-content-between">
            <h2>Funcionários</h2>
            <div class="d-flex align-items-center">
                <router-link to="/funcionario/cadastro" class="btn btn-primary btn-m">Adicionar Funcionário</router-link>
            </div>
        </div>

        <table class="table mt-3">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th>Status</th>
                    <th width="200">Ações</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="workers in workers" :key="workers.id">
                    <td>{{ workers.name }}</td>
                    <td>{{ workers.phone }}</td>
                    <td>{{ workers.active ? "Ativo" : "Inativo" }}</td>
                    <td>
                        <button class="btn btn-sm btn-primary" @click="">Editar</button>
                        <button class="btn btn-sm btn-danger" @click="confirmDelete(customer.id)">Deletar</button>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel">Confirmar Exclusão</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Tem certeza que deseja excluir este funcionário?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-danger" @click="confirmDeletion">Deletar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
import axios from 'axios';

export default {
    data() {
        return {
            workers: [],
            workerIdToDelete: null,
        };
    },
    created() {
        this.loadWorkers();
    },
    methods: {
        loadWorkers() {
            axios({
                method: "GET",
                url: "http://localhost:8080/api/v1/workers",
            })
                .then((response) => {
                    console.log(response.data.data)
                    this.workers = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar funcionários:', error);
                });
        },
        confirmDelete(id) {
            this.workerIdToDelete = id;
            $('#deleteModal').modal('show');
        },
        confirmDeletion() {
            this.deleteWorker(this.workerIdToDelete);
            $('#deleteModal').modal('hide');
        },
        deleteWorker(id) {
            axios.delete(`http://localhost:8080/api/v1/workers/${id}`)
                .then(response => {
                    console.log('Cliente excluído com sucesso:', response.data);
                    this.loadWorkers();
                })
                .catch(error => {
                    console.error('Erro ao excluir funcionário:', error);
                });
        }
    }
}
</script>

<style></style>