<template>
  <div class="reviews-list">
    <div class="transbox">
    <ul>
      <li v-for="review in reviews" v-bind:key="review.id" class="beer-reviews">
        <router-link v-bind:to="{ name: 'reviews', params: { id: beer.id } }">
          {{ reviews.subjectTitle }}</router-link>
        | {{ reviews.beerId }} | {{ reviews.subjectTitle }} | {{ reviews.review }} | {{reviews.rating}} 
      </li>
    </ul>
    </div>
  </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
export default {
  name: "reviews-list",
  data() {
    return {
      reviews: [],
      beers: {}
    };
  },

  created() {
    breweryService.listAllReviews().then((response)=>{
      this.reviews = response.data;
    });
    breweryService.getReviewById(this.$route.params.id).then((response) => {
      this.reviews = response.data;
    });
    breweryService.getReviewsByBeerId(this.$route.params.id).then((response) => {
      this.beers = response.data;
    });
  },
};
</script>

<style>
</style>