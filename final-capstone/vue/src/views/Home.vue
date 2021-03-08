
<template>
<div class ="container">
  <div class="nav">Nav</div>
    <main>Main</main>
    <div id ="sidebar">Sidebar</div>
    <div id ="content1">Content1</div>
    <div id ="content2">Content2</div>
    <div id = "content3">Content3</div>
    <footer>Footer</footer>
    <p>Welcome</p>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>
<style>
.container{
  display:grid;
  height:100vh;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 0.2fr 1.5fr 1.2fr 0.8fr;
  grid-template-areas:
   "nav nav nav nav"
   "sidebar main main main"
   "sidebar content1 content2 content 3"
   "sidebar footer footer footer";
}
.nav{
  background-color: coral;
  grid-area:nav;
}
main{
  background-color: cornflowerblue;
  grid-area:main;
}
#sidebar{
  background-color: crimson;
  grid-area:sidebar;
}

#content1{
background-color: darkmagenta;
grid-area: content1;
}
#content2{
  background-color: darkolivegreen;
  grid-area: content2;
}
#content3{
  background-color: orange;
  grid-area: content3;
}
footer{
  background-color: orchid;
  grid-area: footer;
}


</style>