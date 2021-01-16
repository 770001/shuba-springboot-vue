<template>
  <div class="rest">
    <img alt="Vue logo" src="../assets/logo.png">
    <Ping msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import Ping from '@/components/Ping.vue'

export default {
  name: 'Rest',
  components: {
    Ping
  }
}
</script>
