<template>
  <router-view></router-view>
</template>

<script>
export default {
  components: {},
  bodyClass: "login-page",
  data() {
    return {
      userid: null,
      userpw: null,
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/house-bg.png"),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
};
</script>

<style lang="css"></style>
