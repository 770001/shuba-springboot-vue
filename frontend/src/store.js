import Vue from 'vue'
import Vuex from 'vuex'
import api from './components/backend-api'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    loginSuccess: false,
    loginError: false,
    userName: null,
    userPass: null
  },
  mutations: {
    login_success(state, payload) {
      state.loginSuccess = true
      state.userName = payload.userName
      state.userPass = payload.userPass
    },
    login_error(state, payload) {
      state.loginError = true
      state.userName = payload.userName
    }
  },
  actions: {
    login({commit}, {username, password}) {
      return new Promise((resolve, reject) => {
        api.getSecured(username, password)
          .then(response => {
            if (response.status === 200) {
              // place the loginSuccess state into our vuex store
              commit('login_success', {
                userName: username,
                userPass: password
              })
            }
            resolve(response)
          })
          .catch(error => {

            //todo error должен использоваться
            if (error) {
            }
            // place the loginError state into our vuex store
            commit('login_error', {
              userName: username
            })
            //todo Expected the Promise rejection reason to be an Error  prefer-promise-reject-errors
            reject('Invalid credentials!')
          })
      })
    }
  },
  getters: {
    isLoggedIn: state => state.loginSuccess,
    hasLoginErrored: state => state.loginError,
    getUserName: state => state.userName,
    getUserPass: state => state.userPass
  }
})
