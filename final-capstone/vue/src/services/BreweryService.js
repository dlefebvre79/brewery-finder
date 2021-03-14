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
},

getBeerById(id)
{
    return http.get(`/beer/${id}`);
},

getBeerByBrewery(id)
{
    return http.get(`/beer/brewery/${id}`);
},
addBeerByBrewery(beer)
{
    return http.post(`/beer/create`, beer);
},
deleteBeerByBrewery(beer)
{
    return http.delete(`/beer/delete-beer`, beer);
},
create(brewery) {
    axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('token')}`;
    return http.post('/brewery/create', brewery);
}



}