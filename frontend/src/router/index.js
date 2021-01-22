import Vue from 'vue'
import Router from 'vue-router'
import Protected from '@/components/Protected'
import HelloWorld from '@/components/HelloWorld'
import Service from '@/components/Service'
import About from '@/components/About'
import Login from '@/components/Login'
import Bootstrap from '@/components/Bootstrap'
import User from '@/components/User'
import store from '@/store'

Vue.use(Router)

const router = new Router({
  mode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
  routes: [
    { path: '/', component: HelloWorld },
    { path: '/callservice', component: Service },
    { path: '/bootstrap', component: Bootstrap },
    { path: '/user', component: User },
    { path: '/login', component: Login },
    { path: '/about', component: About },
    {
      path: '/protected',
      component: Protected,
      meta: {
        requiresAuth: true
      }
    },

    // otherwise redirect to home
    { path: '*', redirect: '/' }
  ]
})

router.beforeEach((to, from, next) => {
  console.log('in router.beforeEach()...')
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    if (!store.getters.isLoggedIn) {
      next({
        path: '/login'
      })
    } else {
      next()
      console.log('after next()...')
    }
  } else {
    next() // make sure to always call next()!
    console.log('after else next()...')
  }
})

export default router
