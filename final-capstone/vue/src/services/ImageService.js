import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:8080"
});

export default{

list(id){
    if(id !=null){
        return http.get(`/images/${id}`);
    }else{
        return http.get(`/images`);
    }
},

getAllByBreweryId(id){
	return http.get(`/images/brewery/${id}`);
},

getByBeerId(id){
	return http.get(`/images/beer/${id}`);
},

getByUrl(url){
	return http.get(`images?url=${url}`);
},

getById(id){
	return http.get(`/images/${id}`);
},

getByDescription(description){
	return http.get(`/images?description=${description}`);
},

create(image){
	axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('token')}`;
	return http.post('/images/create', image);
},

deleteImage(id){
	return http.delete(`/images/${id}`);
},

deleteImageByBeerId(id){
    return http.delete(`/images/beer/${id}`);
},

update(image) {
    return http.put('/images/update', image);
},

}