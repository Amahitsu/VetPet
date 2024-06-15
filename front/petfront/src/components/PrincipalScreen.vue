<script>
// import Footer from './footer/footer.vue';
import NavTopBar from './header/NavTopBar.vue';
import SideMenu from './menus/SideBarMenu.vue';

export default {
  components: {
    SideMenu,
    NavTopBar,
  },
}
</script>

<template>
  <NavTopBar />

  <div class="d-flex main">
    <SideMenu />
    <div class="flex-grow-1 container ">
      <router-view></router-view>
    </div>
  </div>

  <!--<FooterComponent />-->
</template>

<style>
</style>
