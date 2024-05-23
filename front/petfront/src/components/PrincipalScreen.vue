<script>
<<<<<<< HEAD
import Footer from './footer/footer.vue';
=======
import FooterComponent from './footer/footer.vue';
>>>>>>> 634757c441a44f0513fdceee428fd788b3975c80
import SideMenu from './menus/SideBarMenu.vue';
import NavTopBar from './header/NavTopBar.vue'

export default {
  components: {
    FooterComponent,
    SideMenu,
    NavTopBar,
  },
}
</script>

<template>
  <NavTopBar />

  <div class="d-flex main">
    <SideMenu />
    <div class="flex-grow-1 container">
      <router-view></router-view>
    </div>
  </div>

  <!--<FooterComponent />-->
</template>

<style>
.main {
  margin-top: 4.5rem;
}
</style>
