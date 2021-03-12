import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080/user"
});

export default {

  findAll() {
    return http.get('')
  },

  update(user) {
    return http.put('/update', user)
  },

  updateRole(user) {
      axios.defaults.headers.common['Authorization'] = `Bearer ${$state.store.token}`
      return http.put('/role?role=brewer', user.username)
  },

}
