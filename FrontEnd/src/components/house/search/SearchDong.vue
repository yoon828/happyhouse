/* eslint-disable prettier/prettier */
<template>
  <b-row class="my-4 wc mx-auto">
    <b-col class="sm-3">
      <b-form-select v-model="sidoCode" :options="sidos" @change="getGugunList">
      </b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="gugunCode"
        :options="guguns"
        @change="getDongList"
      >
      </b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select v-model="dongCode" :options="dongs" @change="getHouseList">
      </b-form-select>
    </b-col>
    <b-button type="button" variant="warning" @click="addLikeDong">
      <b-icon icon="heart"></b-icon>
      <!-- <b-icon icon="heart-fill"></b-icon> -->
    </b-button>
  </b-row>
</template>

<script>
import { sidoList, gugunList, dongList, houseListByDong } from "@/api/house.js";
import { mapActions } from "vuex";

const houseStore = "houseStore";
export default {
  name: "SearchDong",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      sidos: [{ value: null, text: "선택" }],
      guguns: [{ value: null, text: "선택" }],
      dongs: [{ value: null, text: "선택" }],
    };
  },
  created() {
    this.getSidoList();
  },
  computed: {},
  methods: {
    ...mapActions(houseStore, ["setHouses", "setHousesFilter"]),
    getSidoList() {
      sidoList((res) => {
        res.data.forEach((sido) => {
          this.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
        });
      });
    },
    getGugunList() {
      this.guguns = [{ value: null, text: "선택" }];
      gugunList({ sidoCode: this.sidoCode }, (res) => {
        res.data.forEach((gugun) => {
          if (gugun.gugunName != null) {
            this.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
          }
        });
      });
    },
    getDongList() {
      this.dongs = [{ value: null, text: "선택" }];
      dongList({ sidoCode: this.gugunCode }, (res) => {
        res.data.forEach((dong) => {
          if (dong.dongName != null) {
            this.dongs.push({ value: dong.dongCode, text: dong.dongName });
          }
        });
      });
    },
    getHouseList() {
      houseListByDong(
        {
          dongCode: this.dongCode,
        },
        (res) => {
          this.setHouses(res.data);
          this.setHousesFilter(res.data);
          // eslint-disable-next-line prettier/prettier
        }
      );
    },
    addLikeDong() {},
  },
};
</script>

<style>
.wc {
  width: 38rem;
  display: flex;
  align-content: center;
}
</style>
