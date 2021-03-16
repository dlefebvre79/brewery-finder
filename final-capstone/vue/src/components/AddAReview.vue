<template>
  <div class="transbox">
  <form v-on:submit.prevent>
    <h1 class="h3 mb-3 font-weight-normal">Add A Review</h1>
      <title>Add A Review To A Beer Of Your Choice</title>
   <table id="add-review">
     <br >   
    <div class="field">
      <tr>
       <td class="left"> 
        <label for="beer-review">Beer-ID</label>
       </td> 
       <td class="right"> 
      <input type="number" v-model="reviews.beerId" />
       </td> 
      </tr>
    </div>
    <br />
    <div class="field">
      <tr>
       <td class="left"> 
      <label for="user-review-number">User-Id Number</label>
      </td>
      <td class="right"> 
      <input type="number" v-model="reviews.userId" />
      </td>
      </tr>
    </div>
    <br />
    <div class="field">
      <tr>
       <td class="left">      
      <label for="review-beer-name">Beer Name</label>
       </td>
       <td class="right"> 
      <input type="text" v-model="reviews.beerName" />
      </td>
      </tr>      
    </div>
    <div class="field">
      <tr>
       <td class="left">      
      <label for="review-title">Review Title</label>
       </td>
       <td class="right"> 
      <input type="text" v-model="reviews.subjectTitle" />
      </td>
      </tr>      
    </div>
    <br />
    <div class="field">
      <tr>
       <td class="left">       
      <label for="review">Review</label>
       </td>
       <td class="right">      
      <textarea spellcheck="false" v-model="reviews.review" />
      </td>
      </tr>       
    </div>
    <br />
    <div class="field">
      <tr>
       <td class="left">       
      <label for="rating">Rating</label>
       </td>
       <td class="right">      
      <input type="number" v-model="reviews.rating" />
      </td>
      </tr>          
    </div>
   </table>
   <br />
    <div class="actions">
      <button type="button" class="btn" v-on:click="cancel()">Cancel</button>&nbsp;
      <button type="submit" class="btn" v-on:click="saveReview()">Save Review</button>
    </div>
  </form>
  </div>
</template>

<script>


import breweryService from '@/services/BreweryService';

export default {
  name: "add-review",
  data() {
    return {
      reviews: {
        id: "",
        beerId: "",
        beerName: "",
        userId: "",
        subjectTitle: "",
        review: "",
        rating: ""
         }
    };
  },
  methods: {
    saveReview() {
      this.reviews.beerId = this.$route.params.id
      breweryService
          .addReviewByBeerId(this.reviews)
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