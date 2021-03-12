<template>
  <div class="container">
    <div id="register" class="text-center">
      <form class="form-register" @submit.prevent="register">
        <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
        <div class="alert alert-danger" role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <label for="username" class="sr-only">Username  </label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <br />
        <br />
        <label for="password" class="sr-only">Password  </label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <br />
        <br />
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
        <br />
        <br />
        <label for="first-name" class="sr-only">First Name  </label>
        <input
          type="first-name"
          id="firstName"
          class="form-control"
          placeholder="First Name"
          v-model="user.firstName"
          required
        />
        <br />
        <br />
        <label for="last-name" class="sr-only">Last Name  </label>
        <input
          type="last-name"
          id="lastName"
          class="form-control"
          placeholder="Last Name"
          v-model="user.lastName"
          required
        />
        <br />
        <br />
        <label for="email-address" class="sr-only">Email Address  </label>
        <input
          type="email-address"
          id="emailAddress"
          class="form-control"
          placeholder="Email Address"
          v-model="user.email"
          required
        />
        <br />
        <br />
        <label for="birth-date" class="sr-only">Birth Date  </label>
        <input
          type="date"
          id="birthDate"
          v-bind:max="legalBday"
          class="form-control"
          placeholder="yyyy-mm-dd"
          v-model="user.birthDate"
          required
        />
        <br />
        <br />
        <label for="zip-code" class="sr-only">Zip Code  </label>
        <input
          type="zip-code"
          id="zipCode"
          class="form-control"
          placeholder="Zip Code"
          v-model="user.zip"
          required
        />
        <br />
        <br />
        <input
          type="checkbox"
          id="notifications"
          name="notifications"
          value="opt-in"
          v-model="user.subscribed"
        />
        <label for="notifications">Would you like to receive spam?  </label>
        <br />
        <br />
        
        <router-link :to="{ name: 'login' }" class="register-text">Have an account?  </router-link>
        
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
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        firstName: '',
        lastName: '',
        email: '',
        birthDate: '',
        zip:'',
        role: 'user',
        subscribed: true
      },
      legalBday: '',
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
      }
    },
    created(){
        let bday = new Date();
        bday.setFullYear(bday.getFullYear() - 21);
        bday = bday.toISOString().substring(0,10);
        this.legalBday = bday;
    },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
</style>
