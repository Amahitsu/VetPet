<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import ModalWarning from './screenMessage/ModalWarning.vue';
// import footer from './footer/footer.vue';

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
            router.push('/Cadastro/Cliente');
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

<template>
    <section class="vh-100 gradient-custom">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card bg-info text-white" style="border-radius: 1rem">
                        <div class="card-body p-5 text-center">
                            <div class="mb-md-5 mt-md-4 pb-5">
                                <h2 class="fw-bold mb-2">Bem vindo ao PetVets</h2>
                                <p class="text-bold-50 mb-5">
                                    Por favor, digite usuário e senha!
                                </p>
                                <form @submit.prevent="login">
                                    <div data-mdb-input-init class="form-outline form-white mb-4">
                                        <label class="form-label" for="typeEmailX">Usuário</label>
                                        <input type="text" id="typeEmailX" class="form-control form-control-lg" v-model="username" required/>
                                    </div>
 
                                    <div data-mdb-input-init class="form-outline form-white mb-4">
                                        <label class="form-label" for="typePasswordX">Senha</label>
                                        <input type="password" id="typePasswordX" class="form-control form-control-lg" v-model="password" required/>
                                    </div>
 
                                    <button data-mdb-button-init data-mdb-ripple-init
                                        class="btn btn-outline-dark btn-lg px-5" type="submit">
                                        Entrar
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <ModalWarning :modalText="modalMessage" id="modal"/>
    <Footer/>
</template>

<style scoped>
</style>
