<template>
  <div class = "brewery-information">
      <div v-if="brewery.id > 0">
        <h1>{{brewery.name}}</h1>
        <p>{{brewery.address}}</p>
        <p>{{brewery.city}}</p>
        <p>{{brewery.zipcode}}</p>
        <p>{{brewery.phoneNumber}}</p>
        <div id="hours-map">
            <table class="brewery-table">
                <th colspan="2">Hours of Operation:</th>
                <tr v-for="(day, index) in brewery.daysOpen" v-bind:key="day.id" class="table">
                    <td>{{day}}</td>
                    <td>{{formatHours(brewery.hours[index])}}</td>
                </tr>
            </table>
            <br>
            <gmap id="map"/>
            <br>
        </div>
        <p>{{brewery.history}}</p>
        <p>{{brewery.atmosphere}}</p>
        <p>Google Rating: {{$store.state.map.rating}}</p>
        <p>Price Level: {{$store.state.map.price}}</p>
        <p>Is {{brewery.familyFriendly == true ? "" : "not"}} family friendly</p>
        <p>Does {{brewery.patio == true ? "" : "not"}} have a patio</p>
        <p>Does {{brewery.food == true ? "" : "not"}} serve food</p>
        <!-- <a v-bind:href="brewery.websiteUrl">Website</a>
        <p>{{brewery.googleMapsUrl}}</p>
        <p>{{brewery.userId}}</p> -->
      </div>
    </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
import gmap from "@/components/Map";


export default {
    name: 'brewery-information',
    components: {
        gmap
    },
    data(){
        
        return{
            brewery:{
                id: 0,
                name: '',
                address:'',
                city:'',
                zip:'',
                phoneNumber:'',
                daysOpen: [],
                hours: [],
                history:'',
                atmosphere:'',
                familyFriendly:'',
                patio:'',
                food:'',
                isActive:'',

            },
        }
    },
    created(){
        breweryService.getBreweriesById(this.$route.params.id).then((response)=>{
            this.brewery = response.data;
            this.$store.commit('SET_MAP_TARGET', this.brewery.name);
        })
    },
    methods: {
        formatHours(hours) {
            const hoursArray = hours.split("-");
            const op = (hoursArray[0].substring(0,2)) + ":" + (hoursArray[0].substring(2));
            const cl = (hoursArray[1].substring(0,2)) + ":" + (hoursArray[1].substring(2));
            const open = new Date("01-01-1970 " + op).toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' });
            const close = new Date("01-01-1970 " + cl).toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' });
            return open + " - " + close;
        }
    }
}
</script>


<style>

#map {
    height: 250px;
}

</style>