<template>
  <div class="image-list">
      <ul>
          <li v-for="image in images" v-bind:key="image.id">
              <router-link
              v-bind:to="{ name: 'image-list', params: { id: image.id } }"
              class="images"
              >{{ image.url }}</router-link>
              </li>
          </ul>
    </div>
</template>

<script>
import imageService from '@/services/ImageService.js'

export default {
    name: "image-list",
    data(){
        return{
        images:{
            id: '',
            url: '',
            description: '',
            altText: '',
            beerId: '',
            breweryId:''
        },
        };
    },
    created() {
    imageService.list().then((response)=> {
      this.images = response.data;
    });

    imageService.getAllByBreweryId(this.$route.params.id).then((response)=>{
        this.brewery = response.data;
    });

    imageService.getByBeerId(this.$route.params.id).then((response)=>{
        this.beers = response.data;
    });

    imageService.getByUrl(this.$route.params.url).then((response)=>{
        this.url = response.data;
    });

    imageService.getById(this.$route.params.id).then((response)=>{
        this.image = response.data;
    });

    },

   
};

</script>


<style>

</style>