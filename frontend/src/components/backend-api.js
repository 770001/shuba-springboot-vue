import axios from 'axios'

const AXIOS = axios.create({
  baseURL: '/api',
  timeout: 1000
})

export default {
  getPing () {
    return AXIOS.get('/ping')
  },
  getUser (userId) {
    return AXIOS.get('/user/' + userId)
  },
  createUser (username, password) {
    return AXIOS.post('/user/' + username + '/' + password)
  },
  getSecured (username, password) {
    return AXIOS.get('/secured/', {
      auth: {
        username: username,
        password: password
      }
    })
  }
}
