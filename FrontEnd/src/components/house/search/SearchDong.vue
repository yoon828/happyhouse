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
  </b-row>
</template>

<script>
import {
  sidoList,
  gugunList,
  dongList,
  houseList,
  houseListByDong,
} from "@/api/house.js";
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
    ...mapActions(houseStore, ["setHouses"]),
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
          this.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
        });
      });
    },
    getDongList() {
      this.dongs = [{ value: null, text: "선택" }];
      dongList({ sidoCode: this.gugunCode }, (res) => {
        res.data.forEach((dong) => {
          this.dongs.push({ value: dong.dongCode, text: dong.dongName });
        });
      });
    },
    getHouseList() {
      // //나중에 .env파일로 빼기
      // const SERVICE_KEY =
      //   "ncoTsC4jRn7O24ipGkrF24oKEDr7l2uTab3PnB%2F7XER1spQHFhleCl6iLOBf3nTm8tZLyEkNdBta%2FL0rI47H0A%3D%3D";
      // console.log(this.gugunCode);
      // const params = {
      //   LAWD_CD: this.gugunCode,
      //   DEAL_YMD: "202110",
      //   serviceKey: decodeURIComponent(SERVICE_KEY),
      // };
      // houseList(params, ({ data }) => {
      //   this.setHouses(data.response.body.items.item);
      // });
      houseListByDong(
        {
          dongCode: this.dongCode,
        },
        (res) => {
          this.setHouses(res.data);
          // eslint-disable-next-line prettier/prettier
        }
      );
    },
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
