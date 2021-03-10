<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>|
      <router-link v-bind:to="{ name: 'register'}">Register</router-link>
      <router-view/>
    </div>
    <main>Main</main>
    <div id ="sidebar">Sidebar</div>
    <div id ="content1">Content1</div>
    <div id ="content2">Content2</div>
    <div id = "content3">Content3</div>
    <footer>Footer</footer>
   </div>
</template>
    
    
    <script>
    
    </script>
    <style>
    .wrapper, #app{
  display:grid;
  height:100vh;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 0.2fr 1.5fr 1.2fr 0.8fr;
  grid-template-areas:
   "nav nav nav nav"
   "sidebar main main main"
   "sidebar content1 content2 content3"
   "sidebar footer footer footer";
   grid-gap:0.2rem;
   font-family: Verdana, Geneva, Tahoma, sans-serif;
   font-size: medium;
}
#nav{
  background-color:cornflowerblue;
 grid-area: nav;
}
main{
  background-color: grey;
  display: block;
  grid-area: main;
  width:1415px;
  height:500px;
}
#sidebar{
  background-color:grey;
  grid-area:sidebar;
}

#content1{
background-color:cornflowerblue;
grid-area: content1;
}
#content2{
  background-color: white;
  grid-area: content2;
}
#content3{
  background-color:grey ;
  grid-area: content3;
}
footer{
  background-color: grey;
  grid-area: footer;
}


    
    </style>
  
