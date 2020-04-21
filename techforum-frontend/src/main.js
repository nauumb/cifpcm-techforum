import Vue from 'vue'
import App from './App.vue'
import { router } from './router'
import store from './store'

import VuePrismEditor from 'vue-prism-editor'
import 'vue-prism-editor/dist/VuePrismEditor.css'
Vue.component('prism-editor', VuePrismEditor)


import VeeValidate from 'vee-validate'
Vue.use(VeeValidate)

import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
Vue.use(BootstrapVue)
Vue.use(BootstrapVueIcons)

import VuePageTransition from 'vue-page-transition'
Vue.use(VuePageTransition)


Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app')
