<template>
  <div class="wrapper">
    <parallax class="page-header header-filter" :style="headerStyle">
    </parallax>
    <div class="main main-raised">
      <div class="section section-basic pb-2">
        <div class="container">
          <div class="title">
            <h2>동네 맛집 검색</h2>
          </div>
        </div>
        <food-list></food-list>
      </div>
    </div>
  </div>
</template>

<script>
import FoodList from "@/components/food/FoodList.vue";
export default {
  components: {
    FoodList,
  },
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
