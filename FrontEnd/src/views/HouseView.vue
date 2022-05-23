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
        <b-row class="mb-5">
          <b-col cols="4" class="p-0">
            <house-list> </house-list>
          </b-col>
          <b-col class="p-0">
            <house-map v-on:show-modal="showModal"></house-map>
          </b-col>
        </b-row>
      </b-container>
    </div>
    <b-modal
      id="modal-lg"
      size="lg"
      v-model="modalShow"
      title="아파트 상세 조회"
      hide-footer
    >
      <house-detail-modal></house-detail-modal>
    </b-modal>
  </div>
</template>

<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseMap from "@/components/house/HouseMap.vue";
import HouseDetailModal from "@/components/house/HouseDetailModal.vue";
import { mapActions } from "vuex";
const houseStore = "houseStore";

export default {
  components: {},
  name: "HouseView",
  data() {
    return {
      modalShow: false,
    };
  },
  components: {
    HouseSearchBar,
    HouseList,
    HouseMap,
    HouseDetailModal,
  },
  props: {
    image: {
      type: String,
      default: require("@/assets/img/house-bg.png"),
    },
  },
  created() {
    this.clearHouse();
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`,
        height: "30vh",
      };
    },
  },
  methods: {
    ...mapActions(houseStore, ["clearHouse"]),
    showModal() {
      this.modalShow = true;
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
