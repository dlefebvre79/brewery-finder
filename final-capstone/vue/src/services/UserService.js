import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080/user"
});

export default {

    findAll()
    {
        return http.get('')
    },

    getByUsername(username)
    {
        axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('token')}`;
        return http.get(`/get?username=${username}`);
    },

    update(user)
    {
        return http.put('/update', user)
    },

    updateRole(user, role)
    {
        axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('token')}`;
        return http.put(`/role?role=${role}`, user);
    },

}
