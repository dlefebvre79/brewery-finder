<template>
  <div class ="brewery-list">
    <div v-for ="brewery in breweries" v-bind:key ="brewery.id" class="brewery">

      <router-link v-bind:to="{name: 'brewery', params:{id: brewery.id} }">{{brewery.name}}</router-link>>
    </div>
 </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
export default {
  name: 'brewery-list',
  data(){
    return{
      breweries:[]
    }
  },

  created(){
    breweryService.list().then((response)=>{
      this.breweries = response.data;
    });
  }

}
</script>

<style>

</style>