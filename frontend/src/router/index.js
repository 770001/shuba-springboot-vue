import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Protected from "@/components/Protected";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/rest',
    name: 'Rest',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "rest" */ '../views/Rest.vue')
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
