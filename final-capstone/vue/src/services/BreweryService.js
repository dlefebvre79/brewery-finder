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
deleteBeerByBrewery(id)
{
    return http.delete(`/beer/delete-beer/${id}`);
},
deleteBrewery(breweryId)
{
    return http.delete(`/brewery/${breweryId}`);
},
create(brewery) {
    axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('token')}`;
    return http.post('/brewery/create', brewery);
},
sortBreweries(breweries) {
    let sortedBreweries = breweries.sort((a, b) => {
        let nameA = a.name.toLowerCase();
        let nameB = b.name.toLowerCase();
        if (nameA < nameB) {
          return -1;
        } else if (nameA > nameB) {
          return 1;
        } else {
          return 0;
        }
      });
      return sortedBreweries;
}
}