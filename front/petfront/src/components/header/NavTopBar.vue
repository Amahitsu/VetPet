<script>
</script>

<template>
    <!-- Navbar -->
    <nav id="main-navbar" class="navbar navbar-expand-lg fixed-top">
        <!-- Container wrapper -->
        <div class="container-fluid">
            <!-- Toggle button -->
            <button data-mdb-button-init class="navbar-toggler" type="button" data-mdb-collapse-init
                data-mdb-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false"
                aria-label="Toggle navigation">
                <i class="fas fa-bars"></i>
            </button>

            <!-- Brand -->
            <a class="navbar-brand" href="#">
                <img src="https://mdbcdn.b-cdn.net/img/logo/mdb-transaprent-noshadows.webp" height="25" alt="MDB Logo"
                    loading="lazy" />
            </a>

            <!-- Right links -->
            <-- <ul class="navbar-nav ms-auto d-flex flex-row">
                <!-- Nome do login -->
                <li class="dropdown">
                    <button class="btn btn-dark dropdown-toggle text-white" type="button" id="dropdownMenuButton1"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        Amanda Hirata Medeiros
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li>
                            <a class="dropdown-item" href="#">Logout</a>
                        </li>
                    </ul>
                </li>
                </ul>
        </div>
        <!-- Container wrapper -->
    </nav>
</template>

<style>
.navbar {
    background-color: #1e2627;
    position: fixed;
}
</style>