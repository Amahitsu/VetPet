<template>
    <section>
        <form>
            <h2>Cadastro de Funcionário</h2>
            <div class="row mt-3">
                <div class="col-md-6">
                    <label for="inputName" class="form-label">Nome Completo</label>
                    <input type="text" class="form-control" placeholder="Digite Nome Completo"
                        aria-label="Nome Completo">
                </div>
                <div class="col-md-6 d-flex align-items-center">
                    <div class="form-check me-3">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" checked>
                        <label class="form-check-label" for="flexCheckDefault">
                            Ativo
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                        <label class="form-check-label" for="flexCheckChecked">
                            Inativo
                        </label>
                    </div>
                </div>
            </div>

            <div class="row mt-3">
                <div class="col-md-3">
                    <label for="cpf" class="form-label">CPF</label>
                    <input type="text" class="form-control" id="cpf" required placeholder="xxx.xxx.xxx-xx">
                </div>
                <div class="col-md-3">
                    <label for="dateInput" class="form-label">Data de Nascimento</label>
                    <input type="date" class="form-control" id="dateInput">
                </div>
                <div class="col-md-3">
                    <label for="inputEmail4" class="form-label">Email</label>
                    <input type="email" class="form-control" id="inputEmail4">
                </div>
                <div class="col-md-3">
                    <label for="cel" class="form-label">Celular</label>
                    <input type="text" class="form-control" id="cel" required placeholder="(xx)xxxxx-xxxx">
                </div>
            </div>

            <div class="row mt-4">
                <div class="col-12">
                    <h4>Endereço</h4>
                </div>
            </div>


            <div class="row">
                <div class="col-md-4">
                    <label for="cep" class="form-label">CEP</label>
                    <input type="text" class="form-control" id="cep" required placeholder="xxxxx-xxx">
                </div>
                <div class="col-md-8">
                    <label for="street" class="form-label">Rua</label>
                    <input type="text" class="form-control" id="street">
                </div>
            </div>

            <div class="row mt-3">
                <div class="col-md-4">
                    <label for="inputState" class="form-label">Número</label>
                    <input type="text" class="form-control" id="number">
                </div>
                <div class="col-md-8">
                    <label for="inputZip" class="form-label">Complemento</label>
                    <input type="text" class="form-control" id="complement">
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-4">
                    <label for="inputCity" class="form-label">Estado</label>
                    <input type="text" class="form-control" id="state">
                </div>
                <div class="col-md-4">
                    <label for="inputState" class="form-label">Cidade</label>
                    <input type="text" class="form-control" id="city">
                </div>
                <div class="col-md-4">
                    <label for="inputZip" class="form-label">Bairro</label>
                    <input type="text" class="form-control" id="district">
                </div>
            </div>

            <div class="row mt-3">
                <div class="col-md-4">
                    <label for="inputUser" class="form-label">Nome de Usuário</label>
                    <input type="text" class="form-control" id="inputUser">
                </div>
                <div class="col-md-4">
                    <label for="inputPassword" class="form-label">Senha</label>
                    <input type="text" class="form-control" id="inputPassword">
                </div>
                <div class="col-md-4">
                    <label for="inputZip" class="form-label">Nível de Usuário</label>
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Escolha o Nível</option>
                        <option value="1">Administrador</option>
                        <option value="2">Funcionário</option>
                    </select>
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
