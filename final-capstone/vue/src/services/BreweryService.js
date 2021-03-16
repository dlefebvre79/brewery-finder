import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});


export default{

list(brewerId){
    if(brewerId != null) {
        return http.get(`/brewery?brewerId=${brewerId}`);
    } else {
        return http.get('/brewery');
    }
},

listByBrewer(brewerId){
    return http.get(`/brewery?brewerId=${brewerId}`);
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
addReviewByBeerId(review)
{
    return http.post(`/reviews/create/`, review);
},
listAllReviews()
{
    return http.get('/reviews');
},
getReviewById(id)
{
    return http.get(`/reviews/${id}`)
},
getReviewsByBeerId(id)
{
    return http.get(`/reviews/beer/${id}`);
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
update(brewery) {
    axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('token')}`;
    brewery.brewer.authorities = "";
    brewery.daysOpen = this.sortDays(brewery.daysOpen);
    return http.put('/brewery/update', brewery);
},
sortDays(days) {
    let sortedDays = [];
    if(days.includes('Sunday')) {
        sortedDays.push('Sunday');
    }
    if(days.includes('Monday')) {
        sortedDays.push('Monday');
    }
    if(days.includes('Tuesday')) {
        sortedDays.push('Tuesday');
    }
    if(days.includes('Wednesday')) {
        sortedDays.push('Wednesday');
    }
    if(days.includes('Thursday')) {
        sortedDays.push('Thursday');
    }
    if(days.includes('Friday')) {
        sortedDays.push('Friday');
    }
    if(days.includes('Saturday')) {
        sortedDays.push('Saturday');
    }
    return sortedDays;
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