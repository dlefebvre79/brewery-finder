<template>
  <div>
    <div v-if="brewery.id > 0" class="brewery-information">

      <div class="transbox" id="brewery-container">
        <h1 class="headline">{{ brewery.name }}</h1>

        <div id="details" class="brewery-details">
          <span id="address">{{ brewery.address }} | {{ brewery.city }} | {{ brewery.phoneNumber }}</span>
        </div>

        <div id="amenities">
            <div id="price">
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
            </div>
            <div id="features">
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
              <td class="days">{{ day }}</td>
              <td class="hours">{{ formatHours(brewery.hours[index]) }}</td>
            </tr>
          </table>
        </div>

        <div id="gphoto">
          <google-photo 
            v-bind:photo="$store.state.map.photos[randomPhoto()]" height="100%"/>
        </div>

        <div id="map">
          <gmap id="map" />
        </div>

        <div id="brewery-description">
          <h3>Description</h3>
          <p>{{ brewery.history }}</p>
          <p>Atmosphere: {{ brewery.atmosphere }}</p>
          <p>Google Rating: {{ $store.state.map.rating }}</p>
        </div>

        <div id="beer-list">
          <h3>Beer List</h3>
          <ul>
            <li
              v-for="beer in beers"
              v-bind:key="beer.id"
              class="brewery-beers"
            >
              <router-link
                v-bind:to="{
                  name: 'beer-information',
                  params: { id: beer.id },
                }"
              >
                {{ beer.name }}</router-link
            > ({{ beer.type }}, {{ beer.abv }}% ABV, {{ beer.ibu }} IBU)
            </li>
          </ul>
          <router-link v-bind:to="{name:'add-beer', params: {id: brewery.id}}" v-if='isBrewer'>Add A Beer</router-link>
   
        </div>

      </div>

    </div>

  </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
import gmap from "@/components/Map";
import GooglePhoto from "@/components/GooglePhoto.vue";

export default {
  name: "brewery-information",
  components: {
    gmap,
    GooglePhoto,
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
        active: "",
      },
      beers: [],
      photoId: 0,
    };
  },
  computed: {
    isBrewer(){
        if(this.$store.state.token != '') {
            const roles = this.$store.state.user.authorities;
            return roles.filter(role => role.name === "ROLE_BREWER").length > 0 ;
        } else {
            return false;
        }
    }
  },
  created() {
    breweryService.getBreweriesById(this.$route.params.id).then((response) => {
      this.brewery = response.data;
      this.sortDaysHours();
      this.$store.commit("SET_MAP_TARGET", this.brewery.name);
    });
    breweryService.getBeerByBrewery(this.$route.params.id).then((response) => {
      this.beers = response.data;
    });
  },
  methods: {
    formatHours(hours) {
      const hoursArray = hours.split("-");
      const op = hoursArray[0];
      const cl = hoursArray[1];
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
    randomPhoto() {
        return Math.floor(Math.random() * (10));
    },
    sortDaysHours() {
      let days = [];
      let hours = [];
      let index = this.brewery.daysOpen.findIndex((day) =>
        /^Monday/i.test(day)
      );
      if (index >= 0) {
        days.push("Monday");
        hours.push(this.brewery.hours[index]);
      }
      index = this.brewery.daysOpen.findIndex((day) => /^Tuesday/i.test(day));
      if (index >= 0) {
        days.push("Tuesday");
        hours.push(this.brewery.hours[index]);
      }
      index = this.brewery.daysOpen.findIndex((day) => /^Wednesday/i.test(day));
      if (index >= 0) {
        days.push("Wednesday");
        hours.push(this.brewery.hours[index]);
      }
      index = this.brewery.daysOpen.findIndex((day) => /^Thursday/i.test(day));
      if (index >= 0) {
        days.push("Thursday");
        hours.push(this.brewery.hours[index]);
      }
      index = this.brewery.daysOpen.findIndex((day) => /^Friday/i.test(day));
      if (index >= 0) {
        days.push("Friday");
        hours.push(this.brewery.hours[index]);
      }
      index = this.brewery.daysOpen.findIndex((day) => /^Saturday/i.test(day));
      if (index >= 0) {
        days.push("Saturday");
        hours.push(this.brewery.hours[index]);
      }
      index = this.brewery.daysOpen.findIndex((day) => /^Sunday/i.test(day));
      if (index >= 0) {
        days.push("Sunday");
        hours.push(this.brewery.hours[index]);
      }
      this.brewery.daysOpen = days;
      this.brewery.hours = hours;
    },
  },
};
</script>

<style>

#brewery-container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    column-gap: 20px;
    grid-template-areas: 
    "headline headline"
    "details details"
    "hours photo"
    "amenities photo"
    "beers description"
    "map description"
    ;
}

.headline {
    grid-area: headline;
}

#details {
    grid-area: details;
    display: flex;
    flex-direction: row;
    justify-content: center;
    
}

#hours {
    grid-area: hours;
}

#amenities {
    grid-area: amenities;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

#price {
    grid-area: price;
}

#features {
    grid-area: features;
}

#gphoto {
    grid-area: photo;
}

#map {
    grid-area: map;
}

#brewery-description {
    grid-area: description;
}

#beer-list {
    grid-area: beers;
}



#amenities img {
  width: 25px;
  filter: invert() sepia(100%) saturate(100%) brightness(0.65);
}

#amenities img.active {
  filter: sepia(100%) saturate(900%) brightness(0.55);
}

#features {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
}

#map {
  height: 250px;
}

p {
    text-align: left;
}

.days {
    text-align: left;
}

.hours {
    text-align: right;
}

ul {
    list-style: none;
}

li {
    text-align: left;

}
/* #brewery-container {
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

.amenities {
    display: flex;
    grid-area: amenities;
    flex-direction: row;
    justify-content:center;
    max-width: 500px;
    
    
} 

.beer-list {
  display: grid;
  grid-area: beer-list;
}

.brewery-description {
  display: grid;
  grid-area: brewery-description;
}

li {
  display: grid;
  justify-content: left;
} */
</style>
