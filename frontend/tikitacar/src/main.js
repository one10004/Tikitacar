import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';

import router from './router'
import store from './vuex/store'
import LoadScript from 'vue-plugin-load-script';
import VueDaumPostcode from "vue-daum-postcode"

Vue.use(VueDaumPostcode)
Vue.use(LoadScript);
Vue.config.productionTip = false

new Vue({
  vuetify,
  router,
  store : store,
  render: h => h(App)
}).$mount('#app')
