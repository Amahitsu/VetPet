<script>
import Footer from './footer/footer.vue';
import SideMenu from './menus/SideBarMenu.vue';
import NavTopBar from './header/NavTopBar.vue'

export default {
  components: {
    Footer,
    SideMenu,
    NavTopBar,
  },
}
</script>

<template>
    <NavTopBar />
    <SideMenu />
    <Footer />
</template>

<style>
.container {
  display: flex;
  flex-direction: column;
}
</style>
