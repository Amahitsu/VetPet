<template>
    <section>
        <form>
            <h2 v-if="!animalId">Cadastro de Pet</h2>
            <h2 v-if="animalId">Edição de Pet</h2>
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
                        <option value="macho">Macho</option>
                        <option value="fêmea">Fêmea</option>
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
                    <select class="form-select" v-model="selectedSpecieId" @change="loadBreeds" id="speciesSelect">
                        <option value="" disabled>Selecione</option>
                        <option v-for="species in speciesList" :key="species.id" :value="species.id">{{ species.name }}
                        </option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label for="breedSelect" class="form-label">Raça</label>
                    <select class="form-select" v-model="selectedRaceId" :disabled="!selectedSpecieId" id="breedSelect">
                        <option value="" disabled>Selecione</option>
                        <option v-for="breed in breedList" :key="breed.id" :value="breed.id">{{ breed.name }}</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex justify-content-end mt-4">
                    <button type="button" class="btn btn-secondary me-2" @click="$router.go(-1)">Cancelar</button>
                    <button type="button" class="btn btn-primary" @click="saveAnimal">Salvar</button>
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
            animalId: null,
            speciesList: [],
            breedList: [],
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
        this.customerId = this.$route.params.customerId;
        this.animalId = this.$route.params.animalId;

        if (this.customerId)
            this.loadCustomerById(this.customerId);

        if (this.animalId)
            this.loadAnimal(this.animalId);

        this.loadSpecies();
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
            if (this.selectedSpecieId) {
                fetch(`http://localhost:8080/api/v1/breeds?id_specie=${this.selectedSpecieId}`)
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
        saveAnimal() {
            const data = {
                name: this.animalName,
                gender: this.animalGender,
                dateOfBirth: this.animalBirthdate,
                weight: this.animalWeight,
                id_specie: this.selectedSpecieId,
                id_breeds: this.selectedRaceId,
                id_customers: this.customer.id
            };
            if (this.animalId)
                this.editAnimal(this.animalId, data)
            else
                this.addAnimal(data)
        },

        addAnimal(data) {
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
                    this.goToList();
                })
                .catch(error => {
                    console.error('Erro ao criar animal:', error);
                });
        },
        editAnimal(animalId, data) {
            if (!animalId)
                return;

            axios.put(`http://localhost:8080/api/v1/animals/${animalId}`, data)
                .then(response => {
                    console.log('Animal editado com sucesso');
                    this.goToList();
                })
                .catch(error => {
                    console.error('Erro ao editar animal:', error);
                });
        },
        goToList() {
            this.$router.push({ path: `/clientes` });
        },
        loadAnimal(animalId) {
            if (!animalId)
                return;

            axios.get(`http://localhost:8080/api/v1/animals/${animalId}`)
                .then(response => {
                    let data = response.data.data;

                    this.animalName = data.name;
                    this.animalGender = data.gender;
                    this.animalWeight = data.weight;
                    this.animalBirthdate = new Date(data.dateOfBirth).toISOString().split('T')[0];
                    this.customer.id = data.customer.id;
                    this.selectedSpecieId = data.race.specie.id;
                    this.selectedRaceId = data.race.id;

                    this.loadBreeds();
                    this.loadCustomerById(this.customer.id);
                })
                .catch(error => {
                    console.error('Erro ao criar cliente:', error);
                });
        },
    }
};
</script>

<style></style>