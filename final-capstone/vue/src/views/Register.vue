<template>
  <div class="transbox">
    <div id="register" class="text-center">
      <form class="form-register" @submit.prevent="register">
        <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
        <div class="alert alert-danger" role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <table id="registration-table">
          <tr>
            <td class="left">
              <label for="username" class="sr-only">Username </label>
            </td>
            <td class="right">
              <input
                type="text"
                id="username"
                class="form-control"
                placeholder="Username"
                v-model="user.username"
                required
                autofocus
              />
            </td>
          </tr>
          <br />
          <tr>
            <td class="left">
              <label for="password" class="sr-only" id="check">Password </label>
            </td>
            <td class="right">
              <input
                type="password"
                id="password"
                class="form-control"
                placeholder="Password"
                v-model="user.password"
                required
                v-on:keyup="passwordCheck"
              />
            </td>
          </tr>
          <br />
          <tr>
            <td class="left">
              <label for="Confirm" class="sr-only">{{this.user.password != this.user.confirmPassword ? "Confirm Password" : "Passwords Match"}} </label>
            </td>
            <td class="right">
              <input
                type="password"
                id="confirmPassword"
                class="form-control"
                placeholder="Confirm Password"
                v-model="user.confirmPassword"
                required
              />
            </td>
          </tr>
          <br />
          <tr>
            <td class="left">
              <label for="first-name" class="sr-only">First Name </label>
            </td>
            <td class="right">
              <input
                type="first-name"
                id="firstName"
                class="form-control"
                placeholder="First Name"
                v-model="user.firstName"
                required
              />
            </td>
          </tr>
          <br />
          <tr>
            <td class="left">
              <label for="last-name" class="sr-only">Last Name </label>
            </td>
            <td class="right">
              <input
                type="last-name"
                id="lastName"
                class="form-control"
                placeholder="Last Name"
                v-model="user.lastName"
                required
              />
            </td>
          </tr>
          <br />
          <tr>
            <td class="left">
              <label for="email-address" class="sr-only">Email Address </label>
            </td>
            <td class="right">
              <input
                type="email-address"
                id="emailAddress"
                class="form-control"
                placeholder="Email Address"
                v-model="user.email"
                required
              />
            </td>
          </tr>
          <br />
          <tr>
            <td class="left">
              <label for="birth-date" class="sr-only">Birth Date </label>
            </td>
            <td class="right">
              <input
                type="date"
                id="birthDate"
                v-bind:max="legalBday"
                class="form-control"
                placeholder="yyyy-mm-dd"
                v-model="user.birthDate"
                required
              />
            </td>
          </tr>
          <br />
          <tr>
            <td class="left">
              <label for="zip-code" class="sr-only">Zip Code </label>
            </td>
            <td class="right">
              <input
                type="zip-code"
                id="zipCode"
                class="form-control"
                placeholder="Zip Code"
                v-model="user.zip"
                required
              />
            </td>
          </tr>
        </table>
        <br />
        <input
          type="checkbox"
          id="notifications"
          name="notifications"
          value="opt-in"
          v-model="user.subscribed"
        />
        <label for="notifications">Would you like to receive spam? </label>
        <br />
        <br />

        <router-link :to="{ name: 'login' }" class="register-text"
          >Have an account?
        </router-link>

        <br />
        <br />
        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Create Account
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        firstName: "",
        lastName: "",
        email: "",
        birthDate: "",
        zip: "",
        role: "user",
        subscribed: true,
      },
      legalBday: "",
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  created() {
    let bday = new Date();
    bday.setFullYear(bday.getFullYear() - 21);
    bday = bday.toISOString().substring(0, 10);
    this.legalBday = bday;
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    passwordCheck() {
      const strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})");
      const mediumRegex = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");
      const enoughRegex = new RegExp("(?=.{6,}).*", "g");
      const pwd = document.getElementById("password");
      if (pwd.value.length == 0) {
        document.getElementById("check").innerHTML = "Password";
      } else if (false == enoughRegex.test(pwd.value)) {
        document.getElementById("check").innerHTML = "Too Short!";
      } else if (strongRegex.test(pwd.value)) {
        document.getElementById("check").innerHTML =
          '<span style="color:green">Strong!</span>';
      } else if (mediumRegex.test(pwd.value)) {
        document.getElementById("check").innerHTML =
          '<span style="color:orange">Medium!</span>';
      } else {
        document.getElementById("check").innerHTML =
          '<span style="color:red">Weak!</span>';
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
table#registration-table {
  text-align: center;
  margin: 0 auto;
}
.left {
    width: 150px;
  text-align: start;
}
.right {
    width: 180px;
  text-align: start;
}
#birthDate {
    width: 171px;
}
</style>
