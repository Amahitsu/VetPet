<script setup>
import axios from 'axios';

</script>

<template>
    <div class="modal fade" id="modalRace" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Cadastro de Raça</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <label for="inputName" class="form-label">Nome da Raça</label>
                            <input type="text" class="form-control" v-model="raceName">
                        </div>
                        <div class="col-md-6">
                            <label for="inputSpecies" class="form-label">Espécie</label>
                            <select class="form-select" aria-label="Default select example" v-model="selectedSpecieId">
                                <option value="" selected>Selecione</option>
                                <option v-for="species in speciesList" :key="species.id" :value="species.id">{{ species.name }}</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary me-2" @click="saveRace">Salvar</button>
                    <button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Cancelar</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            raceName: '',
            selectedSpecieId: '',
            speciesList: [],
        };
    },
    created() {
        this.loadSpecies();
    },
    methods: {
        loadSpecies() {
            axios.get("http://localhost:8080/api/v1/species")
                .then(response => {
                    this.speciesList = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar as espécies:', error);
                });
        },
        saveRace() {
            axios.post("http://localhost:8080/api/v1/races", {
                name: this.raceName,
                id_specie: this.selectedSpecieId
            })
            .then(response => {
                console.log('Raça criada com sucesso:', response.data);
            })
            .catch(error => {
                console.error('Erro ao criar raça:', error);
            });
        }
    }
}
</script>

<style></style>
