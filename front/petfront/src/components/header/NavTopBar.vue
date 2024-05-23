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
    <nav id="main-navbar" class="navbar navbar-expand-lg fixed-top">
        <div class="container-fluid">
<<<<<<< HEAD
            <a class="navbar-brand" href="#">
                <img src="https://mdbcdn.b-cdn.net/img/logo/mdb-transaprent-noshadows.webp" height="25" alt="MDB Logo"
                    loading="lazy" />
            </a>
            <ul class="navbar-nav ms-auto d-flex flex-row">
=======
            <!-- Brand -->
            <router-link to="/principal" class="navbar-brand text-white" href="#">VetPet</router-link>
            <!-- Right links -->
            <-- <ul class="navbar-nav ms-auto d-flex flex-row">
                <!-- Nome do login -->
>>>>>>> 634757c441a44f0513fdceee428fd788b3975c80
                <li class="dropdown">
                    <button class="btn btn-dark dropdown-toggle text-white" type="button" id="dropdownMenuButton1"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        <label>{{ nameWorker }}</label>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li>
                            <router-link to="/">
                                <a class="dropdown-item" href="#">Logout</a>
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
    background-color: #1e2627;
    position: fixed;
}
</style>
