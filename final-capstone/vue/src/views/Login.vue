<template>
  <div>
    <div class="transbox">
     <h1 class="text-center">Welcome to the Tech Ale-Evator Brewery Finder</h1>
    <div id="login" class="text-body">
    <form class="form-signin" @submit.prevent="login">
      <h2 class="h3 mb-3 font-weight-normal">Please Sign In</h2>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username </label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only"> Password </label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }" class="registration-button" > Need an account? </router-link>
      <button type="submit" class="btn">Sign in</button>
    </form>
    <br>
   <!--  <img src="../assets/images/great-lakes.jpg" alt="Great Lakes Brewery" class="login-image"> -->
    <br>
    </div>
    </div>
    </div>
  
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>
<style scoped>


.text-center{
  grid-area: text;
}

.img1{
  grid-area: image1;
}

.img2{
  grid-area: image2;
  max-height:85%;
  max-width: 85%;
  border-radius: 50%;
  justify-self: center;
  align-self: end;
 
}


.registration-button{
text-decoration: none;
}


</style>