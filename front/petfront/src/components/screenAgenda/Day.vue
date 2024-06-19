<template>
    <div class="d-flex flex-row-reverse">
        <button @click="goToAppointment" class="btn btn-primary mx-3">Fazer agendamento</button>
    </div>
    <div class="btn-group d-flex s-3 justify-content-sm-center" role="group"
        ria-label="Basic checkbox toggle button group">
        <router-link to="/agenda" class="btn btn-primary btn-focus mt-3 mb-2 ml-3"
            active-class="active">Mês</router-link>
        <router-link to="/agenda/week" class="btn btn-primary mt-3 mb-2" active-class="active">Semana</router-link>
        <router-link to="/agenda/day" class="btn btn-primary mt-3 mb-2 mr-3 " active-class="active">Dia</router-link>
    </div>
    <div>
        <FullCalendar :options="calendarOptions" />
    </div>
</template>

<script>
import ptBrLocale from '@fullcalendar/core/locales/pt-br';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import FullCalendar from '@fullcalendar/vue3';
import axios from 'axios';

export default {
    components: {
        FullCalendar
    },
    data() {
        return {
            calendarOptions: {
                plugins: [dayGridPlugin, timeGridPlugin],
                initialView: 'timeGridDay',
                locale: ptBrLocale,
                buttonText: {
                    today: 'Hoje',
                    month: 'Mês',
                    week: 'Semana',
                    day: 'Dia',
                    list: 'Lista',
                    dayGridMonth: 'Mês',
                    timeGridWeek: 'Semana',
                    timeGridDay: 'Dia',
                    dayGridDay: 'Dia inteiro',
                    events: []
                },
                eventClick: this.editAppointment
            }
        }
    },
    created() {
        this.loadAppointments();
    },
    methods: {
        editAppointment(info) {
            this.$router.push({ path: `/agenda/${info.event.id}` });
        },
        loadAppointments() {
            axios.get('http://localhost:8080/api/v1/appointments')
                .then(response => {
                    let data = response.data.data;
                    let events = this.convertData(data);
                    
                    this.calendarOptions.events = events;
                })
                .catch(error => {
                    console.error('Erro ao buscar eventos:', error);
                });
        },
        convertData(data) {
            return data.map(item => {
                const date = new Date(item.date).toISOString().split('T')[0];
                const startDateTime = date + 'T' + item.start_time + ':00+00:00';
                const endDateTime = date + 'T' + item.finish_time + ':00+00:00';
                
                return {
                    id: item.id,
                    title: item.service.name,
                    start: startDateTime,
                    end: endDateTime,
                    observation: item.observation
                };
            });
        },
        goToAppointment() {
            this.$router.push({ path: `/agenda/cadastro` });
        }
    }
}
</script>