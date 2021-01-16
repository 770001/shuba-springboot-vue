<template>
  <button class=”Search__button” @click="callRestService()">CALL Spring Boot REST backend service</button>
  <h3>{{ response }}</h3>
</template>

<script>
import api from './backend-api';

export default {
  data() {
    return {
      response: [],
      errors: []
    }
  },
  methods: {
    callRestService() {
      api.ping(`api/ping`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data
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
