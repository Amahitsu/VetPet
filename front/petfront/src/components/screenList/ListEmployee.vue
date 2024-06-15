<template>
    <div>
        <div class="d-flex justify-content-between">
            <h2>Funcionários</h2>
            <div class="d-flex align-items-center">
                <router-link to="/funcionario/cadastro" class="btn btn-primary btn-m">Adicionar funcionário</router-link>
            </div>
        </div>

        <table class="table mt-3">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th>Status</th>
                    <th width="96">Ações</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="worker in workers" :key="workers.id">
                    <td>{{ worker.name }}</td>
                    <td>{{ formatPhoneNumber(worker.phone) }}</td>
                    <td>{{ worker.active ? "Ativo" : "Inativo" }}</td>
                    <td>
                        <button class="btn btn-icon btn-sm btn-primary me-1" @click="editWorker(worker.id)">
                            <span class="material-symbols-rounded">edit</span>
                        </button>
                        <button class="btn btn-icon btn-sm btn-danger" @click="confirmDelete(worker.id)">
                            <span class="material-symbols-rounded">delete</span>
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="modal fade" id="deleteWorkerModal" tabindex="-1" role="dialog" aria-labelledby="deleteWorkerModallLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteWorkerModallLabel">Confirmar Exclusão</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"
                        @click="closeDeleteModal"></button>
                    </div>
                    <div class="modal-body">
                        Tem certeza que deseja excluir este funcionário?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" @click="closeDeleteModal">Cancelar</button>
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
        formatPhoneNumber(phoneNumber) {
            phoneNumber = phoneNumber.replace(/\D/g, '');
            if (phoneNumber.length === 9) {
                return phoneNumber.replace(/(\d{5})(\d{4})/, '$1-$2');
            } else if (phoneNumber.length === 11) {
                return phoneNumber.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
            } else {
                return phoneNumber;
            }
        },
        loadWorkers() {
            axios({
                method: "GET",
                url: "http://localhost:8080/api/v1/worker",
            })
                .then((response) => {
                    console.log(response.data.data)
                    this.workers = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar funcionários:', error);
                });
        },
        editWorker(workerId) {
            this.$router.push({ path: `/funcionario/${workerId}` });
        },
        confirmDelete(workerId) {
            this.workerIdToDelete = workerId;
            $('#deleteWorkerModal').modal('show');
        },
        deleteWorker() {
            let workerId = this.workerIdToDelete;
            axios.delete(`http://localhost:8080/api/v1/worker/${workerId}`)
                .then(response => {
                    console.log('Funcionário excluído com sucesso:', response.data);
                    this.loadWorkers();
                })
                .catch(error => {
                    console.error('Erro ao excluir funcionário:', error);
                });
        },
        closeDeleteModal() {
            $('#deleteWorkerModal').modal('hide');
        }
    }
}
</script>

<style></style>