<template>
  <div>
    <h1 class="h3 mb-3 font-weight-normal">Add A Review</h1>
  <form v-on:submit.prevent v-if="$store.state.user.id">
    
      <title>Add A Review To A Beer Of Your Choice</title>
   <table id="add-review">
     <br >   
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
      <input type="number" min="1" max="5" v-model="reviews.rating" />
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
  <div v-else>Please Login To Review A Beer</div>
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
      // get the userId from the store
      this.reviews.userId = this.$store.state.user.id;
      breweryService
          .addReviewByBeerId(this.reviews)
          .then(()=>{
            //reload reviews 
            this.reloadReviews();
            this.clearForm();
          })
    },
    reloadReviews() {
      breweryService.getReviewsByBeerId(this.$route.params.id).then((response) => {
        this.$store.commit('LOAD_REVIEWS', response.data)
      });
    },
    clearForm() {
      this.reviews = {
          id: "",
          beerId: "",
          beerName: "",
          userId: "",
          subjectTitle: "",
          review: "",
          rating: ""
        };
    },
    cancel() {
     
    }
  }
};
</script>

<style>
table#add-review {
  text-align: center;
  margin: 0 auto;
}
.left {
    width: 150px;
  text-orientation: sideways-right;
}
.right {
    width: 180px;
  text-align: start;
}

</style>
