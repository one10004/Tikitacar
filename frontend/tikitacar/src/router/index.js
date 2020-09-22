import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/user/Login.vue'
import Signup from '../components/user/Signup.vue'
import Main from '../components/Main.vue'
import MyPage from '../components/user/MyPage.vue'
import Detail from '../components/Detail.vue'
import Search from '../components/Search.vue'
import PwInquiry from '../components/user/PwInquiry'
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

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
