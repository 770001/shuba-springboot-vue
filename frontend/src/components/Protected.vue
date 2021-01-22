<template>
  <div class="protected">
    <h1>
      <b-badge variant="success">ДА, вы сделали это!</b-badge>
    </h1>
    <h5>Если вы можете это прочитать, вы успешно вошли в систему и перешли на этот защищенный сайт :)</h5>

    <b-btn variant="primary" @click="getSecuredTextFromBackend()">Вызов защищенного API</b-btn>
    <p></p>

    <div v-if="securedApiCallSuccess">
      <b-badge variant="success">Вызов API</b-badge>
      Полный ответ: {{ backendResponse }}
      <b-badge variant="success">успешный</b-badge>
    </div>
    <div v-if="errors">
      <b-badge variant="warning">Вызов API</b-badge>
      {{ errors }}
      <b-badge variant="warning">Не успешный</b-badge>
    </div>
  </div>

</template>

<script>
import api from './backend-api'
import store from './../store'

export default {
  name: 'Protected',

  data () {
    return {
      backendResponse: '',
      securedApiCallSuccess: false,
      errors: null
    }
  },
  methods: {
    getSecuredTextFromBackend () {
      api.getSecured(store.getters.getUserName, store.getters.getUserPass)
        .then(response => {
          console.log("Response: '" + response.data + "' with Status code " + response.status)
          this.securedApiCallSuccess = true
          this.backendResponse = response.data
        })
        .catch(error => {
          console.log('Error: ' + error)
          this.errors = error
        })
    }
  }
}

</script>
