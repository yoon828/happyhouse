<template>
  <div class="wrapper">
    <parallax class="page-header header-filter" :style="headerStyle">
    </parallax>
    <div class="main main-raised">
      <b-container class="flex-direction-col">
        <div class="section section-basic pb-2">
          <div class="title">
            <h2>아파트 거래 조회</h2>
          </div>
        </div>
        <house-search-bar></house-search-bar>
        <b-row>
          <b-col>
            <house-list> </house-list>
          </b-col>
          <b-col>
            <house-map></house-map>
          </b-col>
        </b-row>
      </b-container>
    </div>
  </div>
</template>

<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "../components/house/HouseList.vue";
import HouseMap from "../components/house/HouseMap.vue";
export default {
  components: {},
  name: "HouseView",
  components: {
    HouseSearchBar,
    HouseList,
    HouseMap,
  },
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
