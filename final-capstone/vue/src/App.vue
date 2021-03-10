<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link> |
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'register'}">Register</router-link>
      
     
      <router-view/>
      
    </div>
    
    <!-- <main>Main</main>
    <div id ="sidebar">Sidebar</div>
    <div id ="content1">Content1</div>
    <div id ="content2">Content2</div>
    <div id = "content3">Content3</div>
    <footer>Footer</footer> -->
   </div>
</template>
    
    
    <script>
    
    </script>
    <style>
    
    
    </style>
  
