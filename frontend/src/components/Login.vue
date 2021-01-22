<template>
  <div class="unprotected" v-if="loginError">
    <h1><b-badge variant="danger">У тебя здесь нет прав, приятель: D</b-badge></h1>
    <h5>Кажется, что у вас нет прав доступа...</h5>
  </div>
  <div class="unprotected" v-else>
    <h1><b-badge variant="info">Пожалуйста, войдите, чтобы получить доступ!</b-badge></h1>
    <h5>Вы не вошли в систему - поэтому здесь вы мало что видите. Попробуйте авторизоваться:</h5>

    <form @submit.prevent="callLogin()">
      <label>
        <input type="text" placeholder="username" v-model="username">
      </label>
      <label>
        <input type="password" placeholder="password" v-model="password" autocomplete="on">
      </label>
      <b-btn variant="success" type="submit">Войти</b-btn>
      <p v-if="error" class="error">Неверная информация для входа</p>
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
