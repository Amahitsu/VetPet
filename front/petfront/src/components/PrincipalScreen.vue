<script>
// import Footer from './footer/footer.vue';
import SideMenu from './menus/SideBarMenu.vue';
import NavTopBar from './header/NavTopBar.vue'

export default {
  components: {
    SideMenu,
    NavTopBar,
  },
}
</script>

<template>
  <NavTopBar />
  <section class="vh-70 gradient-custom custom-bg"></section>
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
