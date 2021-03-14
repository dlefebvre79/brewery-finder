<template>
  <div class="brewery-list">
    <div class="transbox" id="brewery-list-container">
      <table id="brewery-list-table">
        <th>Brewery Name</th>
        <th>Phone Number</th>
        <th>Address</th>
        <th>City</th>
        <th v-if="isAdmin">Brewer</th>
        <th v-if="isAdmin"></th>
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
          </td>
        </tr>
      </table>
    </div>
    <create-brewery v-if="isAdmin === true" />
  </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
import createBrewery from "@/components/CreateBrewery.vue";
export default {
  components: {
    createBrewery,
  },
  name: "brewery-list",
  data() {
    return {};
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
  },
  created() {
    breweryService.list().then((response) => {
      let sortedBreweries = breweryService.sortBreweries(response.data);
      this.$store.commit("LOAD_BREWERIES", sortedBreweries);
    });
  },
  methods: {
    deleteBrewery(id) {
      breweryService.deleteBrewery(id).then(() => {
        this.loadBreweries();
      });
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
