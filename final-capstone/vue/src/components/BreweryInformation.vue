<template>
  <div>
    <div v-if="brewery.id > 0" class="brewery-information">
    <div class="transbox" id="brewery-container">
      <h1 class="headline">{{ brewery.name }}</h1>
      <div class="brewery-details">
        <p id="address">{{ brewery.address }}</p>
        <p id="city">{{ brewery.city }}</p>
        <p id="phone">{{ brewery.phoneNumber }}</p>
        <div class="amenities">
        <img
          v-bind:class="{ active: $store.state.map.price.length > 0 }"
          src="@/assets/images/icons/tabler-icon-currency-dollar.png"
          alt="price level"
        />
        <img
          v-bind:class="{ active: $store.state.map.price.length > 1 }"
          src="@/assets/images/icons/tabler-icon-currency-dollar.png"
          alt="price level"
        />
        <img
          v-bind:class="{ active: $store.state.map.price.length > 2 }"
          src="@/assets/images/icons/tabler-icon-currency-dollar.png"
          alt="price level"
        />
        <img
          v-bind:class="{ active: $store.state.map.price.length > 3 }"
          src="@/assets/images/icons/tabler-icon-currency-dollar.png"
          alt="price level"
        />
        <img
          v-bind:class="{ active: $store.state.map.price.length > 4 }"
          src="@/assets/images/icons/tabler-icon-currency-dollar.png"
          alt="price level"
        />
        <br />
        <img
          v-bind:class="{ active: brewery.familyFriendly == true }"
          src="@/assets/images/icons/tabler-icon-mood-kid.png"
          alt="family friendly"
        />
        <img
          v-bind:class="{ active: brewery.patio == true }"
          src="@/assets/images/icons/tabler-icon-trees.png"
          alt="patio"
        />
        <img
          v-bind:class="{ active: brewery.food == true }"
          src="@/assets/images/icons/tabler-icon-pizza.png"
          alt="food"
        />
      </div>

      </div>
      <div id="hours">
        <table class="brewery-table">
          <th colspan="2">Hours of Operation:</th>
          <tr
            v-for="(day, index) in brewery.daysOpen"
            v-bind:key="day.id"
            class="table"
          >
            <td>{{ day }}</td>
            <td>{{ formatHours(brewery.hours[index]) }}</td>
          </tr>
        </table>
        <br />
        <br />
      </div>

      <div class="map">
        <gmap id="map" />
      </div>

      <div class="brewery-description">
        <p>{{ brewery.history }}</p>
        <p>{{ brewery.atmosphere }}</p>
        <p>Google Rating: {{ $store.state.map.rating }}</p>
      </div>
        <div class="beer-list">
        <ul>
            <li v-for="beer in beers" v-bind:key="beer.id" class="brewery-beers">
            <router-link
                v-bind:to="{ name: 'beer-information', params: { id: beer.id } }"
            >
                {{ beer.name }}</router-link
            >
            | {{ beer.info }} | {{ beer.abv }} | {{ beer.type }}
            </li>
        </ul>
      </div>
    </div>
  </div>
    </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
import gmap from "@/components/Map";

export default {
  name: "brewery-information",
  components: {
    gmap,
  },
  data() {
    return {
      brewery: {
        id: 0,
        name: "",
        address: "",
        city: "",
        zip: "",
        phoneNumber: "",
        daysOpen: [],
        hours: [],
        history: "",
        atmosphere: "",
        familyFriendly: "",
        patio: "",
        food: "",
        isActive: "",
      },
      beers: [],
    };
  },
  created() {
    breweryService.getBreweriesById(this.$route.params.id).then((response) => {
      this.brewery = response.data;
      this.$store.commit("SET_MAP_TARGET", this.brewery.name);
    });
    breweryService.getBeerByBrewery(this.$route.params.id).then((response) => {
      this.beers = response.data;
    });
  },
  methods: {
    formatHours(hours) {
      const hoursArray = hours.split("-");
      const op =
        hoursArray[0].substring(0, 2) + ":" + hoursArray[0].substring(2);
      const cl =
        hoursArray[1].substring(0, 2) + ":" + hoursArray[1].substring(2);
      const open = new Date("01-01-1970 " + op).toLocaleTimeString("en-US", {
        hour: "2-digit",
        minute: "2-digit",
      });
      const close = new Date("01-01-1970 " + cl).toLocaleTimeString("en-US", {
        hour: "2-digit",
        minute: "2-digit",
      });
      return open + " - " + close;
    },
  },
};
</script>


<style>

#brewery-container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 20px 50px;
    grid-template-areas: 
        "headline           headline"
        "brewery-hours      map"
        "beer-list          brewery-details"
        "beer-list          brewery-description";
    align-items: center;
}

.headline {
    display: grid;
    grid-area: headline;
}

.brewery-details {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas: 
        "    address  city  phone   "
        "amenities amenities amenities";
    grid-area: brewery-details;
}

.map {
    height: 250px;
    grid-area: map;
}

#map {
    height: 250px;
}

.amenities {
    display: flex;
    grid-area: amenities;
    flex-direction: row;
    justify-content:center;
    max-width: 500px;
}

.amenities > img {
  width: 25px;
  filter: invert() sepia(100%) saturate(100%) brightness(0.25);
}

.beer-list {
    display: grid;
    grid-area: beer-list;
}

.brewery-description {
    display: grid;
    grid-area: brewery-description;
}

img.active {
  filter: invert() sepia(100%) saturate(100%) brightness(1);
}
</style>