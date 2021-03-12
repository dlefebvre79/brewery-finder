<template>

  <form v-on:submit.prevent>
      <title>Add A Beer To A Brewery Of Your Choice</title>
      <div>
    <!-- <div class="field">
      <label for="name">Name</label>
      <input type="text" v-model="beer.name" />
    </div>
    <div class="field">
      <label for="type">Type</label>
      <input type="text" v-model="beer.type" />
    </div>
    <div class="field">
      <label for="info">Description</label>
      <textarea spellcheck="false" v-model="beer.info" />
    </div>
    <div class="field">
      <label for="ABV">ABV</label>
      <input type="text" v-model="beer.abv" />
    </div>
    <div class="field">
      <label for="IBU">IBU</label>
      <input type="text" v-model="beer.ibu" />
    </div>
    <div class="actions">
      <button type="button" v-on:click="cancel()">Cancel</button>&nbsp;
      <button type="submit" v-on:click="saveBeer()">Save Beer</button> -->
    </div>
  </form>
</template>

<script>


import breweryService from '@/services/BreweryService';

export default {
  name: "add-beer",
  data() {
    return {
      beer: {
        id: "",
        name: "",
        type: "",
        info: "",
        abv: "",
        ibu: ""
      }
    };
  },
  methods: {
    saveBeer() {
      breweryService
          .addBeerByBrewery(this.beer.id)
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