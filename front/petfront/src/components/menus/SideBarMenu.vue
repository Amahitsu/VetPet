<script>

</script>

<template>

    <!-- Sidebar -->
    <nav id="sidebarMenu" class="position-fixed d-lg-block sidebar bg-gray">

        <img class="mx-3" src='../../assets/dog.png' alt="Logo VetPet" style="max-width: 150px;">
        <div class="list-group list-group-flush mx-3">
            <router-link to="/painel-de-controle" class="list-group-item list-group-item-action py-2 ripple">
                Painel de Controle
            </router-link>
            <router-link to="/agenda" class="list-group-item list-group-item-action py-2 ripple">
                Agenda
            </router-link>
            <router-link to="/clientes" class="list-group-item list-group-item-action py-2 ripple">
                Clientes
            </router-link>
            <router-link to="/especies" class="list-group-item list-group-item-action py-2 ripple">
                Espécies
            </router-link>
            <router-link to="/racas" class="list-group-item list-group-item-action py-2 ripple">
                Raças
            </router-link>
            <router-link to="/servicos" class="list-group-item list-group-item-action py-2 ripple">
                Serviços
            </router-link>
            <router-link to="/medicamentos" class="list-group-item list-group-item-action py-2 ripple">
                Medicamentos
            </router-link>
            <router-link to="/vacinas" class="list-group-item list-group-item-action py-2 ripple">
                Vacinas
            </router-link>
            <router-link to="/funcionarios" class="list-group-item list-group-item-action py-2 ripple">
                Funcionários
            </router-link>
        </div>
    </nav>
</template>

<style>
.sidebar {
    width: 210px;
    height: 100vh;
}

li {
    list-style-type: none;
}
</style>