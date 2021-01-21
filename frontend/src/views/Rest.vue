<template>
  <div class="rest">
    <img alt="Vue logo" src="../assets/logo.png">
    <Ping msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
import Ping from '@/components/Service.vue'

export default {
  name: 'Rest',
  components: {
    Ping
  }
}
</script>
