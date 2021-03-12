<template>
  <div class="container">
    <table id="tblUsers">
      <thead>
        <tr>
          <th>&nbsp;</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Username</th>
          <th>Email Address</th>
          <th>ZIP Code</th>
          <th>Birth Date</th>
          <th>Role</th>
          <th>Active</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <input
              type="checkbox"
              id="selectAll"
              v-model="allSelected"
              v-on:change="selectAll"
            />
          </td>
          <td>
            <input
              type="text"
              id="firstNameFilter"
              v-model="filter.firstName"
            />
          </td>
          <td>
            <input type="text" id="lastNameFilter" v-model="filter.lastName" />
          </td>
          <td>
            <input type="text" id="usernameFilter" v-model="filter.username" />
          </td>
          <td>
            <input type="text" id="emailFilter" v-model="filter.email" />
          </td>
          <td>
            <input type="text" id="zipFilter" v-model="filter.email" />
          </td>
          <td>
            <input type="text" id="birthdateFilter" v-model="filter.email" />
          </td>
          <td>
            <select id="statusFilter" v-model="filter.authorities[0].name">
              <option value>Show All</option>
              <option value="ROLE_USER">User</option>
              <option value="ROLE_BREWER">Brewer</option>
              <option value="ROLE_ADMIN">Admin</option>
            </select>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr
          v-for="user in filteredList"
          v-bind:key="user.id"
          v-bind:class="{ disabled: user.active === false }"
        >
          <td>
            <input
              type="checkbox"
              v-bind:id="user.id"
              v-bind:value="user.id"
              v-model="selectedUserIDs"
            />
          </td>
          <td>{{ user.firstName }}</td>
          <td>{{ user.lastName }}</td>
          <td>{{ user.username }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.zip }}</td>
          <td>{{ user.birthDate }}</td>
          <td>{{ user.authorities[0].name }}</td>
          <td>{{ user.active }}</td>
          <td>
            <button class="btnEnableDisable" v-on:click="makeBrewer(user)">
              {{ user.active == true ? "Disable" : "Enable" }}
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <div class="all-actions">
      <button
        :disabled="actionButtonDisabled"
        v-on:click="enableSelectedUsers()"
      >
        Enable Users
      </button>
      <button
        :disabled="actionButtonDisabled"
        v-on:click="disableSelectedUsers()"
      >
        Disable Users
      </button>
      <button
        :disabled="actionButtonDisabled"
        v-on:click="deleteSelectedUsers()"
      >
        Delete Users
      </button>
    </div>

    <button v-on:click="showForm = !showForm">Add New User</button>

    <form id="frmAddNewUser" v-show="showForm">
      <div class="field">
        <label for="firstName">First Name:</label>
        <input type="text" name="firstName" v-model="newUser.firstName" />
      </div>
      <div class="field">
        <label for="lastName">Last Name:</label>
        <input type="text" name="lastName" v-model="newUser.lastName" />
      </div>
      <div class="field">
        <label for="username">Username:</label>
        <input type="text" name="username" v-model="newUser.username" />
      </div>
      <div class="field">
        <label for="emailAddress">Email Address:</label>
        <input type="text" name="emailAddress" v-model="newUser.emailAddress" />
      </div>
      <button type="submit" class="btn save" v-on:click.prevent="saveUser">
        Save User
      </button>
    </form>
  </div>
</template>

<script>
import userService from "@/services/UserService";

export default {
  name: "user-list",
  data() {
    return {
      showForm: false,
      selectedUserIDs: [],
      filter: {
        firstName: "",
        lastName: "",
        username: "",
        email: "",
        zip: "",
        birthDate: "",
        subscribed: "",
        authorities: [
            {
                "name": "",
            }
        ],
        active: "",
      },
      newUser: {
        id: null,
        firstName: "",
        lastName: "",
        username: "",
        email: "",
        zip: "",
        birthDate: "",
        subscribed: "",
        authorities: [
            {
                "name": "",
            }
        ],
        active: "",
      },
      users: [],
    };
  },
  computed: {
    allSelected() {
      return this.selectedUserIDs.length === this.users.length;
    },
    filteredList() {
      let filteredUsers = this.users;
      if (this.filter.firstName != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.firstName
            .toLowerCase()
            .includes(this.filter.firstName.toLowerCase())
        );
      }
      if (this.filter.lastName != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.lastName
            .toLowerCase()
            .includes(this.filter.lastName.toLowerCase())
        );
      }
      if (this.filter.username != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.username
            .toLowerCase()
            .includes(this.filter.username.toLowerCase())
        );
      }
      if (this.filter.emailAddress != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.email
            .toLowerCase()
            .includes(this.filter.email.toLowerCase())
        );
      }
      if (this.filter.active != "") {
        filteredUsers = filteredUsers.filter(
          (user) => user.active === this.filter.active
        );
      }
      return filteredUsers;
    },
    actionButtonDisabled() {
      return this.selectedUserIDs.length === 0;
    },
  },
  methods: {
    saveUser() {
      this.users.push(this.newUser);
      this.newUser = {};
      this.showForm = false;
    },
    flipStatus(id) {
      this.users.forEach((user) => {
        if (user.id === id) {
          if (user.active == true) {
            user.active = false;
          } else {
            user.active = true;
          }
        }
      });
    },
    makeBrewer(user) {
        userService.updateRole(user);
    //     userService.findAll().then((response) => {
    //   this.users = response.data;
    },
    enableSelectedUsers() {
      this.users.forEach((user) => {
        if (this.selectedUserIDs.includes(user.id)) {
          user.active = true;
        }
      });
    },
    disableSelectedUsers() {
      this.users.forEach((user) => {
        if (this.selectedUserIDs.includes(user.id)) {
          user.active = false;
        }
      });
    },
    deleteSelectedUsers() {
      let newArray = this.users;
      if (this.selectedUserIDs.length > 0) {
        for (let i = 0; i < this.selectedUserIDs.length; i++) {
          newArray = newArray.filter((user) => {
            return user.id != this.selectedUserIDs[i];
          });
        }
      }
      this.users = newArray;
      this.selectedUserIDs = [];
    },
    selectAll() {
      if (this.allSelected) {
        this.selectedUserIDs = [];
      } else {
        this.users.forEach((user) => {
          if (!this.selectedUserIDs.includes(user.id)) {
            this.selectedUserIDs.push(user.id);
          }
        });
      }
    }
  },
  created() {
          userService.findAll().then((response) => {
      this.users = response.data;
    });
  }
};
</script>

<style scoped>
table {
  margin-top: 20px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  margin-bottom: 20px;
}
th {
  text-transform: uppercase;
}
td {
  padding: 10px;
}
tr.disabled {
  color: red;
}
input,
select {
  font-size: 16px;
}

form {
  margin: 20px;
  width: 350px;
}
.field {
  padding: 10px 0px;
}
label {
  width: 140px;
  display: inline-block;
}
button {
  margin-right: 5px;
}
.all-actions {
  margin-bottom: 40px;
}
.btn.save {
  margin: 20px;
  float: right;
}
</style>
