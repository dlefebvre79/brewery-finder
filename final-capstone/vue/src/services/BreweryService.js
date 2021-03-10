import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});


export default{

list(){
    return http.get('/brewery');
},

getBreweriesById(id)
{
    return http.get(`/brewery/${id}`);
}



}