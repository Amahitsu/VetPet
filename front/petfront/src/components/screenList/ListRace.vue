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

    <div class="d-flex justify-content-between mt-3">
       <div class="position-relative">
           <input type="text" v-model="filter.name" placeholder="Filtrar por raça ou espécie" class="form-control input-with-icon" />
       </div>
    </div>

    <table class="table mt-3">
        <thead>
            <tr>
                <th>Raça</th>
                <th>Espécie</th>
                <th width="96">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="race in filteredBreeds" :key="race.id">
                <td>{{ race.name }}</td>
                <td>{{ race.specie.name }}</td>
                <td class="btn-group text-end">
                    <button class="btn btn-icon btn-sm btn-primary me-1" data-toggle="tooltip" data-placement="top"
                        title="Editar" @click="updateRace(race.id)">
                        <span class="material-symbols-rounded">edit</span>
                    </button>
                    <button class="btn btn-icon btn-sm btn-danger" data-toggle="tooltip" data-placement="top"
                        title="Excluir" @click="confirmDelete(race.id)">
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

    <div class="modal fade" id="errorDeleteRace" tabindex="-1" aria-labelledby="deleteRaceModalLabel"
        aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="deleteRaceModalLabel">Erro</h5>
                    <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Raça não pode ser excluída pois está associada a um animal.
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
            filter: {
                name: ''
            },
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
                    $('#errorDeleteRace').modal('show');
                    $('#deleteRaceModal').modal('hide');
                    setTimeout(function () {
                        $('#errorDeleteRace').modal('hide');
                    }, 3000);
                });
        },
        closeDeleteModal() {
            $('#deleteRaceModal').modal('hide');
        }
    },
    computed: {
        filteredBreeds() {
           return this.breeds.filter(race => {
               const filterText = this.filter.name.toLowerCase();
               const matchesName = race.name.toLowerCase().includes(filterText);
               const matchesSpecie = race.specie.name.toLowerCase().includes(filterText);
              return matchesName || matchesSpecie;
           });
        }
   }
}
</script>

<style>
.input-with-icon {
    padding-left: 30px; 
    background: url('@/assets/lupa.png') no-repeat 8px center;
    background-size: 16px 16px;
}
</style>