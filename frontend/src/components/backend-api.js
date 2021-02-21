import axios from 'axios'

const AXIOS = axios.create({
  baseURL: '/api',
  timeout: 1000
})

export default {
  getPing () {
    return AXIOS.get('/hello')
  },
  getUser (userId) {
    return AXIOS.get('/user/' + userId)
  },
  getAllUsers () {
    return AXIOS.get('/admin/all-users/')
  },
  createUser (username, password) {
    return AXIOS.post('/user/' + username + '/' + password)
  },
  deleteUserById (id) {
    return AXIOS.delete('/user/' + id)
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
