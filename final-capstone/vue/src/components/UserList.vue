<template>
  <div class="container">
    <table id="tblUsers">
      <thead>
        <tr>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Username</th>
          <th>Email Address</th>
          <th>Role</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="user in users"
          v-bind:key="user.id"
          v-bind:class="{ disabled: user.active === false }"
        >
          <td>{{ user.firstName }}</td>
          <td>{{ user.lastName }}</td>
          <td>{{ user.username }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.authorities[0].name }}</td>
          <td>
            <div class="all-actions">
              <button v-on:click="changeRole(user, 'brewer')">Make Brewer</button>
              <button v-on:click="changeRole(user, 'user')">Make User</button>
              <button v-on:click="changeRole(user, 'admin')">Make Admin</button>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import userService from "@/services/UserService";

export default {
  name: "user-list",
  data() {
    return {
      users: [],
    };
  },
  computed: {},
  methods: {
    changeRole(user, role) {
      const jsonUser = {
        username: user.username,
      };
      userService.updateRole(jsonUser, role).then(() => {
        this.getUsers();
      });
    },
    getUsers() {
      userService.findAll().then((response) => {
        this.users = response.data;
      });
    },
  },
  created() {
    userService.findAll().then((response) => {
      this.users = response.data;
    });
  },
};
</script>

<style>
#tblUsers {

    background-color: rgba(255, 181, 22, 0.651);
}
</style>
