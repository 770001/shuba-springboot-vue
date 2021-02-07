import Vue from 'vue'
import Router from 'vue-router'
import Protected from '@/components/Protected'
import Home from '@/components/Home'
import Service from '@/components/Service'
import About from '@/components/About'
import Login from '@/components/Login'
import Bootstrap from '@/components/Bootstrap'
import User from '@/components/User'
import AdminPanel from '@/components/AdminPanel'
import store from '@/store'

Vue.use(Router)

const router = new Router({
  mode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
  routes: [
    { path: '/', component: Home },
    { path: '/callservice', component: Service },
    { path: '/user', component: User },
    { path: '/bootstrap', component: Bootstrap },
    { path: '/login', component: Login },
    {
      path: '/protected',
      component: Protected,
      meta: {
        requiresAuth: true
      }
    },
    { path: '/admin-panel', component: AdminPanel },
    { path: '/about', component: About },

    // otherwise redirect to home
    { path: '*', redirect: '/' }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    if (!store.getters.isLoggedIn) {
      next({
        path: '/login'
      })
    } else {
      next()
    }
  } else {
    next() // make sure to always call next()!
  }
})

export default router
