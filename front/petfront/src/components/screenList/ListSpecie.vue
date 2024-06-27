<template>
    <RegisterSpecie ref="modalSpecie" @reloadSpecies="loadSpecies" />
    <div class="d-flex justify-content-between">
        <h2>Espécies</h2>
        <div class="d-flex align-items-center">
            <button type="button" class="btn btn-m btn-primary" @click="addSpecie">
                Adicionar espécie
            </button>
        </div>
    </div>

    <div class="d-flex justify-content-between mt-3">
       <div class="position-relative">
           <input type="text" v-model="filter.name" placeholder="Filtrar por espécie" class="form-control input-with-icon" />
       </div>
    </div>

    <table class="table mt-3">
        <thead>
            <tr>
                <th>Espécie</th>
                <th width="96">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="specie in filteredSpecies" :key="specie.id">
                <td>{{ specie.name }}</td>
                <td class="btn-group text-end">
                    <button class="btn btn-icon btn-sm btn-primary me-1" @click="updateSpecie(specie.id)">
                        <span class="material-symbols-rounded">edit</span>
                    </button>
                    <button class="btn btn-icon btn-sm btn-danger" @click="confirmDelete(specie.id)">
                        <span class="material-symbols-rounded">delete</span>
                    </button>
                </td>
            </tr>
        </tbody>
    </table>

    <div class="modal fade" id="deleteSpecieModal" tabindex="-1" aria-labelledby="deleteSpecieModalLabel"
        aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteSpecieModalLabel">Confirmar Exclusão</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"
                        @click="closeDeleteModal"></button>
                </div>
                <div class="modal-body">
                    Tem certeza que deseja excluir esta espécie?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" @click="closeDeleteModal">Cancelar</button>
                    <button type="button" class="btn btn-danger" @click="deleteSpecie">Confirmar</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="errorDeleteSpecie" tabindex="-1" aria-labelledby="deleteSpecieModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="deleteSpecieModalLabel">Erro</h5>
                    <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Espécie não pode ser excluída pois está associada a uma raça.
                </div>
            </div>
        </div>
    </div>

</template>

<script>
import axios from 'axios';
import RegisterSpecie from '../screensRegister/RegisterSpecie.vue';

export default {
    components: {
        RegisterSpecie
    },
    data() {
        return {
            species: [],
            filter: {
                name: ''
            },
            editedSpecie: {
                id: null,
                name: ''
            }
        };
    },
    created() {
        this.loadSpecies();
    },
    methods: {
        loadSpecies() {
            axios({
                method: "GET",
                url: "http://localhost:8080/api/v1/species",
            })
                .then((response) => {
                    this.species = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar as espécies:', error);
                });
        },
        addSpecie() {
            this.specieId = null;
            this.openModal();
        },
        updateSpecie(specieId) {
            this.specieId = specieId;
            this.openModal(specieId);
        },
        openModal(specieId) {
            let modal = bootstrap.Modal.getOrCreateInstance(document.getElementById("modalSpecie"))

            if (specieId) {
                this.$refs.modalSpecie.loadSpecie(specieId);
                this.$refs.modalSpecie.specieId = specieId;
            }

            modal.show();
        },
        confirmDelete(specieId) {
            this.selectedSpecie = specieId;
            $('#deleteSpecieModal').modal('show');
        },
        deleteSpecie() {
            let specieId = this.selectedSpecie;

            axios.delete(`http://localhost:8080/api/v1/species/${specieId}`)
                .then(response => {
                    console.log('Espécie excluída com sucesso:', response.data);
                    this.closeDeleteModal();
                    this.loadSpecies();
                })
                .catch(error => {
                    $('#errorDeleteSpecie').modal('show');
                    $('#deleteSpecieModal').modal('hide');
                    setTimeout(function(){
                $('#errorDeleteSpecie').modal('hide');
            }, 3000);
                });
        },
        closeDeleteModal() {
            $('#deleteSpecieModal').modal('hide');
        }
    },
    computed: {
        filteredSpecies() {
            return this.species.filter(specie => {
                const matchesName = specie.name.toLowerCase().includes(this.filter.name.toLowerCase());
                return matchesName;
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