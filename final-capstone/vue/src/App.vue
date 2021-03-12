<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link> |
      <router-link v-bind:to="{ name: 'brewery-list'}">Breweries</router-link> |
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> 
      <router-link v-bind:to="{ name: 'login' }" v-else>Login</router-link> |
      <router-link v-bind:to="{ name: 'register'}">Register</router-link>
    </div>


    <main class="main"><router-view/></main>
    
    <footer> &#169; API === IPA</footer>
   </div>
</template>
    
    
    <script>

    </script>
    <style>
    
    
    </style>
  
