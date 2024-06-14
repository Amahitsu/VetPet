<script setup>
import ptBrLocale from '@fullcalendar/core/locales/pt-br';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import FullCalendar from '@fullcalendar/vue3';

const calendarOptions = {
    plugins: [timeGridPlugin, dayGridPlugin],
    initialView: 'timeGridWeek', // ou 'dayGridWeek'
    locale: ptBrLocale,
    // Outras opções de configuração, como eventos, editable, selectable, etc.
};
</script>

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
    <div class="full-calendar-container">
        <FullCalendar :options="calendarOptions" />
    </div>

</template>

<style scoped>
/* Remover sublinhado dos links dentro do FullCalendar */
.full-calendar-container .full-calendar a {
  text-decoration: none; /* Não é necessário !important se a especificidade for suficiente */
}
</style>