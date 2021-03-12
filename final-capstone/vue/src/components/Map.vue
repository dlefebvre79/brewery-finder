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
  target: '',
  async mounted() {
    try {
      const google = await gmapsInit();
      //const geocoder = new google.maps.Geocoder();
      const map = new google.maps.Map(this.$el);
      const request = {
        query: this.target,
        fields: ["name", "geometry", "rating", "price_level"],
      };
      let infowindow = new google.maps.InfoWindow();
      let service = new google.maps.places.PlacesService(map);
      service.findPlaceFromQuery(request, (results, status) => {
          if(status === google.maps.places.PlacesServiceStatus.OK && results) {
              for(let i=0; i<results.length; i++) {
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
                  //console.log(results[i]);
              }
              map.setCenter(results[0].geometry.location);
              map.fitBounds(results[0].geometry.viewport);
              this.$store.commit('SET_GOOGLE_RATING', results[0].rating);
              this.$store.commit('SET_GOOGLE_PRICE', results[0].price_level);
            }
      });
    } catch (error) {
      console.error(error);
    }
  },
};
</script>

<style>
.gmap {
  height: 100vh;
  
}
</style>