<script setup>
</script>

<template>
    <section>
        <form @submit.prevent="handleSubmit">
            <h1>Cadastro de Espécie</h1>

            <div class="row mt-3">
                <div class="col-md-12">
                    <label for="inputName" class="form-label">Nome da Espécie</label>
                    <input type="text" class="form-control">
                </div>
            </div>

            
            <div class="row">
                <div class="col-12 d-flex justify-content-end mt-4">
                    <button type="submit" class="btn btn-primary me-2">Salvar</button>
                    <button type="button" class="btn btn-secondary">Cancelar</button>
                </div>
            </div>
        </form>
    </section>
</template>

<style></style>