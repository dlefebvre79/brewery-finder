<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link> |
<<<<<<< HEAD
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> |
=======
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>|
>>>>>>> 2f6d54e718a21ba801684b50c9baae16c522e757
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
  
