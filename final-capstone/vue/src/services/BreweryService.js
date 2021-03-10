import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:3000"
});


export default{

list(){
    return http.get('/breweries');
},

getBreweriesById(id)
{
    return http.get(`/breweries/${id}`);
}



}