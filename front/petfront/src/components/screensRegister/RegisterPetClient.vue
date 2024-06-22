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
            </div>

            <div class="row mt-3">
                <div class="col-md-6">
                    <label for="inputPetName" class="form-label">Nome do Pet</label>
                    <input type="text" class="form-control" v-model="animalName" id="inputPetName">
                </div>
                <div class="col-md-2">
                    <label for="inputGender" class="form-label">Sexo</label>
                    <select class="form-select" v-model="animalGender" id="inputGender">
                        <option value="" disabled>Selecione</option>
                        <option value="Macho">Macho</option>
                        <option value="Fêmea">Fêmea</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <label for="dateInput" class="form-label">Data de Nascimento</label>
                    <input type="date" class="form-control" id="dateInput" v-model="animalBirthdate">
                    </div>

                <div class="col-md-2">
                    <label>Peso do Pet</label>
                    <input class="form-control" v-model="animalWeight" id="inputWeight">
                </div>
            </div>

            <div class="row mt-3">
                <div class="col-md-3">
                    <label for="speciesSelect" class="form-label">Espécie</label>
                    <select class="form-select" v-model="selectedSpecie" @change="loadBreeds" id="speciesSelect">
                        <option value="" disabled>Selecione</option>
                        <option v-for="species in speciesList" :key="species.id" :value="species.id">{{ species.name }}
                        </option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="breedSelect" class="form-label">Raça</label>
                    <select class="form-select" v-model="selectedRaceId" :disabled="!selectedSpecie" id="breedSelect">
                        <option value="" disabled>Selecione</option>
                        <option v-for="breed in breedList" :key="breed.id" :value="breed.id">{{ breed.name }}</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex justify-content-end mt-4">
                    <button type="button" @click="$router.go(-1)" class="btn btn-secondary me-2">Cancelar</button>
                    <button type="button" class="btn btn-primary" @click="addAnimal">Salvar</button>
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
            selectedSpecieId: '',
            selectedRaceId: '',
            customer: {},
            animalName: '',
            animalGender: '',
            animalBirthdate: '',
            animalWeight: ''
        };
    },
    created() {
        this.loadSpecies();
        this.loadCustomerById(this.$route.params.customerId);
    },

    methods: {
        loadSpecies() {
            fetch('http://localhost:8080/api/v1/species')
                .then(response => response.json())
                .then(({ data }) => {
                    console.log(data)
                    this.speciesList = data;
                })
                .catch(error => console.error('Erro ao carregar espécies:', error));
        },
        loadBreeds() {
            if (this.selectedSpecie) {
                fetch(`http://localhost:8080/api/v1/breeds?id_specie=${this.selectedSpecie}`)
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
        addAnimal() {
    axios.post("http://localhost:8080/api/v1/animals", {
        name: this.animalName,
        gender: this.animalGender,
        dateOfBirth: this.animalBirthdate,
        weight: this.animalWeight,
        id_specie: this.selectedSpecieId,
        id_breeds: this.selectedRaceId,
        id_customers: this.customer.id
    })
    .then(response => {
        console.log('Animal criado com sucesso:', response.data);
        this.$router.go(-1);
    })
    .catch(error => {
        console.error('Erro ao criar animal:', error);
    });
},
        handleSubmit() {
            console.log('Formulário enviado');
        }
    }
};
</script>

<style>
</style>