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

<style>
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
</style>
