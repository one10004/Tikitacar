import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/user/Login.vue'
import Signup from '../components/user/Signup.vue'
import Main from '../components/Main.vue'
import MyPage from '../components/user/MyPage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path : '/user/signup',
    name : 'SignUp',
    component: Signup
  } , {
    path : '/user/mypage',
    name : 'MyPage',
    component: MyPage
  },
  {
    path: '/main',
    name: 'Main',
    component: Main
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
