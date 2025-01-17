import Vue from 'vue'
import Vuex from 'vuex'

import cookies from 'vue-cookies'

import getters from './getters'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

const state = {
  signUpCheck :{
      email : "",
      nickname :"",
      name :"",
      password :"",
      passwordConfirm : "",
      birth :"",
      gender :"",
      phone :"",
      address : "",
      address_detail :""
  },
  userInfo: {
      authToken: cookies.get('auth-token'),
      nickname : cookies.get('nickname'),
      email: cookies.get('email')
  },
}

export default new Vuex.Store({
  state,
  getters,
  mutations,
  actions,
})
