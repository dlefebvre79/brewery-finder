<template>
<div class = "beer-information">
    <div v-if="beer.id > 0">
        <h1>{{beer.name}}</h1>
        <p> ABV: {{beer.abv}}</p>
        <p> IBU: {{beer.ibu}}</p>
        <p>TYPE: {{beer.type}}</p>
        <p>DESCRIPTION: {{beer.info}}</p>
    </div>

</div>
  
</template>

<script>
import breweryService from "@/services/BreweryService";

export default {
    name: 'beer-information',

    data(){

        return{
            beer:{
                id: 0,
                name: '',
                abv: '',
                ibu: '',
                type:'',
                info: '',
                breweryId: '',
                isActive: ''

            },
        }
    },
    created(){
        breweryService.getBeerById(this.$route.params.id).then((response)=>{
            this.beer = response.data;
            
        })
    }

}
</script>

<style>

</style>