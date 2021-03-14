<template>
  <div class="brewery-list">
    <div class="transbox" id="brewery-list-container">
      <table id="brewery-list-table">
        <th>Brewery Name</th>
        <th>Phone Number</th>
        <th>Address</th>
        <th>City</th>
        <tr
          v-for="brewery in breweries"
          v-bind:key="brewery.id"
          class="brewery"
        >
        <td><router-link
            v-bind:to="{ name: 'brewery', params: { id: brewery.id } }"
            class="breweries"
          >{{ brewery.name }}</router-link></td>
          <td>{{ brewery.phoneNumber }}</td>
          <td>{{ brewery.address }}</td>
          <td>{{ brewery.city }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
export default {
  name: "brewery-list",
  data() {
    return {
      breweries: [],
      sort: {
        name: "",
      },
    };
  },
  computed: {},
  created() {
    breweryService.list().then((response) => {
      this.breweries = response.data.sort((a, b) => {
        let nameA = a.name.toLowerCase();
        let nameB = b.name.toLowerCase();
        if (nameA < nameB) {
          return -1;
        } else if (nameA > nameB) {
          return 1;
        } else {
          return 0;
        }
      });
    });
  },
};
</script>

<style></style>
