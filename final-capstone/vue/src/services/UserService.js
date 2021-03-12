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

  updateRole(user, role) {
      //user.authorities = [{name: ""}];
      axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('token')}`;
    //   console.log(localStorage.getItem('token'));
    //   console.log(user);
      return http.put(`/role?role=${role}`, user);
  },

}
