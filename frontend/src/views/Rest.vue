<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <Hello msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import Hello from '@/components/Ping.vue'

export default {
  name: 'Home',
  components: {
    Hello
  }
}
</script>
