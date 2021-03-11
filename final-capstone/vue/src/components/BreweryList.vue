<template>
  <div class="brewery-list">
    <ul>
      <li v-for="brewery in breweries" v-bind:key="brewery.id" class="brewery">
        <router-link v-bind:to="{ name: 'brewery', params: { id: brewery.id } }" class="brew-list">
          {{ brewery.name }}</router-link>
        | {{ brewery.phoneNumber }} | {{ brewery.address }} | {{ brewery.city }} |  Rating: (4/5) |  Pricing: ($$)
      </li>
    </ul>
  </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
export default {
  name: "brewery-list",
  data() {
    return {
      breweries: [],
    };
  },

  created() {
    breweryService.list().then((response) => {
      this.breweries = response.data;
    });
  },
};
</script>

<style>
</style>