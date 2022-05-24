<template>
  <div id="material-kit">
    <div :class="{ 'nav-open': NavbarStore.showNavbar }">
      <router-view name="header" />
      <div>
        <router-view />
      </div>
      <router-view name="footer" />
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Gothic+A1:wght@400;500;600;700;800&display=swap");

*,
h1,
h2,
h3,
h4,
h5 {
  font-family: "Gothic A1", sans-serif !important;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  font-size: 18px;
  font-weight: normal;
}

a,
a:link,
a:visited,
a:hover,
a:active {
  text-decoration: none;
  color: black !important;
}
.flex-direction-col {
  flex-direction: column;
}
.card {
  min-height: 350px;
}
.wt-100 {
  width: 100%;
}
.bold {
  font-weight: bold;
}
.vue-slider-process {
  background-color: #ffc107 !important;
}
.vue-slider-dot-tooltip-inner {
  border-color: #ffc107 !important;
  background-color: #ffffff !important;
  color: black !important;
  border: 1px solid #ffc107;
}
</style>
