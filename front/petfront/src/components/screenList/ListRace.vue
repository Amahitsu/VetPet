<template>
    <RegisterRace :id="raceId" @reloadBreeds="loadBreeds" />
    <div class="d-flex justify-content-between">
        <h2>Raças</h2>
        <div class="d-flex align-items-center">
            <button type="button" class="btn btn-sm btn-primary" @click="addRace()">
                Adicionar raça
            </button>
        </div>
    </div>

    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Raça</th>
                <th>Espécie</th>
                <th width="150">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="race in breeds" :key="race.id">
                <td>{{ race.id }}</td>
                <td>{{ race.name }}</td>
                <td>{{ race.specie.name }}</td>
                <td>
                    <button class="btn btn-sm btn-primary" @click="updateRace(race.id)">Editar</button>
                    <button class="btn btn-sm btn-danger" @click="confirmDelete(race.id)">Deletar</button>
                </td>
            </tr>
        </tbody>
        <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel">Confirmar Exclusão</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Tem certeza que deseja excluir esta raça?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-danger" @click="confirmDeletion">Deletar</button>
                    </div>
                </div>
            </div>
        </div>
    </table>

</template>

<script>
import axios from 'axios';
import RegisterRace from '../screensRegister/RegisterRace.vue';

export default {
    components: {
        RegisterRace
    },
    data() {
        return {
            raceId: null,
            breeds: [],
            speciesList: [],
            raceIdToDelete: null,
        };
    },
    created() {
        this.loadBreeds();
        this.loadSpecies();
    },
    methods: {
        loadBreeds() {
            axios({
                method: "GET",
                url: "http://localhost:8080/api/v1/breeds",
            })
                .then((response) => {
                    this.breeds = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar as raças:', error);
                });
        },
        loadSpecies() {
            fetch('http://localhost:8080/api/v1/species')
                .then(response => response.json())
                .then(({ data }) => {
                    this.speciesList = data;
                })
                .catch(error => console.error('Erro ao carregar espécies:', error));
        },
        addRace() {
            this.raceId = null;
            this.openModal();
        },
        updateRace(id) {
            this.raceId = id;
            this.openModal();
        },
        openModal() {
            let modal = bootstrap.Modal.getOrCreateInstance(document.getElementById("modalRace"))
            modal.show();
        },
        confirmDelete(id) {
            this.raceIdToDelete = id;
            $('#deleteModal').modal('show');
        },
        confirmDeletion() {
            this.deleteRace(this.raceIdToDelete);
            $('#deleteModal').modal('hide');
        },
        deleteRace(id) {
            axios.delete(`http://localhost:8080/api/v1/breeds/${id}`)
                .then(response => {
                    console.log('Raça excluída com sucesso:', response.data);
                    this.loadBreeds();
                })
                .catch(error => {
                    console.error('Erro ao excluir raça:', error);
                });
        }
    }
}
</script>

<style></style>