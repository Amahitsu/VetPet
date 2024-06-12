<template>
    <RegisterRace ref="modalRace" @reloadBreeds="loadBreeds" />
    <div class="d-flex justify-content-between">
        <h2>Raças</h2>
        <div class="d-flex align-items-center">
            <button type="button" class="btn btn-m btn-primary" @click="addRace()">
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
                <th width="96">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="race in breeds" :key="race.id">
                <td>{{ race.id }}</td>
                <td>{{ race.name }}</td>
                <td>{{ race.specie.name }}</td>
                <td class="text-end">
                    <button class="btn btn-icon btn-sm btn-primary me-1" data-toggle="tooltip" data-placement="top" title="Editar" @click="updateRace(race.id)">
                        <span class="material-symbols-rounded">edit</span>
                    </button>
                    <button class="btn btn-icon btn-sm btn-danger" data-toggle="tooltip" data-placement="top" title="Excluir" @click="confirmDelete(race.id)">
                        <span class="material-symbols-rounded">delete</span>
                    </button>
                </td>
            </tr>
        </tbody>
    </table>

    <div class="modal fade" id="deleteRaceModal" tabindex="-1" role="dialog" aria-labelledby="deleteRaceModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteRaceModalLabel">Confirmar Exclusão</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"
                        @click="closeDeleteModal"></button>
                </div>
                <div class="modal-body">
                    Tem certeza que deseja excluir esta raça?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" @click="closeDeleteModal">Cancelar</button>
                    <button type="button" class="btn btn-danger" @click="deleteRace">Confirmar</button>
                </div>
            </div>
        </div>
    </div>

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
        addRace() {
            this.raceId = null;
            this.openModal();
        },
        updateRace(raceId) {
            this.raceId = raceId;
            this.openModal(raceId);
        },
        openModal(raceId) {
            let modal = bootstrap.Modal.getOrCreateInstance(document.getElementById("modalRace"))

            if (raceId) {
                this.$refs.modalRace.loadRace(raceId);
                this.$refs.modalRace.raceId = raceId;
            }

            modal.show();
        },
        confirmDelete(raceId) {
            this.raceIdToDelete = raceId;
            $('#deleteRaceModal').modal('show');
        },
        deleteRace() {
            let raceId = this.raceIdToDelete;

            axios.delete(`http://localhost:8080/api/v1/breeds/${raceId}`)
                .then(response => {
                    console.log('Raça excluída com sucesso:', response.data);
                    this.closeDeleteModal();
                    this.loadBreeds();
                })
                .catch(error => {
                    console.error('Erro ao excluir raça:', error);
                });
        },
        closeDeleteModal() {
            $('#deleteRaceModal').modal('hide');
        }
    }
}
</script>

<style></style>