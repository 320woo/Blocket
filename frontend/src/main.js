import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import PrimeVue from 'primevue/config'
import InputText from 'primevue/inputtext/sfc'
import Button from 'primevue/button'
import SelectButton from 'primevue/selectbutton';
import Dropdown from 'primevue/dropdown';
import AutoComplete from 'primevue/autocomplete';
import Dialog from 'primevue/dialog';
import Panel from 'primevue/panel';
import Textarea from 'primevue/textarea'

import 'primeflex/primeflex.css'
import 'primevue/resources/primevue.min.css'
import 'primevue/resources/themes/bootstrap4-light-blue/theme.css'
import 'primeicons/primeicons.css'

createApp(App)
  .use(PrimeVue)  
  .use(store)
  .use(router)
  .component("InputText", InputText)
  .component("SelectButton", SelectButton)
  .component("Dropdown", Dropdown)
  .component("AutoComplete", AutoComplete)
  .component("Button", Button)  
  .component("Dialog", Dialog)  
  .component("Panel", Panel)
  .component("Textarea", Textarea)

  .mount("#app");

