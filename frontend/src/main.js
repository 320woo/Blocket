import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import PrimeVue from 'primevue/config'
import InputText from 'primevue/inputtext/sfc'
import Button from 'primevue/button'
import 'primeflex/primeflex.css'
import 'primevue/resources/primevue.min.css'
import 'primevue/resources/themes/bootstrap4-light-blue/theme.css'
import 'primeicons/primeicons.css'

createApp(App)
  .use(store).use(router).use(PrimeVue)
  .component("InputText", InputText)
  .component("Button", Button)  
  .mount("#app");

