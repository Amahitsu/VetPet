<script setup>
import { useScreens } from 'vue-screen-utils';

const { mapCurrent } = useScreens({
    xs: '1000px',
    sm: '640px',


});
const columns = mapCurrent({ lg: 2 }, 1);
const expanded = mapCurrent({ lg: false }, true);
</script>

<template>
    <VCalendar :columns="columns" :expanded="expanded" show-weeknumbers />
</template>

<style></style>