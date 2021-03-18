<template>
  <div class="beers-list">
    <div class="transbox">
    <ul>
      <li v-for="beer in beers" v-bind:key="beer.id" class="brewery-beers">
        <router-link v-bind:to="{ name: 'beer-information', params: { id: beer.id } }">
          {{ beer.name }}</router-link>
         {{ beer.info }} | {{ beer.abv }} | {{ beer.type }}&nbsp;<a href="#" class="delete-link" v-on:click.prevent="deleteBeer(beer.id)" v-if='isBreweryAuthorized(brewery.brewer.id)'>x</a>
      </li>
    </ul>
    <router-link v-bind:to="{name:'add-beer', params: {id: brewery.id}}" v-if='isBreweryAuthorized(brewery.brewer.id)'>Add A Beer</router-link>
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
      if(this.$store.state.token !== '') {
            const roles = this.$store.state.user.authorities;
            return roles.filter(role => role.name === "ROLE_BREWER").length > 0 ;
        } else {
            return false;
        }
    },
    breweryId() {
      return this.$route.params.id;
    }
  },
  methods: {
    isBreweryAuthorized(brewerId) {
      return this.isBrewer && this.$store.state.user.id === brewerId;
    },
    deleteBeer(beerId) {
      breweryService
          .deleteBeer(beerId)
          .then(()=>{
            this.reloadBeer();
          })
    },
    reloadBeer(){
      breweryService.getBeerByBrewery(this.breweryId).then((response) => {
        this.beers = response.data;
      });
    },
  },
  created() {
    breweryService.getBreweriesById(this.breweryId).then((response) => {
      this.brewery = response.data;
    });
    breweryService.getBeerByBrewery(this.breweryId).then((response) => {
      this.beers = response.data;
    });
  },
};
</script>

<style>
a.delete-link {
  color: red;
  font-weight: bold;
}
</style>
