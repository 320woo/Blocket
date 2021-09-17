<template>
  <div>
    <h5>Basic</h5>
    <InputSwitch v-model="checked1" />

    <h5>Preselection</h5>
    <InputSwitch v-model="checked2" />
  </div>
</template>

<script>
import { ref } from 'vue';
export default {
name: '',
components: {},
setup() {
  const checked1 = ref(false);
  const checked2 = ref(true);

  return { checked1, checked2 }
},

methods: {},
}
</script>