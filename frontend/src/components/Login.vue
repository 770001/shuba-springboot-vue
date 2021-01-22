<template>
  <div class="unprotected" v-if="loginError">
    <h1><b-badge variant="danger">You don't have rights here, mate :D</b-badge></h1>
    <h5>Seams that you don't have access rights... </h5>
  </div>
  <div class="unprotected" v-else>
    <h1><b-badge variant="info">Please login to get access!</b-badge></h1>
    <h5>You're not logged in - so you don't see much here. Try to log in:</h5>

    <form @submit.prevent="callLogin()">
      <label>
        <input type="text" placeholder="username" v-model="username">
      </label>
      <label>
        <input type="password" placeholder="password" v-model="password" autocomplete="on">
      </label>
      <b-btn variant="success" type="submit">Login</b-btn>
      <p v-if="error" class="error">Bad login information</p>
    </form>
  </div>

</template>

<script>
export default {
  name: 'Login',

  data () {
    return {
      loginError: false,
      username: '',
      password: '',
      error: false,
      errors: []
    }
  },
  methods: {
    callLogin () {
      console.log('in callLogin()...')
      this.errors = []
      this.$store.dispatch('login', { username: this.username, password: this.password })
        .then(() => {
          this.$router.push('/Protected')
          console.log('after push(\'/Protected\')...')
        })
        .catch(error => {
          console.log(error)
          this.loginError = true
          this.errors.push(error)
          this.error = true
        })
    }
  }
}
</script>
