<template>
  <div class="brewery-list">
    <div class="transbox" id="brewery-list-container">
      <table id="brewery-list-table">
        <th class="brewery-header">Brewery Name</th>
        <th class="brewery-header">Phone Number</th>
        <th class="brewery-header">Address</th>
        <th class="brewery-header">City</th>
        <th class="brewery-header" v-if="isAdmin">Brewer</th>
        <th class="brewery-header" v-if="isAdmin"></th>
        <th class="brewery-header" v-if="isBrewer"></th>
        <tr
          v-for="brewery in breweries"
          v-bind:key="brewery.id"
          class="brewery"
        >
          <td>
            <router-link
              v-bind:to="{ name: 'brewery', params: { id: brewery.id } }"
              class="breweries"
              >{{ brewery.name }}</router-link
            >
          </td>
          <td>{{ brewery.phoneNumber }}</td>
          <td>{{ brewery.address }}</td>
          <td>{{ brewery.city }}</td>
          <td v-if="isAdmin">
            {{ brewery.brewer.firstName }} {{ brewery.brewer.lastName }}
          </td>
          <td v-if="isAdmin">
            <button
              class="btn btn-lg btn-primary btn-block"
              v-on:click="deleteBrewery(brewery.id)"
            >
              Delete
            </button>
          <td v-if="isBrewer && $store.state.user.id === brewery.brewer.id">
            <button
              class="btn btn-lg btn-primary btn-block"
              v-on:click="showUpdateForm(brewery)"
            >
              Update
            </button>
          </td>
        </tr>
      </table>
    </div>
    <assign-brewer v-if="isAdmin" @refreshBreweries="loadBreweries()"/>
    <create-brewery v-if="isAdmin" />
    <update-brewery v-on:hideForm="showForm = false" v-if="isBrewer && showForm"/>
  </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
import createBrewery from "@/components/CreateBrewery.vue";
import updateBrewery from "@/components/UpdateBrewery.vue";
import userService from "@/services/UserService";
import assignBrewer from "@/components/AssignBrewer.vue";

export default {
  components: {
    createBrewery,
    updateBrewery,
    assignBrewer,
  },
  name: "brewery-list",
  brewers: [],
  data() {
    return {
        showForm: false,
        currentBrewery: {},
    };
  },
  computed: {
    breweries() {
      return this.$store.state.breweries;
    },
    isAdmin() {
      if (this.$store.state.token != "") {
        for (let i = 0; i < this.$store.state.user.authorities.length; i++) {
          if (this.$store.state.user.authorities[i].name === "ROLE_ADMIN") {
            return true;
          }
        }
      }
      return false;
    },
    isBrewer() {
      if (this.$store.state.token != "") {
        for (let i = 0; i < this.$store.state.user.authorities.length; i++) {
          if (this.$store.state.user.authorities[i].name === "ROLE_BREWER") {
            return true;
          }
        }
      }
      return false;
    },
  },
  created() {
    userService.findAllBrewers().then((response) => {
      this.brewers = response.data;
    });
    breweryService.list().then((response) => {
      let sortedBreweries = breweryService.sortBreweries(response.data);
      sortedBreweries.forEach(brewery => {
          for(let i = 0; i < brewery.daysOpen.length; i++) {
              brewery.daysOpen[i] = brewery.daysOpen[i].trim();
          }
          for(let i = 0; i < brewery.hours.length; i++) {
              brewery.hours[i] = brewery.hours[i].trim();
          }

      });
      this.$store.commit("LOAD_BREWERIES", sortedBreweries);
    });
  },
  methods: {
    deleteBrewery(id) {
      breweryService.deleteBrewery(id).then(() => {
        this.loadBreweries();
      });
    },
    showUpdateForm(brewery) {
        this.$store.commit('SET_CURRENT_BREWERY', brewery)
        this.showForm = true;
    },
    loadBreweries() {
      breweryService.list().then((response) => {
        let sortedBreweries = breweryService.sortBreweries(response.data);
        this.$store.commit("LOAD_BREWERIES", sortedBreweries);
      });
    },
  },
};
</script>

<style>
#brewery-list-container {
  display: flex;
  justify-content: center;
}
</style>
