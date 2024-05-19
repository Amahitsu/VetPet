<template>
    <section>
        <form>
            <h1>Cadastro de Atendimento</h1>
            <div class="row mt-3">
                <div class="col-md-4">
                    <label for="cliente">Cliente</label>
                    <input type="text" class="form-control" id="cliente" placeholder="Nome do cliente">
                </div>

                <div class="col-md-4">
                    <label for="petNome">Pet (Nome)</label>
                    <input type="text" class="form-control" id="petNome" placeholder="Nome do pet">
                </div>

                <div class="col-md-4">
                    <label for="raca">Raça</label>
                    <input type="text" class="form-control" id="raca" placeholder="Raça do pet">
                </div>
            </div>

            <div class="row mt-3">
                <div class="col-md-4">
                    <label for="servico">Serviço</label>
                    <select class="form-control" id="servico">
                        <option>Consulta</option>
                        <option>Banho</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="preco">Preço do Serviço</label>
                    <input type="text" class="form-control" id="preco" placeholder="R$">
                </div>
            </div>

            <div class="row mt-3">
                <div class="col-md-4">
                    <label for="dataAgendada">Data agendada</label>
                    <input type="date" class="form-control" id="dataAgendada">
                </div>
                <div class="col-md-4">
                    <label for="horarioAgendado">Horário agendado</label>
                    <input type="time" class="form-control" id="horarioAgendado">
                </div>
                <div class="col-md-4">
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="vacinas">
                        <label class="form-check-label" for="vacinas">Vacinas</label>
                    </div>
                    <div class="form-check mt-2">
                        <input type="checkbox" class="form-check-input" id="medicamentos">
                        <label class="form-check-label" for="medicamentos">Medicamentos</label>
                    </div>
                </div>

            </div>
            <div class="row mt-3">
                <div class="col-md-12">
                    <label for="anotacoes">Anotações do serviço</label>
                    <textarea class="form-control" id="anotacoes" rows="3"></textarea>
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
