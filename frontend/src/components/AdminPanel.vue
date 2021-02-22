<template>
  <!--list users-->
  <div class="all-users" id="el">
    <div class="container">
      <div class="row">
        <div class="col-md-8 col-md-offset-2">
          <div class="panel panel-default">
            <div class="panel-heading">Список пользователей</div>
            <div class="panel-body">
              <table class="table">
                <thead>
                <tr>
                  <th>ID</th>
                  <th>Username</th>
                  <th>Roles</th>
                  <th>Edit</th>
                  <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="user in allUsers" :key="user.id">
                  <td>{{ user.id }}</td>
                  <td>{{ user.username }}</td>
                  <td>
                    <div v-for="role in user.roles" :key="role.id">
                      {{ role.name }}
                    </div>
                  </td>
                  <td>
                    <b-button size="sm" v-b-modal="'editUserModal'" @click="editUser(user)">Изменить</b-button>
                  </td>
                  <td>
                    <button class=”Search__button” @click="deleteUserById(user.id)">Удалить</button>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--modal-->
    <b-modal id="editUserModal">
      id {{updateUser.id}}
      username {{updateUser.username}}
      password {{updateUser.password}}
      role {{updateUser.role}}
    </b-modal>
  </div>
</template>

<script>
import api from './backend-api'

export default {
  name: 'AdminPanel',
  data() {
    return {
      allUsers: [],
      errors: [],
      modalIsVisible: false,
      updateUser: {
        username: '',
        password: '',
        role: '',
        id: ''
      }
    }
  },
  methods: {
    getAllUsers() {
      console.log('in getAllUsers()')
      api.getAllUsers()
        .then(response => {
          // JSON responses are automatically parsed.
          console.log('in api.getAllUsers()')
          this.allUsers = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
    },
    deleteUserById(id) {
      console.log('in deleteUserById()')
      api.deleteUserById(id)
        .then(response => {
          console.log(response)
          const index = this.allUsers.findIndex(user => user.id === id)
          console.log('find the user index')
          if (~index) {
            console.log('if the user exists in array')
            this.allUsers.splice(index, 1)
            console.log('delete the user')
          }
        })
    },
    editUser(user) {
      console.log('in editUser')
      console.log(user)
      this.updateUser.id = user.id
      this.updateUser.username = user.username
      this.updateUser.password = user.password
      this.updateUser.role = user.roles[0].name
      console.log(this.updateUser)
    }
  },
  created() {
    console.log('in created()...')
    this.getAllUsers()
  }
}
</script>

<style scoped>

</style>
