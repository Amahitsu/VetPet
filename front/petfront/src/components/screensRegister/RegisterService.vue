<template>
    <section class="vh-70 gradient-custom d-flex align-items-center">
        <div class="container">
            <div class="row justify-content-center align-items-center" style="min-height: 100vh;">
                <form class="row g-3">
                    <h1>Cadastro de Serviço</h1>
                    <div class="form-group">
                        <label for="cliente">Cliente</label>
                        <input type="text" class="form-control" id="cliente" placeholder="Nome do cliente">
                    </div>

                    <div class="col-md-5">
                        <label for="petNome">Pet (Nome)</label>
                        <input type="text" class="form-control" id="petNome" placeholder="Nome do pet">
                    </div>
                    <div class="col-md-5">
                        <label for="raca">Raça</label>
                        <input type="text" class="form-control" id="raca" placeholder="Raça do pet">
                    </div>

                    
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
                    
                    <div class="form-row mt-3">
                        <div class="col-md-6">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="vacinas">
                                <label class="form-check-label" for="vacinas">Vacinas</label>
                            </div>
                            <div class="form-check mt-2">
                                <input type="checkbox" class="form-check-input" id="medicamentos">
                                <label class="form-check-label" for="medicamentos">Medicamentos</label>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <label for="dataAgendada">Data agendada</label>
                            <input type="date" class="form-control" id="dataAgendada">
                        </div>
                        <div class="col-md-3">
                            <label for="horarioAgendado">Horário agendado</label>
                            <input type="time" class="form-control" id="horarioAgendado">
                        </div>
                    </div>
                    <div class="form-group mt-4">
                        <label for="anotacoes">Anotações do serviço</label>
                        <textarea class="form-control" id="anotacoes" rows="3"></textarea>
                    </div>
                    <div class="form-group mt-3">
                        <button type="submit" class="btn btn-primary">Salvar</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
</template>
