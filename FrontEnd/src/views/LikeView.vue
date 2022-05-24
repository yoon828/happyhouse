<template>
  <div class="wrapper">
    <parallax class="page-header header-filter" :style="headerStyle">
    </parallax>
    <div class="main main-raised">
      <div class="section section-basic pb-2">
        <div class="container">
          <div class="title">
            <h2>나의 관심지역</h2>
          </div>
        </div>
      </div>
      <my-like-list></my-like-list>
    </div>
  </div>
</template>

<script>
import MyLikeList from "../components/member/like/MyLikeList.vue";
export default {
  components: { MyLikeList },
  name: "index",
  bodyClass: "index-page",
  props: {
    image: {
      type: String,
      default: require("@/assets/img/house-bg.png"),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`,
        height: "30vh",
      };
    },
  },
};
</script>
<style lang="scss">
@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}
.pb-2 {
  padding-bottom: 2rem !important;
}
</style>
