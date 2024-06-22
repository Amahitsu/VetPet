<template>
    <div>
        <h2>Agendamento</h2>
        <form>
            <div class="mb-3 row">
                <label for="dateInput" class="col-sm-2 col-form-label">Data</label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" id="dateInput" v-model="dateAppointments" required>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="startTimeInput" class="col-sm-2 col-form-label">Hora de Início</label>
                <div class="col-sm-4">
                    <input type="time" class="form-control" id="startTimeInput" v-model="start_timeAppointments"
                        required>
                </div>
                <label for="finishTimeInput" class="col-sm-2 col-form-label">Hora de Término</label>
                <div class="col-sm-4">
                    <input type="time" class="form-control" id="finishTimeInput" v-model="finish_timeAppointments"
                        required>
                </div>
            </div>
            <!--<div class="mb-3 row">
                <div class="col-sm-2">Medicamentos</div>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="medicinesCheckbox"
                            v-model="medicinesAppointments">
                        <label class="form-check-label" for="medicinesCheckbox">
                            Necessário
                        </label>
                    </div>
                </div>
            </div>
            <div class="mb-3 row">
                <div class="col-sm-2">Vacinas</div>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="vaccinesCheckbox"
                            v-model="vaccinesAppointments">
                        <label class="form-check-label" for="vaccinesCheckbox">
                            Necessário
                        </label>
                    </div>
                </div>
            </div>-->
            <div class="mb-3 row">
                <label for="customerSelect" class="col-sm-2 col-form-label">Cliente</label>
                <div class="col-sm-10">
                    <select class="form-select" aria-label="Default select example" v-model="customerAppointments" @change="loadAnimals" :disabled="animalName">
                        <option value="" selected>Selecione</option>
                        <option v-for="customers in customersList" :key="customers.id" :value="customers.id">{{
                        customers.name }}</option>
                    </select>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="animalSelect" class="col-sm-2 col-form-label">Animal</label>
                <div class="col-sm-10 d-flex align-items-center">
                    <select class="form-select" aria-label="Default select example" v-model="animalAppointments"
                        :disabled="!customerAppointments" required v-if="!animalName">
                        <option value="" selected>Selecione</option>
                        <option v-for="animals in animalsList" :key="animals.id" :value="animals.id">{{
                        animals.name }}</option>
                    </select>
                    <div v-if="animalName">
                        {{ animalName }}
                    </div>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="workerSelect" class="col-sm-2 col-form-label">Funcionário</label>
                <div class="col-sm-10">
                    <select class="form-select" aria-label="Default select example" v-model="workerAppointments">
                        <option value="" selected>Selecione</option>
                        <option v-for="workers in workersList" :key="workers.id" :value="workers.id">{{
                        workers.name }}</option>
                    </select>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="serviceSelect" class="col-sm-2 col-form-label">Serviços</label>
                <div class="col-sm-10">
                    <select class="form-select" aria-label="Default select example" v-model="servicesAppointments">
                        <option value="" selected>Selecione</option>
                        <option v-for="services in servicesList" :key="services.id" :value="services.id">{{
                        services.name }}</option>
                    </select>
                </div>
            </div>
            <div class="mb-5 row">
                <label for="observationInput" class="col-sm-2 col-form-label">Observações</label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="observationInput" rows="3" v-model="observationAppointments"
                        required></textarea>
                </div>
            </div>
            <div class="row mb-4" v-if="appointmentId">
                <div class="col-md-12">
                    <ListActivityPet ref="listActivityPet" />
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex justify-content-end">
                    <button type="button" class="btn btn-secondary me-2" @click="goToAgenda">Cancelar</button>
                    <button type="button" class="btn btn-primary" @click="saveAppointment">Salvar</button>
                </div>
            </div>
        </form>
    </div>
</template>

<script>
import axios from 'axios';
import ListActivityPet from '../screenList/ListActivityPet.vue';

export default {
    components: {
        ListActivityPet
    },
    data() {
        return {
            appointmentId: null,
            start_timeAppointments: '',
            finish_timeAppointments: '',
            medicinesAppointments: false,
            vaccinesAppointments: false,
            dateAppointments: '',
            customerAppointments: '',
            animalAppointments: '',
            workerAppointments: '',
            servicesAppointments: '',
            observationAppointments: '',
            animalsList: [],
            workersList: [],
            customersList: [],
            servicesList: []
        };
    },
    mounted() {
        this.loadCustomers();
        this.loadServices();
        this.loadWorkers();

        //edicao
        this.appointmentId = this.$router.currentRoute.value.params.appointmentId;
        this.loadAppointment(this.appointmentId);
    },
    methods: {
        loadAppointment(appointmentId) {
            if (!appointmentId)
                return;

            axios.get(`http://localhost:8080/api/v1/appointments/${appointmentId}`)
                .then(response => {
                    let data = response.data.data;

                    this.start_timeAppointments = data.start_time;
                    this.finish_timeAppointments = data.finish_time;
                    this.medicinesAppointments = data.medicines;
                    this.vaccinesAppointments = data.vaccines;
                    this.dateAppointments = new Date(data.date).toISOString().split('T')[0];
                    this.customerAppointments = data.customer.id;
                    this.animalAppointments = data.animal.id;
                    this.animalName = data.animal.name;
                    this.workerAppointments = data.worker.id;
                    this.servicesAppointments = data.service.id;
                    this.observationAppointments = data.observation;
                })
                .catch(error => {
                    console.error('Erro ao criar cliente:', error);
                });

        },
        loadCustomers() {
            axios.get("http://localhost:8080/api/v1/customers")
                .then(response => {
                    this.customersList = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar os clientes:', error);
                });
        },
        loadAnimals(animalId) {
            if(!this.customerAppointments)
                return

            let customerId = this.customerAppointments;

            axios.get(`http://localhost:8080/api/v1/animals?customerId=${customerId}`)
                .then((response) => {
                    this.animalsList = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao carregar animais do cliente:', error);
                });
        },
        loadWorkers() {
            axios.get("http://localhost:8080/api/v1/worker")
                .then(response => {
                    this.workersList = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar os funcionários:', error);
                });
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
        saveAppointment() {
            let dateAppointments = new Date(this.dateAppointments + 'T00:00:00')
            let isoDateString = dateAppointments.toISOString();

            let data = {
                start_time: this.start_timeAppointments,
                finish_time: this.finish_timeAppointments,
                medicines: this.medicinesAppointments,
                vaccines: this.vaccinesAppointments,
                date: isoDateString,
                id_customer: this.customerAppointments,
                id_animals: this.animalAppointments,
                id_workers: this.workerAppointments,
                id_services: this.servicesAppointments,
                observation: this.observationAppointments
            }

            if (this.appointmentId)
                this.editAppointment(this.appointmentId, data)
            else
                this.createAppointment(data)
        },
        createAppointment(data) {
            axios.post("http://localhost:8080/api/v1/appointments", data)
                .then(response => {
                    console.log('Agendamento realizado com sucesso:', response.data);
                    this.goToAgenda();
                })
                .catch(error => {
                    console.error('Erro ao criar um agendamento:', error);
                });
        },
        editAppointment(appointmentId, data) {
            if (!appointmentId)
                return;

            axios.put(`http://localhost:8080/api/v1/appointments/${appointmentId}`, data)
                .then(response => {
                    this.goToAgenda();
                })
                .catch(error => {
                    console.error('Erro ao editar cliente:', error);
                });
        },
        goToAgenda() {
            this.$router.push({ path: `/agenda` });
        }
    }
}
</script>