<template>
    <RegisterSpecie />
    <div class="d-flex justify-content-between">
        <h2>Espécies</h2>
        <div class="d-flex align-items-center">
            <button type="button" class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#modalSpecie">
                Adicionar espécie
            </button>
        </div>
    </div>


    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Espécie</th>
                <th width="150">Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="specie in species" :key="specie.id">
                <td>{{ specie.id }}</td>
                <td>{{ specie.name }}</td>
                <td>
                    <button class="btn btn-sm btn-primary">Editar</button>
                    <button class="btn btn-sm btn-danger" @click="confirmDelete(specie.id)">Deletar</button>
                </td>
            </tr>
        </tbody>
    </table>

    <div class="modal fade" id="deleteConfirmationModal" tabindex="-1" aria-labelledby="deleteConfirmationModalLabel"
        aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteConfirmationModalLabel">Confirmar Exclusão</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>Tem certeza que deseja excluir esta espécie?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-danger" @click="deleteSpecie(id)">Confirmar</button>
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
                    console.log(response.data.data)
                    this.species = response.data.data;
                })
                .catch(error => {
                    console.error('Erro ao listar as espécies:', error);
                });
        },
        confirmDelete(id) {
            this.selectedSpecie = id;
            $('#deleteConfirmationModal').modal('show');
        },
        deleteSpecie() {
            const id = this.selectedSpecie;
            axios.delete(`http://localhost:8080/api/v1/species/${id}`)
                .then(response => {
                    console.log('Espécie excluída com sucesso:', response.data);
                    $('#deleteConfirmationModal').modal('hide');
                    this.loadSpecies(); // Atualiza a lista de espécies após a exclusão
                })
                .catch(error => {
                    console.error('Erro ao excluir espécie:', error);
                });
        }
    }
}
</script>

<style></style>