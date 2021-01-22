<template>
  <div class="bootstrap">
    <h1>{{ msg }}</h1>
    <h5>Вызов службы REST легко выполнить с помощью Vue.js, если вы знаете, как это сделать.</h5>
    <p></p>
    <h6>
      <b-badge variant="primary">Поехали!</b-badge>
      Вызовите серверную службу REST Spring Boot, нажав кнопку:
    </h6>
    <p></p>
    <b-btn variant="success" @click="callHelloApi()" id="btnCallHello">/hello (GET)</b-btn>
    <p></p>
    <h4>Ответ с бекэнда:
      <b-alert :show="showResponse" dismissible @dismissed="showResponse=false">{{ backendResponse }}</b-alert>
    </h4>

    <b-btn v-b-toggle.collapse1>Показать детали ответа</b-btn>
    <p></p>
    <b-collapse id="collapse1" class="mt-2">
      <b-card>
        <p class="card-text">Ответ в деталях</p>
        <b-btn v-b-toggle.collapse1_inner size="sm" variant="primary">HTTP статус</b-btn>
        <b-collapse id=collapse1_inner class="mt-2">
          <b-card>Статус: {{ httpStatusCode }}</b-card>
          <b-card>Сообщение: {{ httpStatusText }}</b-card>
        </b-collapse>

        <b-btn v-b-toggle.collapse2_inner size="sm" id="btnHttpHeaders" variant="warning">HTTP заголовок</b-btn>
        <b-collapse id=collapse2_inner class="mt-2">

          <div v-if="headers && headers.length">
            <li v-for="header of headers" :key="header">
              <b-card>Заголовок: {{ header.valueOf() }}</b-card>
            </li>
          </div>
        </b-collapse>

        <b-btn v-b-toggle.collapse3_inner size="sm" variant="danger">Полная конфигурация запроса</b-btn>
        <b-collapse id=collapse3_inner class="mt-2">
          <p class="card-text">Конфиг: {{ fullResponse.config }} </p>
        </b-collapse>
      </b-card>
    </b-collapse>

    <b-tooltip target="btnHttpHeaders" title="Вы всегда должны знать свои HTTP-заголовки!"></b-tooltip>

  </div>
</template>

<script>
import api from './backend-api'

export default {
  name: 'bootstrap',

  data () {
    return {
      msg: 'Хорошая конфетка Bootstrap!',
      showResponse: false,
      backendResponse: '',
      fullResponse: {
        config: {
          foo: '',
          bar: ''
        }
      },
      httpStatusCode: '',
      httpStatusText: '',
      headers: ['Здесь ничего нет. Вы звонили в Службу?'],
      errors: []
    }
  },
  methods: {
    callHelloApi () {
      api.getPing().then(response => {
        this.backendResponse = response.data
        this.httpStatusCode = response.status
        this.httpStatusText = response.statusText
        this.headers = response.headers
        this.fullResponse = response
        this.showResponse = true
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
p {
  margin-bottom: 20px;
}

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
