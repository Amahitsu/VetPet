<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { getAddressByCep } from '../../services/getAddressByCep.vue';
import ModalWarning from '../screenMessage/ModalWarning.vue';

const router = useRouter();

const name = ref('');
const cpf = ref('');
const email = ref('');
const phone = ref('');
const cep = ref('');
const street = ref('');
const numberStreet = ref('');
const complement = ref('');
const state = ref('');
const city = ref('');
const neighborhood = ref('');
const modalMessage = ref('');
const functionWorker = ref('');
const userName = ref('');
const password = ref('');

document.addEventListener('DOMContentLoaded', function () {
    const checkboxes = document.querySelectorAll('.single-checkbox');

    checkboxes.forEach((checkbox) => {
        checkbox.addEventListener('change', function () {
            if (this.checked) {
                checkboxes.forEach((cb) => {
                    if (cb !== this) {
                        cb.checked = false;
                    }
                });
            }
        });
    });
});

const getAddressData = async (cepValue) => {
    try {
        return await getAddressByCep(cepValue.replace('-', ''));
    } catch (error) {
        console.error('Erro ao buscar o endereço:', error);
        return null;
    }
};

const formatCep = async (event) => {
    let cepValue = event.target.value.replace(/\D/g, '');
    if (cepValue.length > 5) {
        cepValue = cepValue.slice(0, 5) + '-' + cepValue.slice(5, 8);
    }
    cep.value = cepValue;

    if (cep.value.length === 9) {
        const address = await getAddressData(cep.value);
        if (address) {
            street.value = address.logradouro;
            neighborhood.value = address.bairro;
            city.value = address.localidade;
            state.value = address.uf;
        }
    }
};

const formatCpf = (event) => {
    let cpfValue = event.target.value.replace(/\D/g, '');

    if (cpfValue.length > 3) {
        cpfValue = cpfValue.slice(0, 3) + '.' + cpfValue.slice(3);
    }
    if (cpfValue.length > 7) {
        cpfValue = cpfValue.slice(0, 7) + '.' + cpfValue.slice(7);
    }
    if (cpfValue.length > 11) {
        cpfValue = cpfValue.slice(0, 11) + '-' + cpfValue.slice(11);
    }

    cpf.value = cpfValue;
};

const formatPhone = (event) => {
    let phoneNumber = event.target.value.replace(/\D/g, '');

    if (phoneNumber.length > 0) {
        phoneNumber = '(' + phoneNumber;
    }
    if (phoneNumber.length > 3) {
        phoneNumber = phoneNumber.slice(0, 3) + ') ' + phoneNumber.slice(3);
    }
    if (phoneNumber.length > 10) {
        phoneNumber = phoneNumber.slice(0, 10) + '-' + phoneNumber.slice(10);
    }

    phone.value = phoneNumber;
};

const createWorker = async () => {
    const address = `CEP: ${cep.value}, Rua: ${street.value}, Número: ${numberStreet.value}, Complemento: ${complement.value}, Bairro: ${neighborhood.value}, Cidade: ${city.value}, Estado: ${state.value}`;

    try {
        const response = await axios.post("http://localhost:8080/api/v1/workers", {
            name: name.value,
            cpf: cpf.value,
            phone: phone.value,
            email: email.value,
            address: address,
            function: functionWorker.value,
        });

        const modal = document.getElementById("modal");
        modal.style.display = 'block';
        modalMessage.value = 'Funcionário cadastrado com sucesso!';
        setTimeout(() => {
            router.push('/funcionarios')
        }, 2000);

    } catch (error) {
        console.error('Erro ao criar o cliente:', error);
    }
};

const checkboxes = document.querySelectorAll('.single-checkbox');
document.addEventListener('DOMContentLoaded', function () {

    checkboxes.forEach((checkbox) => {
        checkbox.addEventListener('change', function () {
            if (this.checked) {
                checkboxes.forEach((cb) => {
                    if (cb !== this) {
                        cb.checked = false;
                    }
                });
            }
        });
    });
});
</script>

<template>
    <section>
        <form @submit.prevent="createWorker">
        <form @submit.prevent="createWorker">
            <h2>Cadastro de Funcionário</h2>
            <div class="row mt-3">
                <div class="col-md-9">
                <div class="col-md-9">
                    <label for="inputName" class="form-label">Nome Completo</label>
                    <div class="d-flex">
                        <input type="text" class="form-control" placeholder="Digite Nome Completo" maxlength="45"
                            aria-label="Nome Completo" v-model="name" required>
                        <div class="col-md-3 d-flex align-itens-center gap-3">
                            <div class="form-check md-3">
                                <input class="form-check-input single-checkbox" type="checkbox" value=""
                                    id="flexCheckDefault" checked>
                                <label class="form-check-label" for="flexCheckDefault">
                                    Ativo
                                </label>
                            </div>
                            <div class="form-check md-3">
                                <input class="form-check-input single-checkbox" type="checkbox" value=""
                                    id="flexCheckChecked">
                                <label class="form-check-label" for="flexCheckChecked">
                                    Inativo
                                </label>
                            </div>
                        </div>
                    <div class="d-flex">
                        <input type="text" class="form-control" placeholder="Digite Nome Completo" maxlength="45"
                            aria-label="Nome Completo" v-model="name" required>
                        <div class="col-md-3 d-flex align-itens-center gap-3">
                            <div class="form-check md-3">
                                <input class="form-check-input single-checkbox" type="checkbox" value=""
                                    id="flexCheckDefault" checked>
                                <label class="form-check-label" for="flexCheckDefault">
                                    Ativo
                                </label>
                            </div>
                            <div class="form-check md-3">
                                <input class="form-check-input single-checkbox" type="checkbox" value=""
                                    id="flexCheckChecked">
                                <label class="form-check-label" for="flexCheckChecked">
                                    Inativo
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mt-3  ">
                <div class="col-md-4">
            <div class="row mt-3  ">
                <div class="col-md-4">
                    <label for="cpf" class="form-label">CPF</label>
                    <input type="text" class="form-control" id="cpf" @input="formatCpf" maxlength="14" required
                        placeholder="xxx.xxx.xxx-xx" v-model="cpf">
                    <input type="text" class="form-control" id="cpf" @input="formatCpf" maxlength="14" required
                        placeholder="xxx.xxx.xxx-xx" v-model="cpf">
                </div>
                <div class="col-md-8">
                <div class="col-md-8">
                    <label for="inputEmail4" class="form-label">Email</label>
                    <input type="email" class="form-control" maxlength="150" id="inputEmail4" v-model="email"
                        placeholder="E-mail">
                    <input type="email" class="form-control" maxlength="150" id="inputEmail4" v-model="email"
                        placeholder="E-mail">
                </div>
                <div class="col-md-4">
                <div class="col-md-4">
                    <label for="cel" class="form-label">Celular</label>
                    <input type="tel" class="form-control" id="cel" maxlength="15" required
                        placeholder="(xx) xxxxx-xxxx" v-model="phone" @input="formatPhone">
                </div>
                <div class="col-md-8">
                    <label for="inputFunction"> Função </label>
                    <input type="text" class="form-control" id="inputFuntion" v-model="functionWorker" required>
                </div>
            </div>
            <div class="row mt-3">

            </div>
            <div class="row mt-3">
                <div class="col-12">
                    <h4>Endereço</h4>
                </div>
            </div>
            <div class="row mt-3">
            <div class="row mt-3">
                <div class="col-md-4">
                    <label for="cep" class="form-label">CEP</label>
                    <input type="text" class="form-control" id="cep" required placeholder="xxxxx-xxx" v-model="cep"
                        maxlength="9" @input="formatCep">
                    <input type="text" class="form-control" id="cep" required placeholder="xxxxx-xxx" v-model="cep"
                        maxlength="9" @input="formatCep">
                </div>
                <div class="col-md-8">
                    <label for="street" class="form-label">Rua</label>
                    <input type="text" class="form-control" id="inputStreet" v-model="street" required>
                    <input type="text" class="form-control" id="inputStreet" v-model="street" required>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-4">
                    <label for="inputState" class="form-label">Número</label>
                    <input type="text" class="form-control" id="inputZip" v-model="numberStreet">
                    <input type="text" class="form-control" id="inputZip" v-model="numberStreet">
                </div>
                <div class="col-md-8">
                    <label for="inputZip" class="form-label">Complemento</label>
                    <input type="text" class="form-control" id="inputZip" v-model="complement">
                    <input type="text" class="form-control" id="inputZip" v-model="complement">
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-4">
                    <label for="inputCity" class="form-label">Estado</label>
                    <input type="text" class="form-control" id="inputCity" v-model="state" required>
                    <input type="text" class="form-control" id="inputCity" v-model="state" required>
                </div>
                <div class="col-md-6">
                <div class="col-md-6">
                    <label for="inputState" class="form-label">Cidade</label>
                    <input type="text" class="form-control" id="inputState" v-model="city" required>
                    <input type="text" class="form-control" id="inputState" v-model="city" required>
                </div>

                <div class="col-md-2">

                <div class="col-md-2">
                    <label for="inputZip" class="form-label">Bairro</label>
                    <input type="text" class="form-control" id="inputZip" v-model="neighborhood" required>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-4">
                    <label for="inputUserName" class="form-label">Usuário</label>
                    <input type="text" class="form-control" id="inputUserName" v-model="userName" required>
                    <label for="inputUserName" class="form-label">Usuário</label>
                    <input type="text" class="form-control" id="inputUserName" v-model="userName" required>
                </div>
                <div class="col-md-4">
                    <label for="inputUserName" class="form-label">Senha</label>
                    <input type="text" class="form-control" id="inputUserName" v-model="password" required>
                    <label for="inputUserName" class="form-label">Senha</label>
                    <input type="text" class="form-control" id="inputUserName" v-model="password" required>
                </div>
                <div class="col-md-4">
                    <label for="inputZip" class="form-label">Nível de Usuário</label>
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Escolha o Nível</option>
                        <option value="1">Administrador</option>
                        <option value="2">Funcionário</option>
                    </select>
                </div>
            </div>

            <div class="row mt-3">
            <div class="row mt-3">
                <div class="col-12 d-flex justify-content-end mt-4">
                    <button type="submit" class="btn btn-primary me-2">Salvar</button>
                    <button type="submit" class="btn btn-primary me-2">Salvar</button>
                    <a @click="$router.go(-1)" class="btn btn-secondary">Cancelar</a>
                </div>
            </div>
        </form>
    </section>
    <ModalWarning :modalText="modalMessage" id="modal" />
    <ModalWarning :modalText="modalMessage" id="modal" />
</template>
