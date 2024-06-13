<script setup>
import { useRouter } from 'vue-router';
import { useScreens } from 'vue-screen-utils';

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
    <div class="btn-group d-flex s-3 justify-content-sm-center" role="group" aria-label="Basic example">
      <button type="button" class="btn btn-primary">Dia</button>
      <button type="button" class="btn btn-primary">Semana</button>
      <button type="button" class="btn btn-primary">Mês</button>
    </div>
  <div>
    <VCalendar :columns="columns" :expanded="expanded" show-weeknumbers />
    <button @click="goToAppointment" class="btn btn-primary mt-3">Fazer Agendamento</button>
  </div>
</template>

<style>
/* Adicione seus estilos aqui */
</style>
