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
  getAllRoles () {
    return AXIOS.get('/role/all-roles/')
  },
  createUser (username, password, role) {
    return AXIOS.post('/user/' + username + '/' + password + '/' + role)
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
