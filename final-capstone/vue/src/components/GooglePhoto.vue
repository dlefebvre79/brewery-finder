<template>
  <div>
      <img v-bind:src="photo.getUrl({maxHeight: 300, maxWidth: 300})"><br>
      <span>Photo credit: </span>
      <div class="credit"
        v-for="credit in photo.html_attributions"
        v-bind:key="credit.id"
        v-html="credit"
      ></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      api: 'https://maps.googleapis.com/maps/api/place',
      apiKey: 'AIzaSyDnd_boDh3SsvljQrH7KTI5hatCNW1ZzYA',
      photoPath: '/photo?maxheight=300&photoreference='
    }
  },
  props: {
    photo: {
      html_attributions: [],
      photo_reference: ""
    },
    height: {}
  },
  computed: {
    imageUrl() {
     return this.api + this.photoPath + this.photo.photo_reference + "&key=" + this.apiKey;
        // return this.photo.getUrl + "({maxWidth: 300, maxHeight: 300})";
    }
  }

}
</script>

<style>
.credit{
  display: inline;
}
</style>
