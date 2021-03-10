<template>
  <div class= "breweries">
      <brewery-information v-bind:brewery-id="$route.params.id"/>
  </div>
</template>

<script>
import BreweryInformation from '@/components/BreweryInformation.vue';

export default {
    name: 'Breweries',
    components:{
        BreweryInformation
    }

}
</script>

<style>

</style>