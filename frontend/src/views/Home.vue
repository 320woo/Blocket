<template>
  <div class="home">
    <Main msg="Welcome to Your Vue.js App" />
  </div>
</template>

<script>
import Main from "@/components/main/main.vue";
import * as pService from '@/utils/pService.js'

export default {
  name: "Home",
  setup() {
    pService.UserCheck();
  },
  components: {
    Main,
  },
  methods: {
    user() {
      this.$store.dispatch("userCheck");
    }
  },
};
</script>
