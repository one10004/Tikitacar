import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Main from '../components/Main.vue'
import Detail from '../components/Detail.vue'
import Search from '../components/Search.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/',
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
  },
  {
    path: '/search',
    name: 'Search',
    component: Search,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
