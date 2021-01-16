import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 1000
});


export default {
  ping() {
    return AXIOS.get(`/ping`);
  }
}
