<template>
    <div>
        <h2>Agendamento</h2>
        <form>
            <div class="mb-3 row">
                <label for="dateInput" class="col-sm-2 col-form-label">Dia</label>
                <div class="col-sm-4">
                    <input type="date" class="form-control" id="dateInput" v-model="dateAppointments"  v-on:input="onChangeDateHandler" required>
                </div>
                <label for="selectedAppointmentSlot" class="col-sm-2 col-form-label">Horário</label>
                <div class="col-sm-4">
                    <v-select 
                        label="title" 
                        placeholder="Selecione"
                        v-model="selectedAppointmentSlot"     
                        :options="availableAppointmentsFromDate"
                        :disabled="isLoading" 
                    ></v-select>
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
                    <v-select 
                        label="title"  
                        v-model="customerAppointments"     
                        :options="customersList"
                        :reduce="customer => customer.id" 
                        @update:modelValue="onChangeCustomerHandler"
                    ></v-select>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="animalSelect" class="col-sm-2 col-form-label">Animal</label>
                <div class="col-sm-10">
                    <v-select 
                        label="title" 
                        placeholder="Selecione"
                        v-model="animalAppointments"     
                        :options="animalsList"
                        :reduce="animal => animal.id" 
                        :disabled="!customerAppointments || isLoading" 
                    ></v-select>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="workerSelect" class="col-sm-2 col-form-label">Funcionário</label>
                <div class="col-sm-10">
                    <!-- <select class="form-select" aria-label="Default select example" v-model="workerAppointments">
                        <option value="" selected>Selecione</option>
                        <option v-for="workers in workersList" :key="workers.id" :value="workers.id">{{
                        workers.name }}</option>
                    </select> -->
                    <v-select 
                        label="title" 
                        placeholder="Selecione"
                        v-model="workerAppointments"     
                        :options="workersList"
                        :reduce="worker => worker.id" 
                        :disabled="!animalAppointments || isLoading" 
                    ></v-select>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="serviceSelect" class="col-sm-2 col-form-label">Serviços</label>
                <div class="col-sm-10">
                    <!-- <select class="form-select" aria-label="Default select example" v-model="servicesAppointments">
                        <option value="" selected>Selecione</option>
                        <option v-for="services in servicesList" :key="services.id" :value="services.id">{{
                        services.name }}</option>
                    </select> -->
                    <v-select 
                        label="title" 
                        placeholder="Selecione"
                        v-model="servicesAppointments"     
                        :options="servicesList"
                        :reduce="service => service.id" 
                        :disabled="!workerAppointments || isLoading" 
                    ></v-select>
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
import { format } from 'date-fns'
import ListActivityPet from '../screenList/ListActivityPet.vue';
import { findAnimalsByCustomer } from '../../services/animals';
import { listAvailableSlotsByDate } from '../../services/appointments';
import { listWorkers } from '@/services/workers';
import { listServices } from '@/services/services';

export default {
    components: {
        vSelect: window["vue-select"],
        ListActivityPet
    },
    data() {
        return {
            appointmentId: null,
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
            servicesList: [],
            isLoading: false,
            availableAppointmentsFromDate: [],
            selectedAppointmentSlot: null
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
        // função para quando alterar o cliente limpar e carregar os animais do cliente
        onChangeCustomerHandler() {
            this.animalAppointments = '';
            this.loadAnimals();
        },
        onChangeDateHandler(){
            this.selectedAppointmentSlot = null
            this.loadAvailableAppointmentsByDate()
        },

        async loadAvailableAppointmentsByDate() {
            if(!this.dateAppointments) return

            const date = new Date(this.dateAppointments+ 'T00:00:00')
            const dateFormatted = format(date, "yyyy-MM-dd");


            const availableAppointmentsFromDate = await listAvailableSlotsByDate(dateFormatted)
            console.log(availableAppointmentsFromDate);
            this.availableAppointmentsFromDate = availableAppointmentsFromDate.map(appointment => ({
                ...appointment,
                title: `${appointment.start_time} - ${appointment.finish_time} `
            }));
        },
        async loadAppointment(appointmentId) {

            if (!appointmentId)
                return;

           const response = await axios.get(`http://localhost:8080/api/v1/appointments/${appointmentId}`)
            
            const data = response.data.data
            this.medicinesAppointments = data.medicines;
            this.vaccinesAppointments = data.vaccines;
            this.dateAppointments = new Date(data.date).toISOString().split('T')[0];
            this.customerAppointments = data.customer.id;
            this.animalAppointments = data.animal.id;
            this.workerAppointments = data.worker.id;
            this.servicesAppointments = data.service.id;
            this.observationAppointments = data.observation;
            this.selectedAppointmentSlot = {
                start_time: data.start_time,
                finish_time: data.finish_time,
                title: `${data.start_time} - ${data.finish_time}`
            }
            
            this.loadAvailableAppointmentsByDate()
            this.loadAnimals()
        },
        loadCustomers() {
            axios.get("http://localhost:8080/api/v1/customers")
                .then(response => {
                    this.customersList = response.data.data.map(this.transformToSelectItem);
                })
                .catch(error => {
                    console.error('Erro ao listar os clientes:', error);
                });
        },
        // método para buscar os animais do cliente
        async loadAnimals() {
            //define o carregamento para bloquear o select enquanto está carregando
            this.isLoading = true
            
            // this.customerAppointments chama o endpoint da api de animais, com o id do client
            // this.customerAppointments = id do cliente
            const animals = await findAnimalsByCustomer(this.customerAppointments);
            console.log('animals', animals)

            const animalsTransformed= animals.map(this.transformToSelectItem)
            // passa por todos itens do array retornado e transforma para o formato que o select precisa
            console.log('animalsTransformede', animalsTransformed);
            this.animalsList = animalsTransformed
            this.isLoading = false
        },
        async loadWorkers() {
            this.isLoading = true

            const workers = await listWorkers(this.workerAppointments)
            console.log('workers', workers)

            const workersTransformed = workers.map(this.transformToSelectItem)
            console.log('workersTransformed', workersTransformed)
            this.workersList = workersTransformed
            this.isLoading = false
        },
        async loadServices() {
            this.isLoading = true

            const services = await listServices(this.servicesAppointments)
            console.log('services', services)

            const servicesTransformed = services.map(this.transformToSelectItem)
            console.log('servicesTransformed', servicesTransformed)
            this.servicesList = servicesTransformed
            this.isLoading = false
        },
        saveAppointment() {
            let dateAppointments = new Date(this.dateAppointments + 'T00:00:00')
            let isoDateString = dateAppointments.toISOString();

            let data = {
                start_time: this.selectedAppointmentSlot.start_time,
                finish_time: this.selectedAppointmentSlot.finish_time,
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
        },
        transformToSelectItem(item) {
            return {
                id: item.id,
                title: item.name
            }
        },    
    }
}
</script>