<template>
  <div class="ping">
    <button class=”Search__button” @click="callRestService()">Вызов SpringBoot REST</button>
    <h3>{{ backendResponse }}</h3>
  </div>
</template>

<script>
import api from './backend-api'

export default {
  name: 'Ping',
  data () {
    return {
      backendResponse: [],
      errors: []
    }
  },
  methods: {
    callRestService () {
      api.getPing()
        .then(response => {
          // JSON responses are automatically parsed.
          this.backendResponse = response.data
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
