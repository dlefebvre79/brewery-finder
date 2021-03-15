<template>
  <div class="container">
    <div class="transbox" id="brewery-list-container">
      <div id="newBrewery" class="text-center">
        <h3 class="h3 mb-3 font-weight-normal">Create New Brewery</h3>
        <form class="form-brewery" @submit.prevent="createBrewery">
          <table>
            <tr>
              <td class="left">
                <label for="username" class="sr-only">Brewer Username </label>
              </td>
              <td class="right">
                <input
                  type="text"
                  id="username"
                  class="form-control"
                  placeholder="Brewer Username"
                  v-model="user.username"
                  required
                  autofocus
                />
              </td>
            </tr>
            <tr>
              <td class="left">
                <label for="brewery-name" class="sr-only">Brewery Name </label>
              </td>
              <td class="right">
                <input
                  type="text"
                  id="brewery-name"
                  class="form-control"
                  placeholder="Brewery Name"
                  v-model="brewery.name"
                  required
                />
              </td>
            </tr>
          </table>

          <br />
          <button class="btn btn-lg btn-primary btn-block" type="submit">
            Create Brewery
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
import userService from "@/services/UserService";

export default {
  name: "create-brewery",
  data() {
    return {
      user: {
        username: "",
        id: "",
      },
      brewery: {
        name: "",
        brewer: {},
      },
    };
  },
  methods: {
    createBrewery() {
      userService.getByUsername(this.user.username).then((response) => {
        this.brewery.brewer = response.data;
        this.brewery.brewer.authorities = "";
        breweryService.create(this.brewery).then((response) => {
          this.brewery = response.data;
          this.loadBreweries();
        });
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

<style></style>
