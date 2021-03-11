<template>
  <div class= "breweries">
      <brewery-information v-bind:brewery-id="$route.params.id"/>
      <brewery-list/>
      <beer-information/>
  </div>
</template>

<script>
import BreweryInformation from '@/components/BreweryInformation.vue';
import BreweryList from '@/components/BreweryList.vue';
import BeerInformation from '@/components/BeerInformation.vue';

export default {
    name: 'Breweries',
    components:{
        BreweryInformation,
        BreweryList,
        BeerInformation
    }

}
</script>

<style>

</style>