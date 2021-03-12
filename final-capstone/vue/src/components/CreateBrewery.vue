<template>
    <div class="container">
    <div id="newBrewery" class="text-center">
      <form class="form-brewery" @submit.prevent="createBrewery">
        <h1 class="h3 mb-3 font-weight-normal">Create Brewery</h1>
        <label for="username" class="sr-only">Brewer Username  </label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Brewer Username"
          v-model="user.username"
          required
          autofocus
        />
        <br />
        <br />
        <label for="brewery-name" class="sr-only">Brewery Name  </label>
        <input
          type="text"
          id="brewereyName"
          class="form-control"
          placeholder="Brewery Name"
          v-model="brewery.name"
          required
        />
        <br />
        <br />
        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Create Brewery
        </button>
      </form>
    </div>
  </div>

</template>

<script>
import breweryService from '@/services/BreweryService';
import userService from '@/services/UserService';

export default {
    name: 'create-brewery',
    data() {
        return {
            user: {
                username: '',
                id: ''
            },
            brewery: {
                name: '',
                brewer: {}
            }
        }
    },
    methods: {
        createBrewery() {
            userService.getByUsername(this.user.username).then((response) => {
            this.brewery.brewer = response.data;
            });
            this.brewery.brewer.authorities = "";
            breweryService.create(this.brewery).then((response) => {
            this.brewery = response.data;
            });
        }
    },
}
</script>

<style>

</style>