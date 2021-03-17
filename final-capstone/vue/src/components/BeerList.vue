<template>
  <div class="beers-list">
    <div class="transbox">
    <ul>
      <li v-for="beer in beers" v-bind:key="beer.id" class="brewery-beers">
        <router-link v-bind:to="{ name: 'brewery', params: { id: brewery.id } }">
          {{ beer.name }}</router-link>
         {{ beer.info }} | {{ beer.abv }} | {{ beer.type }} <button v-on:click= "breweryService.deleteBeer(beer.id)" v-if='isBrewer'>Delete</button>
      </li>
    </ul>
    <router-link v-bind:to="{name:'add-beer', params: {id: brewery.id}}" v-if='isBrewer'>Add A Beer</router-link>
    </div>
  </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
export default {
  name: "beer-list",
  data() {
    return {
      brewery: {},
      beers: [],
    };
  },
  computed: {
    isBrewer(){
      if(this.$store.state.token != '') {
            const roles = this.$store.state.user.authorities;
            return roles.filter(role => role.name === "ROLE_BREWER").length > 0 ;
        } else {
            return false;
        }
    }
  },

  created() {
    breweryService.getBreweriesById(this.$route.params.id).then((response) => {
      this.brewery = response.data;
    });
    breweryService.getBeerByBrewery(this.$route.params.id).then((response) => {
      this.beers = response.data;
    });
  },
};
</script>

<style>
</style>