<script setup>
import ptBrLocale from '@fullcalendar/core/locales/pt-br';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import FullCalendar from '@fullcalendar/vue3';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useScreens } from 'vue-screen-utils';

FullCalendar; // make the <FullCalendar> tag available

const calendarOptions = ref({
  plugins: [dayGridPlugin, interactionPlugin],
  initialView: 'dayGridMonth',
  locale: ptBrLocale,
  editable: true,
  selectable: true,
  events: []
})

const Eventos = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/v1/appointment');
    const events = response.data.map(event => ({
      title: event.title,
      start: event.start
    }));
    calendarOptions.value.events = events;
  } catch (error) {
    console.error('Erro ao buscar eventos:', error);
  }
};

Eventos(); // Chamar a função para buscar eventos

const { mapCurrent } = useScreens({
  xs: '1000px',
  sm: '640px',
});

const columns = mapCurrent({ lg: 2 }, 1);
const expanded = mapCurrent({ lg: false }, true);

const router = useRouter();

const goToAppointment = () => {
  router.push('/agenda/cadastro');
};

</script>

<template>
  <div class="d-flex flex-row-reverse">
    <button @click="goToAppointment" class="btn btn-primary mx-3">Fazer agendamento</button>
  </div>
  <div class="btn-group d-flex s-3 justify-content-sm-center" role="group"
    ria-label="Basic checkbox toggle button group">
    <router-link to="/agenda" class="btn btn-primary btn-focus mt-3 mb-2 ml-3" active-class="active">Mês</router-link>
    <router-link to="/agenda/week" class="btn btn-primary mt-3 mb-2" active-class="active">Semana</router-link>
    <router-link to="/agenda/day" class="btn btn-primary mt-3 mb-2 mr-3 " active-class="active">Dia</router-link>
  </div>
  <div>
    <FullCalendar :options="calendarOptions" />
  </div>
</template>

<style></style>
