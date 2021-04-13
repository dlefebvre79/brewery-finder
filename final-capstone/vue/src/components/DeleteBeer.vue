<template>
<div class="transbox">
<form v-on:submit.prevent>
    <h1 class="h3 mb-3 font-weight-normal">Delete Beer</h1>
    <table id="add-beer">
     <br />
      <div class="field">
      <tr>
        <td class="left">   
      <label for="name">Name </label>
        </td>
        <td class="right">         
      <input type="text" v-model="beer.name" />
      </td>
      </tr>
    </div>
    <br />
    <div class="field">
      <tr>
        <td class="left"> 
      <label for="beerId">Beer Id</label>
        </td>
        <td class="right">
      <input type="text" v-model="beer.id" />
        </td>
      </tr>
    </div>
    <br />
    <div class="actions">
      <button type="button" class="btn" v-on:click="cancel()">Cancel</button>&nbsp;
      <button type="submit" class ="btn" v-on:click="deleteBeer()">Delete Beer</button>
    </div>

    </table>
  
  </form>
</div>
</template>

<script>


import breweryService from '@/services/BreweryService';

export default {
  name: "delete-beer",
  data() {
    return {
      beer: {
        id: "",
        name: ""
        
      }
    };
  },
  methods: {
    deleteBeer() {
      this.beer.breweryId = this.$route.params.id
      breweryService
          .deleteBeer(this.beer.id)
          .then(()=>{
            this.reloadBeer();
            this.clearForm();
            // if (response.status === 201){
            //   this.$router.push("/");
            // }
          })
    },
    reloadBeer(){
      breweryService.getBeerByBrewery(this.$route.params.id).then((response) => {
        this.$store.commit('LOAD_BEER', response.data) ;
        this.$router.push(`/brewery/${this.$route.params.id}`)
      });
    },
     clearForm() {
      this.beer = {
        id: "",
        name: ""
      };
     },
    cancel() {
      
    }
  }
};
</script>