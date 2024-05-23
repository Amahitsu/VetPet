<template>
    <section>
        <form @submit.prevent="handleSubmit">
            <h2>Cadastro de Pet</h2>
            <div class="row mt-3">
                <div class="col-md-6">
                    <label for="inputName" class="form-label">Nome do Tutor (Cliente)</label>
                    <input type="text" class="form-control" placeholder="Digite Nome Completo"
                        aria-label="Nome Completo" v-model="customer.name" disabled>
                </div>
                <div class="col-md-6 d-flex align-items-center">
                    <div class="form-check me-3">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" checked>
                        <label class="form-check-label" for="flexCheckDefault">
                            Ativo
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                        <label class="form-check-label" for="flexCheckChecked">
                            Inativo
                        </label>
                    </div>
                </div>
            </div>

            <div class="row mt-3">
                <div class="col-md-6">
                    <label for="inputName" class="form-label">Nome do Pet</label>
                    <input type="text" class="form-control">
                </div>
                <div class="col-md-2">
                    <label for="inputZip" class="form-label">Sexo</label>
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Selecione</option>
                        <option value="1">Macho</option>
                        <option value="2">Fêmea</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="dateInput" class="form-label">Data de Nascimento</label>
                    <input type="date" class="form-control" id="dateInput">
                </div>
            </div>

            <div class="row mt-3">
                <div class="col-md-3">
                    <label for="speciesSelect" class="form-label">Espécie</label>
                    <select class="form-select" v-model="selectedSpecie" @change="loadBreeds">
                        <option value="" disabled>Selecione</option>
                        <option v-for="species in speciesList" :key="species.id" :value="species.id">{{ species.name }}
                        </option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="breedSelect" class="form-label">Raça</label>
                    <select class="form-select" v-model="selectedBreed" :disabled="!selectedSpecie">
                        <option value="" disabled>Selecione</option>
                        <option v-for="breed in breedList" :key="breed.id" :value="breed.id">{{ breed.name }}</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex justify-content-end mt-4">
                    <a class="btn btn-primary me-2">Salvar</a>
                    <a @click="$router.go(-1)" class="btn btn-secondary">Cancelar</a>
                </div>
            </div>
        </form>
    </section>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            speciesList: [],
            breedList: [],
            selectedSpecie: '',
            selectedBreed: '',
            customer: {},
        };
    },
    created() {
        this.loadSpecies();
        this.loadCustomerById(this.$route.params.customerId);
    },

    methods: {
        loadSpecies() {
            fetch('http://localhost:8080/api/v1/species') // Substitua com a URL real da sua API
                .then(response => response.json())
                .then(({ data }) => {
                    console.log(data)
                    this.speciesList = data;
                })
                .catch(error => console.error('Erro ao carregar espécies:', error));
        },
        loadBreeds() {
            if (this.selectedSpecie) {
                fetch(`http://localhost:8080/api/v1/breeds?id_specie=${this.selectedSpecie}`) // Substitua com a URL real da sua API
                    .then(response => response.json())
                    .then(({ data }) => {
                        console.log(data);
                        this.breedList = data;
                    })
                    .catch(error => console.error('Erro ao carregar raças:', error));
            } else {
                this.breedList = [];
            }
        },
        loadCustomerById(id) {
            axios({
                method: "GET",
                url: `http://localhost:8080/api/v1/customers/${id}`,
            })
                .then((response) => {
                    console.log(response.data.data);
                    this.customer = response.data.data;
                })
                .catch(error => {
                    if (error.response.status === 404) {
                        alert('Cliente não encontrado');
                    }
                    console.error('Erro ao listar o cliente:', error);
                });
        },
        handleSubmit() {
            // Implementar lógica para enviar o formulário
            console.log('Formulário enviado');
        }
    }
};
</script>

<style>
/* Adicione seus estilos aqui */
</style>