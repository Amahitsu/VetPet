<script>
</script>

<template>
    <!-- Navbar -->
    <nav id="main-navbar" class="navbar navbar-expand-lg fixed-top">
        <!-- Container wrapper -->
        <div class="container-fluid">
            <!-- Brand -->
            <router-link to="/principal" class="navbar-brand text-white" href="#">VetPet</router-link>
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
                            <router-link to="/">
                                <a class="dropdown-item" href="#">Logout</a>
                            </router-link>
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