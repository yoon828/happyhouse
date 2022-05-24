<template>
  <b-container class="flex-direction-col right">
    <b-button
      id="popover-button-event"
      ref="button"
      variant="warning"
      class="mb-2"
    >
      <b-icon icon="sliders"></b-icon>
    </b-button>
    <div v-if="houses && houses.length != 0" class="bv-example-row wd">
      <b-list-group class="con wt-100">
        <house-list-item
          v-for="(house, index) in housesfilter"
          :key="index"
          :houseItem="house"
        />
      </b-list-group>
    </div>
    <div v-else class="bv-example-row mt-3 center">
      <b-row>
        <b-col>
          <div class="alert alert-info d-flex align-items-center">
            <div class="alert-icon">
              <md-icon>info_outline</md-icon>
            </div>
            아파트 목록이 없습니다.
          </div>
        </b-col>
      </b-row>
    </div>

    <b-popover
      ref="popover"
      target="popover-button-event"
      title="필터링"
      triggers="focus"
      :show.sync="popoverShow"
      placement="auto"
      container="my-container"
      class="popstyle"
    >
      <template #title>
        <div class="filtertitle">
          <b-button @click="onClose" class="close m-0" aria-label="Close">
            <span class="d-inline-block" aria-hidden="true">&times;</span>
          </b-button>
          <h6>아파트 목록 필터링</h6>
        </div>
      </template>

      <b-container class="flex-direction-col mx-2">
        <div class="my-4">
          평균 가격 범위 (단위 : 만원)
          <vue-slider
            v-model="pricevalue"
            :data="pricedata"
            :tooltip="'always'"
            :tooltip-placement="['bottom', 'bottom']"
            :tooltip-formatter="format1"
            @dragging="onSubmit"
          ></vue-slider>
        </div>
        <div class="my-4">
          건설연도 범위 (단위 : 년)
          <vue-slider
            v-model="buildrange"
            :tooltip="'always'"
            :tooltip-placement="['bottom', 'bottom']"
            :tooltip-formatter="format2"
            :min="1961"
            :max="2022"
            @dragging="onSubmit"
          ></vue-slider>
        </div>
        <b-row class="mt-4 d-flex justify-content-end">
          <b-button @click="onReset" size="sm" variant="outline-danger"
            >초기화</b-button
          >
        </b-row>
      </b-container>
    </b-popover>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import VueSlider from "vue-slider-component";
import "vue-slider-component/theme/default.css";

import { mapActions, mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
    VueSlider,
  },

  data() {
    return {
      popoverShow: false,
      pricevalue: "1200",
      pricedata: ["1", "5", "10", "20", "50", "100", "500", "1000", "1200"],
      buildrange: [1961, 2022],
      format1: (v) => `${v / 10.0}억`,
      format2: `{value}년`,
    };
  },
  watch: {
    houses: function() {
      this.pricevalue = "1200";
      this.buildrange = [1961, 2022];
    },
  },
  created() {
    this.clearHouses();
  },
  computed: {
    ...mapState(houseStore, ["houses", "housesfilter"]),
  },
  methods: {
    ...mapActions(houseStore, [
      "clearHouses",
      "clearHousesFilter",
      "setHousesFilter",
    ]),
    onClose() {
      this.popoverShow = false;
    },
    onSubmit() {
      this.clearHousesFilter();
      //필터 적용하기
      let hlist = this.houses;
      let filterHouse = hlist.filter((h) => {
        return (
          parseInt(h.avgPrice) <= this.pricevalue * 1000 &&
          h.buildYear >= this.buildrange[0] &&
          h.buildYear <= this.buildrange[1]
        );
      });
      this.setHousesFilter(filterHouse);
    },
    onReset() {
      //필터 해제
      let origin = this.houses;
      this.setHousesFilter(origin);
      this.pricevalue = "120";
      this.buildrange = [1961, 2022];
    },
  },
};
</script>

<style>
.con {
  position: relative;
  overflow-y: auto;
  height: 600px;
}
.con::-webkit-scrollbar {
  width: 10px;
}
.con::-webkit-scrollbar-thumb {
  background-color: #edcf25;
  border-radius: 10px;
}
.con::-webkit-scrollbar-track {
  border-radius: 10px;
}
.center {
  height: 100%;
  width: 100%;
  display: flex;
  justify-content: center;
}
.filtertitle {
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-around;
  align-items: baseline;
}
.popover.b-popover {
  border: none;
}

.right {
  display: flex;
  align-items: flex-end;
}

.wd {
  width: 100%;
}
</style>
