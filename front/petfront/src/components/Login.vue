<template>
    <section class="vh-100 gradient-custom custom-bg">
        <div class="container py-5">
            <div class="row d-flex justify-content-center align-items-center">
                <div class="col-12 col-md-8 col-lg-4 col-xl-5">
                    <div class="card custom-card-bg text-white" style="border-radius: 1rem">
                        <div class="card-body p-5 text-center">

                            <img :src="DogPng" alt="Logo PetVets" style="max-width: 150px;">
                            <h2 class="fw-bold mb-2">Bem vindo ao PetVet!</h2>
                            <p class="text-white-50 mb-4">
                                Por favor, digite usuário e senha.
                            </p>
                            <form @submit.prevent="login">
                                <div class="form-outline form-white mb-3">
                                    <label class="form-label" for="typeEmailX">Usuário</label>
                                    <input type="text" id="typeEmailX" class="form-control form-control-lg"
                                        v-model="username" required />
                                </div>
                                <div class="form-outline form-white mb-3">
                                    <label class="form-label" for="typePasswordX">Senha</label>
                                    <input type="password" id="typePasswordX" class="form-control form-control-lg"
                                        v-model="password" required />
                                </div>
                                <button class="btn btn-primary btn-login btn-lg px-5" type="submit">
                                    Entrar
                                </button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <ModalWarning :modalText="modalMessage" id="modal" />

</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import DogPng from '../assets/dog.png';
import ModalWarning from './screenMessage/ModalWarning.vue';



const username = ref('');
const password = ref('');
const modalMessage = ref('');
const router = useRouter();

const login = () => {
    axios.post('http://localhost:8080/api/v1/login', {
        username: username.value,
        password: password.value
    })
        .then(response => {
            if (response.status === 202) {
                saveDataInCache(response.data);
                router.push('/painel-de-controle');
            } else {
                console.log(response);
            }
        })
        .catch(error => {
            if (error.response && (error.response.status === 404 || error.response.status === 401)) {
                const modal = document.getElementById("modal");
                modal.style.display = 'block';
                modalMessage.value = 'Usuário ou senha inválidos!';
                setTimeout(() => {
                    modal.style.display = 'none';
                }, 2000);
            }
        });
};

const saveDataInCache = (data) => {
    localStorage.setItem('user', JSON.stringify(data));
};
</script>