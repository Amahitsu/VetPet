<script setup>
import { useScreens } from 'vue-screen-utils';
import { useRouter } from 'vue-router';

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
  <div>
    <VCalendar :columns="columns" :expanded="expanded" show-weeknumbers />
    <button @click="goToAppointment" class="btn btn-primary mt-3">Fazer Agendamento</button>
  </div>
</template>

<style>
/* Adicione seus estilos aqui */
</style>
