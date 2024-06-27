<template>
    <div>
        <div class="d-flex justify-content-between">
            <h2>Funcionários</h2>
            <div class="d-flex align-items-center">
                <router-link to="/funcionario/cadastro" class="btn btn-primary btn-m">Adicionar funcionário</router-link>
            </div>
        </div>

        <div class="d-flex justify-content-between mt-3">
       <div class="position-relative">
           <input type="text" v-model="filter.name" placeholder="Filtrar por espécie" class="form-control input-with-icon" />
       </div>
    </div>

        <table class="table mt-3">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Função</th>
                    <th>Telefone</th>
                    <th>Nível</th>
                    <th>Status</th>
                    <th width="96">Ações</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="worker in filteredWorkers" :key="workers.id">
                    <td>{{ worker.name }}</td>
                    <td>{{ worker.functionn }}</td>
                    <td>{{ formatPhoneNumber(worker.phone) }}</td>
                    <td>{{ worker.userLevel }}</td>
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
                        <button type="button" class="btn btn-danger" @click="deleteWorker">Confirmar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="errorDeleteWorker" tabindex="-1" aria-labelledby="deleteWorkerModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="deleteWorkerModalLabel">Erro</h5>
                    <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Funcionário não pode ser excluído pois está associado a serviços.
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
            filter: {
                name: ''
            },
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
                    this.closeDeleteModal();
                    this.loadWorkers();
                })
                .catch(error => {
                    $('#errorDeleteWorker').modal('show');
                    $('#deleteWorkerModal').modal('hide');
                    setTimeout(function(){
                $('#errorDeleteWorker').modal('hide');
            }, 3000);
                });
        },
        closeDeleteModal() {
            $('#deleteWorkerModal').modal('hide');
        }
    },
    computed: {
        filteredWorkers() {
            return this.workers.filter(worker => {
                const filterText = this.filter.name.toLowerCase();
                const matchesName = worker.name.toLowerCase().includes(filterText);
                const matchesFunctionn = worker.functionn.toLowerCase().includes(filterText);
                //Retira os caracteres não numéricos do telefone
                const sanitizedPhone = worker.phone.replace(/\D/g, '');
                const matchesPhone = sanitizedPhone.includes(filterText);
                //Responde somente true e false
                const matchesActive = worker.active.toString().toLowerCase().includes(filterText);


                return matchesName || matchesFunctionn || matchesPhone || matchesActive;
            });
        }
    }
}
</script>

<style>
.input-with-icon {
    padding-left: 30px; 
    background: url('@/assets/lupa.png') no-repeat 8px center;
    background-size: 16px 16px; 
}
</style>