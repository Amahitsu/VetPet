<template>
    <div class="card">
        <div class="card-body">
            <div class="d-flex justify-content-between">
                <h4>Atendimento</h4>
                <div class="d-flex align-items-center">
                    <button type="button" class="btn btn-icon btn-primary btn-m me-2" @click="openModalService">
                        <span class="material-symbols-rounded">add_circle</span>
                        <span class="material-symbols-rounded">stethoscope</span>
                    </button>

                    <button type="button" class="btn btn-icon btn-primary btn-m me-2" @click="openModalVaccine">
                        <span class="material-symbols-rounded">add_circle</span>
                        <span class="material-symbols-rounded">syringe</span>
                    </button>

                    <button type="button" class="btn btn-icon btn-primary btn-m" @click="openModalMedicine">
                        <span class="material-symbols-rounded">add_circle</span>
                        <span class="material-symbols-rounded">pill</span>
                    </button>
                </div>
            </div>

            <div v-if="loading">
                Carregando...
            </div>
            <div v-else>
                <table class="table mt-3" v-if="activities.length > 0">
                    <thead>
                        <tr>
                            <th>Descrição</th>
                            <th>Tipo</th>
                            <th>Valor</th>
                            <th>Observação</th>
                            <th class="text-end">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="activity in activities" :key="activity.id">
                            <td>{{ activity.name }}</td>
                            <td>{{ activity.type }}</td>
                            <td>{{ formatCurrency(activity.value) }}</td>
                            <td>{{ activity.observation }}</td>
                            <td class="text-end">
                                <button type="button" class="btn btn-icon btn-sm btn-danger"
                                    @click="confirmDelete(activity.id)">
                                    <span class="material-symbols-rounded">delete</span>
                                </button>
                            </td>
                        </tr>
                        
                        <tr>
                            <th>Total</th>
                            <th></th>
                            <th>{{ formatCurrency(activitieTotal) }}</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </tbody>
                </table>
                <div v-else>
                    Não há atendimentos cadastrados para este agendamento.
                </div>
            </div>

            <div class="modal fade" id="deleteActivityModal" tabindex="-1" role="dialog"
                aria-labelledby="deleteActivityModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteModalLabel">Confirmar Exclusão</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"
                                @click="closeDeleteModal"></button>
                        </div>
                        <div class="modal-body">
                            Tem certeza que deseja excluir este atendimento?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" @click="closeDeleteModal">Cancelar</button>
                            <button type="button" class="btn btn-danger" @click="deleteActivity">Confirmar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="modalService" tabindex="-1" aria-labelledby="modalService" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Selecionar Serviço</h5>
                        <button type="button" class="btn-close" aria-label="Close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <label for="serviceSelect" class="col-sm-2 col-form-label">Serviço</label>
                            <div class="col-sm-10">
                                <select class="form-select">
                                    <option value="" selected>Selecione</option>
                                    <option v-for="service in servicesList" :key="service.id" :value="service.id">{{
                        service.name }}</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-primary me-2" @click="">Confirmar</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="modalVaccine" tabindex="-1" aria-labelledby="modalVaccine" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Selecionar Vacina</h5>
                        <button type="button" class="btn-close" aria-label="Close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <label for="vaccineSelect" class="col-sm-2 col-form-label">Vacina</label>
                            <div class="col-sm-10">
                                <select class="form-select">
                                    <option value="" selected>Selecione</option>
                                    <option v-for="vaccine in vaccinesList" :key="vaccine.id" :value="vaccine.id">{{
                        vaccine.name }}</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-primary me-2" @click="">Confirmar</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="modalMedicine" tabindex="-1" aria-labelledby="modalMedicine" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Selecionar Medicamento</h5>
                        <button type="button" class="btn-close" aria-label="Close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <label for="medicineSelect" class="col-sm-3 col-form-label">Medicamento</label>
                            <div class="col-sm-9">
                                <select class="form-select">
                                    <option value="" selected>Selecione</option>
                                    <option v-for="medicine in medicinesList" :key="medicine.id" :value="medicine.id">{{
                                        medicine.name }}</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-primary me-2" @click="">Confirmar</button>
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
            loading: true,
            servicesList: [],
            vaccinesList: [],
            medicinesList: [],
            activitieTotal: null,
            activities: []
        };
    },
    mounted() {
        this.loadActivities();
    },
    methods: {
        formatCurrency(value) {
            if (!value) return '';
            return parseFloat(value).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
        },
        openModalService() {
            $('#modalService').modal('show');
            this.loadServices();
        },
        openModalVaccine() {
            $('#modalVaccine').modal('show');
            this.loadVaccines();
        },
        openModalMedicine() {
            $('#modalMedicine').modal('show');
            this.loadMedicines();
        },
        loadActivities() {
            // adicionar chamada para lista de atendimentos
            // precisa receber id do agendamento
            this.loading = false;
            this.activities = [{
                id: 1,
                type: 'Serviço',
                name: 'Consulta',
                value: 10.00,
                observation: 'Teste'
            }, {
                id: 2,
                type: 'Medicamento',
                name: 'Aspirina',
                value: 10.00,
                observation: ''
            }, {
                id: 3,
                type: 'Vacina',
                name: 'Raiva',
                value: 10.00,
                observation: ''
            }]
            
            for (let i = 0; i < this.activities.length; i++){
                this.activitieTotal += this.activities[i].value;
            }
        },
        loadServices() {
            axios.get("http://localhost:8080/api/v1/services")
                .then(response => {
                    this.servicesList = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar os serviços:', error);
                });
        },
        loadVaccines() {
            axios.get("http://localhost:8080/api/v1/vaccines")
                .then(response => {
                    this.vaccinesList = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar os vacinas:', error);
                });

        },
        loadMedicines() {
            axios.get("http://localhost:8080/api/v1/medicines")
                .then(response => {
                    this.medicinesList = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar os medicamentos:', error);
                });
        },
        confirmDelete(activityId) {
            this.activityIdToDelete = activityId;
            $('#deleteActivityModal').modal('show');
        },
        deleteActivity() {
            let activityId = this.activityIdToDelete;
            axios.delete(`http://localhost:8080/api/v1/animals/${activityId}`)
                .then(response => {
                    console.log('Atendimento excluído com sucesso:', response.data);
                    this.closeDeleteModal();
                })
                .catch(error => {
                    console.error('Erro ao excluir atendimento:', error);
                });
        },
        closeDeleteModal() {
            $('#deleteActivityModal').modal('hide');
        }
    },
};
</script>