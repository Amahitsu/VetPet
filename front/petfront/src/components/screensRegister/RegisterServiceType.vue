<template>
    <section>
        <form>
            <h1>Cadastro de Serviço</h1>
            <div class="row mt-3">
                <div class="col-md-8">
                    <label for="cliente">Nome do serviço</label>
                    <input type="text" class="form-control" id="cliente" placeholder="Nome do cliente">
                </div>

                <div class="col-md-4">
                    <label for="petNome">Valor do serviço</label>
                    <input type="text" class="form-control" id="petNome" placeholder="Nome do pet">
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
