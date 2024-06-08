<script>
export default {
    data() {
        return {
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
        };
    },
    created() {
    },
    methods: {
        loadCustomers() {
            axios.get("http://localhost:8080/api/v1/customers")
                .then(response => {
                    this.customersList = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar os clientes:', error);
                });
        },
        loadAnimals() {
            axios.get("http://localhost:8080/api/v1/animals")
                .then(response => {
                    this.animalsList = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar os animaiss:', error);
                });
        },
        loadWorkers() {
            axios.get("http://localhost:8080/api/v1/workers")
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
        createAppointments() {
            axios.post("http://localhost:8080/api/v1/appointments", {
                start_time: this.start_timeAppointments,
                finish_time: this.finish_timeAppointments,
                medicines: this.medicinesAppointments,
                vaccines: this.vaccinesAppointments,
                date: this.dateAppointments,
                id_cusomer: this.customerAppointments,
                id_animals: this.animalAppointments,
                id_workers: this.workerAppointments,
                id_services: this.servicesAppointments,
                observation: this.observationAppointments
            })
                .then(response => {
                    console.log('Agendamento realizado com sucesso:', response.data);
                })
                .catch(error => {
                    console.error('Erro ao criar um agendamento:', error);
                });
        },
    },
}
</script>

<template>
    <div class="container mt-5">
        <h2>Agendamento</h2>
        <form @submit.prevent="createAppointments">
            <div class="mb-3 row">
                <label for="dateInput" class="col-sm-2 col-form-label">Data</label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" id="dateInput" v-model="dateAppointments" required>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="startTimeInput" class="col-sm-2 col-form-label">Hora de Início</label>
                <div class="col-sm-4">
                    <input type="time" class="form-control" id="startTimeInput" v-model="start_timeAppointments" required>
                </div>
                <label for="finishTimeInput" class="col-sm-2 col-form-label">Hora de Término</label>
                <div class="col-sm-4">
                    <input type="time" class="form-control" id="finishTimeInput" v-model="finish_timeAppointments" required>
                </div>
            </div>
            <div class="mb-3 row">
                <div class="col-sm-2">Medicamentos</div>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="medicinesCheckbox" v-model="medicinesAppointments">
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
                        <input class="form-check-input" type="checkbox" id="vaccinesCheckbox" v-model="vaccinesAppointments">
                        <label class="form-check-label" for="vaccinesCheckbox">
                            Necessário
                        </label>
                    </div>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="customerSelect" class="col-sm-2 col-form-label">Cliente</label>
                <div class="col-sm-10">
                    <select class="form-select" aria-label="Default select example" v-model="customerAppointments">
                        <option value="" selected>Selecione</option>
                        <option v-for="customers in customersList" :key="customers.id" :value="customers.id">{{
                        customers.name }}</option>
                    </select>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="animalSelect" class="col-sm-2 col-form-label">Animal</label>
                <div class="col-sm-10">
                    <select class="form-select" aria-label="Default select example" v-model="animalAppointments" :disabled="!customerAppointments" required>
                        <option value="" selected>Selecione</option>
                        <option v-for="animals in animalsList" :key="animals.id" :value="animals.id">{{
                        animals.name }}</option>
                    </select>
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
            <div class="mb-3 row">
                <label for="observationInput" class="col-sm-2 col-form-label">Observações</label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="observationInput" rows="3" v-model="observationAppointments" required></textarea>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex justify-content-end">
                    <button type="submit" class="btn btn-primary me-2">Salvar</button>
                    <button type="reset" class="btn btn-secondary">Cancelar</button>
                </div>
            </div>
        </form>
    </div>
</template>

<style>
</style>