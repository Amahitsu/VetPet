<script>
</script>

<template>


    <!--Main Navigation-->
    <header>
        <!-- Sidebar -->
        <nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-white">
            <div class="position-sticky">
                <div class="list-group list-group-flush mx-3 mt-4">
                    <a>
                        <i class="fas fa-tachometer-alt fa-fw me-3"></i><span></span>
                    </a>
                    <!--Aba para acessar funcionalidades de cliente -->
                    <a href="#" >
                        <li class="dropdown">
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
                    </a>

                    <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i
                            class="fas fa-lock fa-fw me-3"></i><span>Password</span></a>
                    <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i
                            class="fas fa-chart-line fa-fw me-3"></i><span>Analytics</span></a>
                    <a href="#" class="list-group-item list-group-item-action py-2 ripple">
                        <i class="fas fa-chart-pie fa-fw me-3"></i><span>SEO</span>
                    </a>
                    <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i
                            class="fas fa-chart-bar fa-fw me-3"></i><span>Orders</span></a>
                    <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i
                            class="fas fa-globe fa-fw me-3"></i><span>International</span></a>
                    <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i
                            class="fas fa-building fa-fw me-3"></i><span>Partners</span></a>
                    <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i
                            class="fas fa-calendar fa-fw me-3"></i><span>Calendar</span></a>
                    <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i
                            class="fas fa-users fa-fw me-3"></i><span>Users</span></a>
                    <a href="#" class="list-group-item list-group-item-action py-2 ripple"><i
                            class="fas fa-money-bill fa-fw me-3"></i><span>Sales</span></a>
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
    margin-top: 3em;
}

li { list-style-type: none;}
</style>