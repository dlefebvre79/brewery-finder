<template>
  <div class="gmap"></div>
</template>

<script>
import gmapsInit from "@/utils/gmaps";

export default {
  created() {
    this.target = this.$store.state.map.location;
  },
  name: "gmap",
  target: "",
  async mounted() {
    const google = await gmapsInit();
    //const geocoder = new google.maps.Geocoder();
    const map = new google.maps.Map(this.$el);
    let request = {
      query: this.target,
      fields: ["name", "geometry", "rating", "price_level", "place_id"],
    };
    let infowindow = new google.maps.InfoWindow();
    let service = new google.maps.places.PlacesService(map);
    service.findPlaceFromQuery(request, (results, status) => {
      if (status === google.maps.places.PlacesServiceStatus.OK && results) {
        for (let i = 0; i < results.length; i++) {
          let place = results[i];
          if (!place.geometry || !place.geometry.location) return;
          const marker = new google.maps.Marker({
            map,
            position: place.geometry.location,
          });
          google.maps.event.addListener(marker, "click", () => {
            infowindow.setContent(place.name || "");
            infowindow.open(map);
          });
          map.setCenter(results[0].geometry.location);
          map.fitBounds(results[0].geometry.viewport);

          this.$store.commit("SET_GOOGLE_PLACE", results[0].place_id);
          this.$store.commit("SET_GOOGLE_RATING", results[0].rating);
          this.$store.commit("SET_GOOGLE_PRICE", results[0].price_level);

        //   let requestDetails = {
        //     placeId: this.$store.state.map.placeId,
        //   };
          //service = new google.maps.places.PlacesService(map);
        //   service.getDetails(requestDetails, (placeDetails, statusDetails) => {
        //     if (statusDetails === google.maps.places.PlacesServiceStatus.OK) {
        //       let photos = [];
        //       placeDetails.photos.forEach((photo) => {
        //         photos.push(photo);
        //         console.log(photo.getUrl({ maxWidth: 500, maxHeight: 500 }));
        //       });
        //       console.log(place);
        //       this.$store.commit("SET_GOOGLE_PHOTOS", photos);
        //     }
        //   });
        }
      }
    });
  },
};
</script>

<style>
.gmap {
  height: 100vh;
}
</style>
