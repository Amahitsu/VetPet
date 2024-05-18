<template>
    <section class="vh-70 gradient-custom d-flex align-items-center">
        <div class="container">
            <div class="row justify-content-center align-items-center" style="min-height: 100vh;">
                <form class="row g-3" @submit.prevent="handleSubmit">
                    <h1>Cadastro de Pet</h1>
                    <div class="row g-4 align-items-center">
                        <div class="col-md-6">
                            <label for="inputName" class="form-label">Nome do Tutor (Cliente)</label>
                            <input type="text" class="form-control" placeholder="Digite Nome Completo" aria-label="Nome Completo">
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

                    <div class="col-md-2">
                        <label for="speciesSelect" class="form-label">Espécie</label>
                        <select class="form-select" v-model="selectedSpecie" @change="loadBreeds">
                            <option value="" disabled>Selecione</option>
                            <option v-for="species in speciesList" :key="species.id" :value="species.id">{{ species.name }}</option>
                        </select>
                    </div> 

                    <div class="col-md-2">
                        <label for="breedSelect" class="form-label">Raça</label>
                        <select class="form-select" v-model="selectedBreed" :disabled="!selectedSpecie">
                            <option value="" disabled>Selecione</option>
                            <option v-for="breed in breedList" :key="breed.id" :value="breed.id">{{ breed.name }}</option>
                        </select> 
                    </div> 

                    <div class="col-12 d-flex justify-content-end mt-4">
                        <button type="submit" class="btn btn-primary me-2">Salvar</button>
                        <button type="button" class="btn btn-secondary">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
</template>

<script>
export default {
    data() {
        return {
            speciesList: [],
            breedList: [],
            selectedSpecie: '',
            selectedBreed: '',
        };
    },
    created() {
        this.loadSpecies();
    },
    methods: {
        loadSpecies() {
            fetch('http://localhost:8080/api/v1/species') // Substitua com a URL real da sua API
                .then(response => response.json())
                .then(({data}) => {
                    console.log(data)
                    this.speciesList = data;
                })
                .catch(error => console.error('Erro ao carregar espécies:', error));
        },
        loadBreeds() {
            if (this.selectedSpecie) {
                fetch(`http://localhost:8080/api/v1/races?id_specie=${this.selectedSpecie}`) // Substitua com a URL real da sua API
                    .then(response => response.json())
                    .then(({data}) => {
                        console.log(data);
                        this.breedList = data;
                    })
                    .catch(error => console.error('Erro ao carregar raças:', error));
            } else {
                this.breedList = [];
            }
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
