<template>
  <div class="beers-list">
    <ul>
      <li v-for="beer in breweries" v-bind:key="beer.id" class="brewery-beers">
        <router-link v-bind:to="{ name: 'brewery', params: { id: brewery.id } }">
          {{ beer.name }}</router-link>
        | {{ beer.info }} | {{ beer.abv }} | {{ beer.type }} 
      </li>
    </ul>
  </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
export default {
  name: "beer-list",
  data() {
    return {
      beer: [],
    };
  },

  created() {
    breweryService.list().then((response) => {
      this.beer = response.data;
    });
  },
};
</script>

<style>
</style>