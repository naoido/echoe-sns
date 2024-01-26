import { createApp } from 'vue'
import App from './App.vue'
import axios from "./plugins/axios"
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'

loadFonts()

createApp(App)
  .use(vuetify, axios)
  .mount('#app')
