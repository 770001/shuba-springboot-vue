<template>
  <!--таблица всех юзеров-->
  <div class="all-users">
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
                  <td>{{ user.roles }}</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>

</template>

<script>
import api from './backend-api'

export default {
  name: 'AdminPanel',
  data () {
    return {
      allUsers: [],
      errors: []
    }
  },
  methods: {
    //todo эта функция почему-то не вызывается!!!
    getAllUsers () {
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
    }
  }
}
</script>

<style scoped>

</style>
