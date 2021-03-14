<template>
<form v-on:submit.prevent>
     <div class = "delete beer"> Delete Beer
    <div class="field">
      <label for="name">Name</label>
      <input type="text" v-model="beer.name" />
    </div>
    <div class="actions">
      <button type="button" v-on:click="cancel()">Cancel</button>&nbsp;
      <button type="submit" v-on:click="deleteBeer()">Delete Beer</button>
    </div>
    </div>
     
  </form>
</template>

<script>


import breweryService from '@/services/BreweryService';

export default {
  name: "delete-beer",
  data() {
    return {
      beer: {
        id: "",
        name: "",
        breweryId: ""
      }
    };
  },
  methods: {
    deleteBeer() {
      this.beer.breweryId = this.$route.params.id
      breweryService
          .deleteBeerByBrewery(this.beer)
          .then((response)=>{
            if (response.status === 201){
              this.$router.push("/");
            }
          })
    },
    cancel() {
      this.$router.push("/");
    }
  }
};
</script>