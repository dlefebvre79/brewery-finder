<template>
  <div class = "brewery-information">
     
      <h1>{{brewery.name}}</h1>
      <h3>{{brewery.address}}</h3>
      <h3>{{brewery.city}}</h3>
      <h3>{{brewery.zipcode}}</h3>
      <h3>{{brewery.phoneNumber}}</h3>
      <h3>{{brewery.daysOfOperation}}</h3>
      <h3>{{brewery.businessHours}}</h3>
      <h3>{{brewery.historyDesc}}</h3>
      <h3>{{brewery.atmosphere}}</h3>
      <h3>{{brewery.isFamilyFriendly}}</h3>
      <h3>{{brewery.isPatio}}</h3>
      <h3>{{brewery.isFood}}</h3>
      <h3>{{brewery.isActive}}</h3>
      <h3>{{brewery.websiteUrl}}</h3>
      <h3>{{brewery.googleMapsUrl}}</h3>
      <h3>{{brewery.userId}}</h3>
     

  </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
export default {
    name: 'brewery-information',

    data(){
        return{
            brewery:{
                id: 0,
                name: '',
                address:'',
                city:'',
                zipcode:'',
                phoneNumber:'',
                daysOfOperation:'',
                businessHours:'',
                historyDesc:'',
                atmosphere:'',
                isFamilyFriendly:'',
                isPatio:'',
                isFood:'',
                isActive:'',
                websiteUrl:'',
                googleMapsUrl:'',
                userId:''
            },
        }
    },
    created(){
        breweryService.getBreweriesById(this.$route.params.id).then((response)=>{
            this.brewery = response.data;
        })
    }

}
</script>


<style>

</style>