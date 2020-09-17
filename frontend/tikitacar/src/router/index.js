import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/user/Login.vue'
import Signup from '../components/user/Signup.vue'
import Main from '../components/Main.vue'
<<<<<<< HEAD
import MyPage from '../components/user/MyPage.vue'
=======
import Detail from '../components/Detail.vue'
>>>>>>> frontend-feature-detail

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
<<<<<<< HEAD
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
=======
    path: '/',
>>>>>>> frontend-feature-mainpage
    name: 'Main',
    component: Main
  },
<<<<<<< HEAD

=======
  {
    path: '/detail/:id',
    name: 'Detail',
    component: Detail,
    props: route => ({
      id: Number(route.params.id)
    })
  }
>>>>>>> frontend-feature-detail
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
