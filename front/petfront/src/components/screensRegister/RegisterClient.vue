<template>
    <section class="vh-70 gradient-custom d-flex align-items-center">
        <div class="row justify-content-center align-items-center" style="min-height: 100vh;">

            <form class="row g-3">
                <h1>Cadastro de Cliente</h1>


                <div class="row g-4">
                    <div class="col-md-6">
                        <label for="inputName" class="form-label">Nome Completo</label>
                        <input type="text" class="form-control" placeholder="Digite Nome Completo"
                            aria-label="Nome Completo">
                    </div>
                    <div class="col-md-2">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" checked>
                        <label class="form-check-label" for="flexCheckDefault">
                            Ativo
                        </label>
                    </div>
                    <div class="col-md-2">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                        <label class="form-check-label" for="flexCheckChecked">
                            Inativo
                        </label>
                    </div>
                </div>

                <div class="col-md-5">
                    <label for="cpf" class="form-label">CPF</label>
                    <input type="cpf" class="form-control" id="cpf" required placeholder="xxx.xxx.xxx-xx">
                </div>
                <div class="col-md-4">
                    <label for="dateInput" class="form-label">Data de Nascimento</label>
                    <input type="date" class="form-control" id="dateInput">
                </div>
                <div class="col-md-5">
                    <label for="inputEmail4" class="form-label">Email</label>
                    <input type="email" class="form-control" id="inputEmail4">
                </div>
                <div class="col-md-4">
                    <label for="cel" class="form-label">Celular</label>
                    <input type="cel" class="form-control" id="cel" required placeholder="(xx)xxxxx-xxxx">
                </div>

                <div class="col-12">
                    <label for="inputAddress" class="form-label">Endereço</label>
                </div>

                <div class="col-md-4">
                    <label for="cep" class="form-label">CEP</label>
                    <input type="cep" class="form-control" id="cpf" required placeholder="xxxxx-xxx">
                </div>
                <div class="col-md-6">
                    <label for="street" class="form-label">Rua</label>
                    <input type="street" class="form-control" id="inputStreet">
                </div>
                <div class="col-md-4">
                    <label for="inputState" class="form-label">Número</label>
                    <input type="text" class="form-control" id="inputZip">
                </div>
                <div class="col-md-6">
                    <label for="inputZip" class="form-label">Complemento</label>
                    <input type="text" class="form-control" id="inputZip">
                </div>
                <div class="col-md-4">
                    <label for="inputCity" class="form-label">Estado</label>
                    <input type="text" class="form-control" id="inputCity">
                </div>
                <div class="col-md-4">
                    <label for="inputState" class="form-label">Cidade</label>
                    <input type="text" class="form-control" id="inputZip">
                </div>
                <div class="col-md-2">
                    <label for="inputZip" class="form-label">Bairro</label>
                    <input type="text" class="form-control" id="inputZip">
                </div>

                <div class="col-md-4">
                    <div class="row g-3">

                        <div class="col-md-2">
                            <button type="submit" class="btn btn-primary">Salvar</button>
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-primary">Cancelar</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </section>
</template>
