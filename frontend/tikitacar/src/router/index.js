import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/user/Login.vue'
import Signup from '../components/user/Signup.vue'
import Main from '../components/car/Main.vue'
import MyPage from '../components/user/MyPage.vue'
import Detail from '../components/car/Detail.vue'
import Search from '../components/car/Search.vue'
import Register from '../components/car/Register.vue'
import Predict from '../components/car/Predict.vue'

import PwInquiry from '../components/user/UserPwInquiryPage'
import UserDeletePage from "@/components/user/UserDeletePage";
import UserUpdatePage from "@/components/user/UserUpdatePage"

//import App from '@/App.vue';
Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path : '/user/pwinquiry',
    name : 'PwInquiry',
    component: PwInquiry
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
  {
    path: '/detail/:id',
    name: 'Detail',
    component: Detail,
    props: route => ({
      id: Number(route.params.id)
    })
  },{
    path : '/',
    name : "Home",

  },
  {
    path: '/search',
    name: 'Search',
    component: Search,
  },
  {
    path : '/user/leave',
    name : 'UserDeletePage',
    component: UserDeletePage
  },
  {
    path : '/user/modifyInfo',
    name : 'UserUpdatePage',
    component: UserUpdatePage
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
  },
  {
    path: '/predict',
    name: 'Predict',
    component: Predict
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
