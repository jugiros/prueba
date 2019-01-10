<template lang="pug">
  #app(style="margin-top: 0px;")
    v-app(dark, style='margin-top: 0px;')
      router-view
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.v-content[data-booted=true] {
  padding-top: 60px !important;
}
</style>
