<script setup>
</script>

<template>
    <div class="container mt-5">
        <h2>Agendamento</h2>
        <form>
            <div class="mb-3 row">
                <label for="dateInput" class="col-sm-2 col-form-label">Data</label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" id="dateInput" required>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="startTimeInput" class="col-sm-2 col-form-label">Hora de Início</label>
                <div class="col-sm-4">
                    <input type="time" class="form-control" id="startTimeInput" required>
                </div>
                <label for="finishTimeInput" class="col-sm-2 col-form-label">Hora de Término</label>
                <div class="col-sm-4">
                    <input type="time" class="form-control" id="finishTimeInput" required>
                </div>
            </div>
            <div class="mb-3 row">
                <div class="col-sm-2">Medicamentos</div>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="medicinesCheckbox">
                        <label class="form-check-label" for="medicinesCheckbox">
                            Necessário
                        </label>
                    </div>
                </div>
            </div>
            <div class="mb-3 row">
                <div class="col-sm-2">Vacinas</div>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="vaccinesCheckbox">
                        <label class="form-check-label" for="vaccinesCheckbox">
                            Necessário
                        </label>
                    </div>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="animalSelect" class="col-sm-2 col-form-label">Animal</label>
                <div class="col-sm-10">
                    <select class="form-select" id="animalSelect" required>
                        <option selected disabled>Selecione o Animal</option>
                        <!-- Exemplo de opções, substitua com dados reais -->
                        <option value="1">Rex</option>
                        <option value="2">Bobby</option>
                    </select>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="workerSelect" class="col-sm-2 col-form-label">Funcionário</label>
                <div class="col-sm-10">
                    <select class="form-select" id="workerSelect" required>
                        <option selected disabled>Selecione o Funcionário</option>
                        <!-- Exemplo de opções, substitua com dados reais -->
                        <option value="1">Dr. Smith</option>
                        <option value="2">Dr. Jones</option>
                    </select>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="observationInput" class="col-sm-2 col-form-label">Observações</label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="observationInput" rows="3" required></textarea>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex justify-content-end">
                    <button type="submit" class="btn btn-primary me-2">Salvar</button>
                    <button type="reset" class="btn btn-secondary">Cancelar</button>
                </div>
            </div>
        </form>
    </div>
</template>

<style>
</style>