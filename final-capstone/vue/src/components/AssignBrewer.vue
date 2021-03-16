<template>
  <div>
    <div class="transbox" id="brewery-update-container">
      <h3 class="h3 mb-3 font-weight-normal plain-text">Assign Brewers</h3>
      <table class="brewery-table">
        <th class="brewery-header">Brewery</th>
        <th class="brewery-header">Brewer</th>
        <th></th>
        <tr v-for="brewery in breweries" v-bind:key="brewery.id">
          <td class="plain-text">
            {{ brewery.name }}
          </td>
          <td class="plain-text">{{ brewery.brewer.firstName }} {{ brewery.brewer.lastName }}</td>
          <td>
            <select
              name="brewer-list"
              id="brewer-list"
              v-model="brewery.brewer"
            >
              <option
                v-for="brewer in brewers"
                v-bind:key="brewer.id"
                v-bind:value="brewer"
                >{{ brewer.firstName }} {{ brewer.lastName }}
              </option>
            </select>
          </td>
          <td>
            <button
              name="save-brewery"
              id="save-brewery"
              v-on:click="updateBrewery(brewery)"
            >
              Save Brewery
            </button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import userService from "@/services/UserService";
import breweryService from "@/services/BreweryService";

export default {
  name: "assign-brewer",
  data() {
    return {
      selectedBrewer: null,
      brewers: [],
      breweries: [],
    };
  },
  computed: {
    options() {
      return Object.keys(this.brewers).map((k) => {
        let b = this.brewers[k];
        return `${b.firstName} ${b.lastName}`;
      });
    },
  },
  created() {
    this.getBrewers();
    this.getBreweries();
  },
  methods: {
    updateBrewery(brewery) {
      breweryService.update(brewery).then((response) => {
        this.$store.commit("SET_CURRENT_BREWERY", response.data);
        this.$emit("refreshBreweries");
      });
      //   breweryService.update(brewery);
      //   this.$emit("refreshBreweries");
    },
    getBreweries() {
      breweryService.list().then((response) => {
        let sortedBreweries = breweryService.sortBreweries(response.data);
        this.breweries = sortedBreweries;
      });
    },
    getBrewers() {
      userService.findAllBrewers().then((response) => {
        this.brewers = response.data;
      });
    },
  },
};
</script>

<style>
.plain-text {
    color: darkblue;
}    
</style>
