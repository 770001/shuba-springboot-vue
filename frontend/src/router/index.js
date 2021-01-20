import Vue from 'vue'
import VueRouter from 'vue-router'
import Protected from '@/components/Protected'
import HelloWorld from '@/components/HelloWorld'
import Ping from '@/components/Ping'
import About from '@/components/About'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'HelloWorld',
    component: HelloWorld
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/rest',
    name: 'Ping',
    component: Ping
  },
  {
    path: '/protected',
    component: Protected,
    meta: {
      requiresAuth: true
    }
  }
]

const router = new VueRouter({
  routes
})

export default router
