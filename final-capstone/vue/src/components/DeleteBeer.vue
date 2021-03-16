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