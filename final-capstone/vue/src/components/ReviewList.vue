<template>
  <div class="reviews-list">
    <h3>Reviews</h3>
    <ul>
      <li v-for="review in reviews" v-bind:key="review.id" class="beer-reviews">
        <router-link v-bind:to="{ name: 'beer-information', params: { id: review.beerId } }">
          Rating: {{review.rating}} | {{ review.subjectTitle }} | {{ review.review }} 
         </router-link> 
      </li>
    </ul>

  </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
export default {
  name: "reviews-list",
  data() {
    return {
    };
  },

  created() {
   
    breweryService.getReviewsByBeerId(this.$route.params.id).then((response) => {
      this.$store.commit('LOAD_REVIEWS', response.data)
    });
  },
  computed: {
    reviews(){
      return this.$store.state.reviews;
    }
  }
};
</script>

<style>
</style>