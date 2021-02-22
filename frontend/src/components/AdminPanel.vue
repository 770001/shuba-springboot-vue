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

                    <button type="button" class="btn btn-primary" @click="openModal(user.id)">Изменить</button>

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
    <div v-if="modalIsVisible">
      <transition name="modal">
        <div class="modal-mask">
          <div class="modal-wrapper">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">Изменить {{ user.username }}</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true" @click="closeModal()">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <p>Здесь поля юзера:</p>
                  {{ updateUser.id }}
                  {{ updateUser.username }}
                  {{ updateUser.password }}
                  {{ updateUser.role }}
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" @click="closeModal()">Закрыть</button>
                  <button type="button" class="btn btn-primary">Сохранить</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </transition>
    </div>

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
        id: 0
      }
    }
  },
  methods: {
    getAllUsers() {
      console.log('in getAllUsers()...')
      api.getAllUsers()
        .then(response => {
          // JSON responses are automatically parsed.
          console.log('in api.getAllUsers()...')
          this.allUsers = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
    },
    deleteUserById(id) {
      console.log('in deleteUserById()...')
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
    openModal(id) {
      console.log('in openModal()...')
      this.modalIsVisible = true
      console.log(this.modalIsVisible)
      api.getUser(id).then(response => {
        console.log(response.data)
        this.updateUser.id = response.data.id
        console.log(this.updateUser.id)
        this.updateUser.username = response.data.username
        console.log(this.updateUser.username)
        this.updateUser.password = response.data.password
        console.log(this.updateUser.password)
        this.updateUser.role = response.data.roles[0].name
        console.log(this.updateUser.role)
      })
    },
    closeModal() {
      console.log('in closeModal()...')
      this.modalIsVisible = false
      console.log(this.modalIsVisible)
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
