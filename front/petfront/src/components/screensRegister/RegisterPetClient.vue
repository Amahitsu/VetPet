<template>
    <section class="vh-70 gradient-custom d-flex align-items-center">
        <div class="container">
            <div class="row justify-content-center align-items-center" style="min-height: 100vh;">
                <form class="row g-3">
                    <h1>Cadastro de Pet</h1>
                    <div class="row g-4 align-items-center">
                        <div class="col-md-6">
                            <label for="inputName" class="form-label">Nome do Tutor (Cliente)</label>
                            <input type="text" class="form-control" placeholder="Digite Nome Completo" aria-label="Nome Completo">
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

                    <div class="col-md-6">
                        <label for="inputName" class="form-label">Nome do Pet</label>
                        <input type="text" class="form-control">
                    </div>

                    <div class="col-md-2">
                        <label for="inputZip" class="form-label">Sexo</label>
                        <select class="form-select" aria-label="Default select example">
                            <option selected>Selecione</option>
                            <option value="1">Macho</option>
                            <option value="2">Fêmea</option>
                        </select>
                    </div>

                    <div class="col-md-4">
                        <label for="dateInput" class="form-label">Data de Nascimento</label>
                        <input type="date" class="form-control" id="dateInput">
                    </div>

                    <div class="col-md-2">
                        <label for="inputZip" class="form-label">Espécie</label>
                        <select class="form-select" aria-label="Default select example">
                            <option selected>Selecione</option>
                        </select> 
                    </div> 

                    <div class="col-md-2">
                        <label for="inputSex" class="form-label">Raça</label>
                        <select class="form-select" aria-label="Default select example">
                            <option selected>Selecione</option>
                        </select> 
                    </div> 

                    <div class="col-12 d-flex justify-content-end mt-4">
                        <button type="submit" class="btn btn-primary me-2">Salvar</button>
                        <button type="button" class="btn btn-secondary">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
</template>
