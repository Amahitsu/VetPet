
<template>
    <div class="modal fade" id="modalRace" tabindex="-1" aria-labelledby="modalRace" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="modalRace" v-if="!raceId">Cadastro de Raça</h1>
                    <h1 class="modal-title fs-5" id="modalRace" v-if="raceId">Editar Raça</h1>
                    <button type="button" class="btn-close" aria-label="Close" @click="closeModal"></button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <label for="inputName" class="form-label">Nome da Raça</label>
                            <input type="text" class="form-control" v-model="raceName">
                        </div>
                        <div class="col-md-6">
                            <label for="inputSpecies" class="form-label">Espécie</label>
                            <!-- <select class="form-select" aria-label="Default select example" v-model="selectedSpecieId">
                                <option value="" selected>Selecione</option>
                                <option v-for="species in speciesList" :key="species.id" :value="species.id">{{
                                species.name }}</option>
                            </select> -->
                            <v-select
                        label="title"
                        placeholder="Selecione"
                        v-model="selectedSpecieId"
                        :options="speciesList"
                        :reduce="specie => specie.id"
                    ></v-select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" @click="closeModal">Cancelar</button>
                    <button type="button" class="btn btn-primary me-2" @click="saveRace">Salvar</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import { listSpecies } from '@/services/species';

export default {
    emits: ['reloadBreeds'],
    data() {
        return {
            raceId: null,
            raceName: '',
            selectedSpecieId: '',
            speciesList: [],
        };
    },
    created() {
        this.loadSpecies();
    },
    methods: {
        transformToSelectItem(item) {
            return {
                id: item.id,
                title: item.name
            }
        },

        async loadSpecies() {
            const species = await listSpecies(this.selectedSpecieId)
            console.log('species', species)

            const speciesTransformed = species.map(this.transformToSelectItem)
            console.log('speciesTransformed', speciesTransformed)
            this.speciesList = speciesTransformed
        },
        loadRace(raceId) {
            if(raceId == null) {
                this.reset();
                return;
            }

            axios.get(`http://localhost:8080/api/v1/breeds/${raceId}`)
                .then(response => {
                    let data = response.data.data;

                    this.raceName = data.name;
                    this.selectedSpecieId = data.specie.id;
                })
                .catch(error => {
                    console.error('Erro ao buscar raça:', error);
                });
        },
        saveRace() {
            if(this.raceId)
                this.editRace(this.raceId)
            else
                this.addRace()
        },
        addRace() {
            axios.post("http://localhost:8080/api/v1/breeds", {
                name: this.raceName,
                id_specie: this.selectedSpecieId
            })
                .then(response => {
                    this.closeModal();
                    this.$emit('reloadBreeds');
                    console.log('Raça criada com sucesso:', response.data);
                })
                .catch(error => {
                    console.error('Erro ao criar raça:', error);
                });
        },
        editRace(raceId) {
            axios.put(`http://localhost:8080/api/v1/breeds/${raceId}`, {
                id: raceId,
                name: this.raceName,
                id_specie: this.selectedSpecieId
            })
                .then(response => {
                    this.closeModal();
                    this.$emit('reloadBreeds');
                    console.log('Raça editada com sucesso:', response.data);
                })
                .catch(error => {
                    console.error('Erro ao editar raça:', error);
                });
        },
        closeModal(){
            let modal = bootstrap.Modal.getInstance(document.getElementById("modalRace"))
            modal.hide();
            this.reset();
        },
        reset() {
            this.raceId = null;
            this.raceName = '';
            this.selectedSpecieId = '';
        }
    }
}
</script>

<style></style>
