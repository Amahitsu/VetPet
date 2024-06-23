<script setup>
import { ref } from 'vue';

const nameWorker = ref('');

const data = localStorage.getItem('user');
if (data) {
    try {
        const parsedData = JSON.parse(data);
        nameWorker.value = parsedData.name;
    } catch (e) {
        console.error('Error parsing user data from localStorage', e);
    }
}
</script>

<template>
    <nav id="main-navbar" class="navbar navbar-expand-lg fixed-top custom ">
        <div class="container-fluid custom-card-bg">
            <router-link to="/painel-de-controle" class="navbar-brand text-white font-weight-bold" href="#">VetPet</router-link>
            <ul class="navbar-nav ms-auto d-flex flex-row">
                <li class="dropdown">
                    <button class="btn dropdown-toggle text-white custom-card-bg" type="button" id="dropdownMenuButton1"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        <label>{{ nameWorker }}</label>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li>
                            <router-link to="/">
                                <a class="dropdown-item" href="#">Sair</a>
                            </router-link>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</template>

<style>
.navbar {
    background-color: #578EBE;
    position: fixed;
}


</style>
