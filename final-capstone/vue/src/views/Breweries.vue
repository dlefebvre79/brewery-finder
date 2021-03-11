<template>
  <div class= "breweries">
      <brewery-information v-bind:brewery-id="$route.params.id"/>
      <brewery-list/>
      <beer-information/>
      <beer-list/>
  </div>
</template>

<script>
import BreweryInformation from '@/components/BreweryInformation.vue';
import BreweryList from '@/components/BreweryList.vue';
import BeerInformation from '@/components/BeerInformation.vue';
import BeerList from '@/components/BeerList.vue';
export default {
    name: 'Breweries',
    components:{
        BreweryInformation,
        BreweryList,
        BeerInformation,
        BeerList
    }

}
</script>

<style>

</style>