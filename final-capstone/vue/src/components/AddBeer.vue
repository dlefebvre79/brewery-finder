<template>
  <div class="transbox">
  <h1 class="page-title">Add A Beer</h1>
  <form v-on:submit.prevent>
    
      <title>Add A Beer To A Brewery Of Your Choice</title>
   <table id="add-beer">
     <br >   
    <div class="field">
      <tr>
       <td class="left"> 
        <label for="name">Name</label>
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
      <label for="type">Type</label>
      </td>
      <td class="right"> 
      <input type="text" v-model="beer.type" />
      </td>
      </tr>
    </div>
    <br />
    <div class="field">
      <tr>
       <td class="left">      
      <label for="info">Description</label>
       </td>
       <td class="right"> 
      <textarea spellcheck="false" v-model="beer.info" />
      </td>
      </tr>      
    </div>
    <br />
    <div class="field">
      <tr>
       <td class="left">       
      <label for="ABV">ABV</label>
       </td>
       <td class="right">      
      <input type="text" v-model="beer.abv" />
      </td>
      </tr>       
    </div>
    <br />
    <div class="field">
      <tr>
       <td class="left">       
      <label for="IBU">IBU</label>
       </td>
       <td class="right">      
      <input type="number" v-model="beer.ibu" />
      </td>
      </tr>          
    </div>
   </table>
   <br />
    <div class="actions">
      <button type="button" class="btn" v-on:click="cancel()">Cancel</button>&nbsp;
      <button type="submit" class="btn" v-on:click="saveBeer()">Save Beer</button>
    </div>
  </form>
  </div>
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
        ibu: "",
        breweryId: ""
      }
    };
  },
  methods: {
    saveBeer() {
      this.beer.breweryId = this.$route.params.id
      breweryService
          .addBeerByBrewery(this.beer)
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
        name: "",
        type: "",
        info: "",
        abv: "",
        ibu: "",
        breweryId: ""
      };
    },
    cancel() {
      window.history.go(-1);
    }
  }
};
</script>

<style>
table#add-beer {
  text-align: center;
  margin: 0 auto;
}
.left {
    width: 150px;
  text-align: start;
}
.right {
    width: 180px;
  text-align: start;
}
</style>
