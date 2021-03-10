<template>
  <div id="app" class="grid-container">
    <div class= "header">
    <div id="nav" class= "main-nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link> |
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'register'}">Register</router-link>
      </div>
    </div>
    <div class="left"></div>
    <div class="main">
    <router-view />
    </div>
    <div class="right"></div>
    <div class="footer">&#169; API === IPA </div>
  </div>
</template>
