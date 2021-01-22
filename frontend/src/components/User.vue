<template>
  <div class="user">
    <h1>Создать пользователя</h1>

    <h3>Просто какое-то взаимодействие с базой данных ...</h3>

    <input type="text" v-model="user.username" placeholder="username">
    <input type="text" v-model="user.password" placeholder="password">

    <button @click="createNewUser()">Создать пользователя</button>

    <div v-if="showResponse"><h6>Пользователь создан с id: {{ response }}</h6></div>

    <button v-if="showResponse" @click="retrieveUser()">Получить пользователя под id: {{ user.id }} - данные из базы</button>

    <h4 v-if="showRetrievedUser">Полученный пользователь: {{ retrievedUser.username }} {{ retrievedUser.password }}</h4>

  </div>
</template>

<script>
import api from './backend-api'

export default {
  name: 'User',

  data () {
    return {
      response: [],
      errors: [],
      user: {
        username: '',
        password: '',
        id: 0
      },
      showResponse: false,
      retrievedUser: {},
      showRetrievedUser: false
    }
  },
  methods: {
    // Fetches posts when the component is created.
    createNewUser () {
      api.createUser(this.user.username, this.user.password).then(response => {
        console.log(response.data)
        // JSON responses are automatically parsed.
        this.response = response.data
        this.user.id = response.data
        this.showResponse = true
      })
        .catch(e => {
          this.errors.push(e)
        })
    },
    retrieveUser () {
      api.getUser(this.user.id).then(response => {
        // JSON responses are automatically parsed.
        this.retrievedUser = response.data
        this.showRetrievedUser = true
      })
        .catch(e => {
          this.errors.push(e)
        })
    }
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
