<script>
</script>

<template>


    <!--Main Navigation-->
    <header>
        <!-- Sidebar -->
        <nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-white">
            <div class="position-sticky">
                <div class="list-group list-group-flush mx-3 mt-4">
                    <div id=" imagem-sidebar">
                        <img>
                    </div>
                    <!--Acesso funcionalidades de cliente -->

                    <li class="dropdown list-group-item ">
                        <button class="btn btn-white dropdown-toggle text-black text-aling-center" type="button"
                            id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            Clientes
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li>
                                <router-link to="/cadastro/cliente">
                                    <a class="dropdown-item" href="#">Cadastro</a>
                                </router-link>
                            </li>
                            <li>
                                <router-link to="/list/cliente">
                                    <a class="dropdown-item" href="#">Consultar</a>
                                </router-link>
                            </li>
                        </ul>
                    </li>


                    <!--Acessar pagina da agenda-->
                    <li class="dropdown list-group-item">
                        <button class="btn btn-white dropdown-toggle text-black text-aling-center" type="button"
                            id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">
                            Agenda Geral
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li>
                                <router-link to="/cadastro/cliente">
                                    <a class="dropdown-item" href="#">Agenda</a>
                                </router-link>
                            </li>
                        </ul>

                    </li>
                    <!--Acessar pagina cadastros gerais-->
                    <li class="dropdown list-group-item">
                        <button class="btn btn-white dropdown-toggle text-black text-aling-center" type="button"
                            id="dropdownMenuButton3" data-bs-toggle="dropdown" aria-expanded="false">
                            Cadastros Gerais
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li>
                                <router-link to="/cadastro/cliente">
                                    <a class="dropdown-item" href="#">Espécie</a>
                                </router-link>
                            </li>
                            <li>
                                <router-link to="/cadastro/cliente">
                                    <a class="dropdown-item" href="#">Funcionário</a>
                                </router-link>
                            </li>
                            <li>
                                <router-link to="/cadastro/cliente">
                                    <a class="dropdown-item" href="#">Raça</a>
                                </router-link>
                            </li>
                            <li>
                                <router-link to="/cadastro/cliente">
                                    <a class="dropdown-item" href="#">Serviço</a>
                                </router-link>
                            </li>
                        </ul>
                    </li>
                </div>
            </div>
        </nav>
        <!-- Sidebar -->
    </header>
    <!--Main Navigation-->

    <!--Main layout-->
    <main style="margin-top: 58px;">
        <div class="container pt-4"></div>
    </main>
</template>

<style>
.sidebar {
    width: 210px;
    height: 100vh;
    margin-top: 11rem;
}

;

.imagem-sidebar {
    max-width: 150px;
    max-height: 150px;
    border: 
}

;

li {
    list-style-type: none;
}
</style>