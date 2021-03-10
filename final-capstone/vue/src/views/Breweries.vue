<template>
  <div class= "breweries">
      <brewery-information v-bind:brewery-id="$route.params.id"/>
      <brewery-list/>
  </div>
</template>

<script>
import BreweryInformation from '@/components/BreweryInformation.vue';
import BreweryList from '@/components/BreweryList.vue';

export default {
    name: 'Breweries',
    components:{
        BreweryInformation,
        BreweryList
    }

}
</script>

<style>

</style>