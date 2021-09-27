<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <Main msg="Welcome to Your Vue.js App" />
  </div>
</template>

<script>
import Main from "@/components/main/main.vue";

export default {
  name: "Home",
  components: {
    Main,
  },
};
</script>
