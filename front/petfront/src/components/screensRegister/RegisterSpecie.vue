<template>
    <div class="modal fade" id="modalSpecie" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" v-if="!specieId">Cadastro de Espécie</h1>
                    <h1 class="modal-title fs-5" v-if="specieId">Editar Espécie</h1>
                    <button type="button" class="btn-close" aria-label="Close" @click="closeModal"></button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <label for="inputName" class="form-label">Nome da Espécie</label>
                            <input type="text" class="form-control" v-model="specieName">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" @click="closeModal">Cancelar</button>
                    <button type="button" class="btn btn-primary me-2" @click="saveSpecie">Salvar</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            specieId: null,
            specieName: '',
        };
    },
    methods: {
        loadSpecie(specieId) {
            if(specieId == null) {
                this.reset();
                return;
            }

            axios.get(`http://localhost:8080/api/v1/species/${specieId}`)
                .then(response => {
                    let data = response.data.data;

                    this.specieName = data.name;
                })
                .catch(error => {
                    console.error('Erro ao buscar espécie:', error);
                });
        },
        saveSpecie() {
            if(this.specieId)
                this.editSpecie(this.specieId)
            else
                this.addSpecie()
        },
        addSpecie() {
            axios.post("http://localhost:8080/api/v1/species", {
                name: this.specieName
            })
                .then(response => {
                    this.closeModal();
                    this.$emit('reloadSpecies');
                    console.log('Espécie criada com sucesso:', response.data);
                })
                .catch(error => {
                    console.error('Erro ao criar espécie:', error);
                });
        },
        editSpecie(specieId) {
            axios.put(`http://localhost:8080/api/v1/species/${specieId}`, {
                id: specieId,
                name: this.specieName
            })
                .then(response => {
                    this.closeModal();
                    this.$emit('reloadSpecies');
                    console.log('Espécie editada com sucesso:', response.data);
                })
                .catch(error => {
                    console.error('Erro ao editar espécie:', error);
                });
        },
        closeModal(){
            let modal = bootstrap.Modal.getInstance(document.getElementById("modalSpecie"))
            modal.hide();
            this.reset();
        },
        reset() {
            this.specieId = null;
            this.specieName = '';
        }
    }
}
</script>

<style></style>