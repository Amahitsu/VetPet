<template>
    <section>
        <form>
            <h2>Cadastro de Serviço</h2>
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
                    <a class="btn btn-primary me-2">Salvar</a>
                    <a @click="$router.go(-1)" class="btn btn-secondary">Cancelar</a>
                </div>
            </div>
        </form>
    </section>
</template>
