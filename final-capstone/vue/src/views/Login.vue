<template>
  <div class="container">
    <img src="../assets/beer_glass.png" alt="Beer Glass">
    
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="splash-title">Welcome to the Tech Ale-evator Brewery Finder</h1>
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
      <router-link :to="{ name: 'register' }" > Need an account? </router-link>
      <button type="submit">Sign in</button>
    </form>
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
@import url("https://fonts.googleapis.com/css2?family=Press+Start+2P&family=Special+Elite&family=Ultra&display=swap");

@keyframes word{
  0%{background-color:aquamarine;}
  50%{background-color:rgb(64, 156, 126)}
  100%{background-color: rgb(13, 83, 60);}
  
}
.container{
    display: flex;
    justify-content: space-evenly;
    background-color: aquamarine;
    border-radius: 10px;
    animation-name: word;
    animation-duration: 8s;
    animation-iteration-count: infinite;
    animation-direction:alternate;
    }
    
    .text-center{
    align-content: flex-end;
    justify-content: center;
    font-family:'Special Elite', cursive;
    
    }
.splash-title{
  font-family:'Special Elite', cursive;
  color: chocolate;
}


</style>