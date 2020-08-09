<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  components: {

  },
}
</script>

<style>
* {
  margin:0;
  padding:0;
}
html, body {
  height: 100%
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height:100%;
  width:100%;
}
.left {
  height: 100%;
  background: #e7e7e7;
}
.header {
  height: 10%;
  background: #e9e9e9
}
.main {
  height:90%;
  background: #f9f9f9
}

</style>
