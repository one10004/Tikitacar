import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';

import router from './router'
import store from './vuex/store'
import VueCookies from 'vue-cookies'
import VueDaumPostcode from "vue-daum-postcode"

Vue.use(VueDaumPostcode)
Vue.use(VueCookies)

Vue.config.productionTip = false

new Vue({
  vuetify,
  router,
  store : store,
  render: h => h(App)
}).$mount('#app')
