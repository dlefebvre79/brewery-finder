<template>
<div class = "beer-information">
    <div class="transbox">
    <div v-if="beer.id > 0">
        <h1>{{beer.name}}</h1>
        <img :src="image.url"
             :alt="image.altText"
             height="250px">
        <p> ABV: {{beer.abv}}</p>
        <p> IBU: {{beer.ibu}}</p>
        <p>TYPE: {{beer.type}}</p>
        <p>DESCRIPTION: {{beer.info}}</p>
    </div>
    <review-list/>
    <add-a-review/>
    </div>
</div>
  
</template>

<script>
import breweryService from "@/services/BreweryService";
import reviewList from "@/components/ReviewList";
import addAReview from "@/components/AddAReview";
import imageService from "@/services/ImageService";

export default {
    name: 'beer-information',
    components:{
        reviewList,
        addAReview
    },

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
            image: {},
        }
    },
    created(){
        breweryService.getBeerById(this.$route.params.id).then((response)=>{
            this.beer = response.data;
        });
        imageService.getByBeerId(this.$route.params.id).then((response)=>{
            this.image = response.data;
            console.log(this.image);
        });
    }

}
</script>

<style>
</style>