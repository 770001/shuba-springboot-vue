<template>
  <div class="user">
    <h1>Create User</h1>

    <h3>Just some database interaction...</h3>

    <input type="text" v-model="user.username" placeholder="username">
    <input type="text" v-model="user.password" placeholder="password">

    <button @click="createNewUser()">Create User</button>

    <div v-if="showResponse"><h6>User created with Id: {{ response }}</h6></div>

    <button v-if="showResponse" @click="retrieveUser()">Retrieve user {{ user.id }} data from database</button>

    <h4 v-if="showRetrievedUser">Retrieved User {{ retrievedUser.username }} {{ retrievedUser.password }}</h4>

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
