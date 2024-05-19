<script setup>
</script>

<template>
    <div class="modal fade" id="modalSpecie" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Cadastro de Espécie</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <label for="inputName" class="form-label">Nome da Espécie</label>
                            <input type="text" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary me-2">Salvar</button>
                    <button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Cancelar</button>
                </div>
            </div>
        </div>
    </div>
</template>

<style></style>