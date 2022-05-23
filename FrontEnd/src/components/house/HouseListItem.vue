<template>
  <b-list-group-item
    class="m-2 rd-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-row class="d-flex justify-content-between">
      <p class="px-1 bold apt">{{ house.apartmentName }}</p>
      평균 {{ house.avgPrice | price | toprice }}만원
    </b-row>
    <b-row>
      {{ house.sidoname }} {{ house.gugunname }} {{ house.dong }}
      {{ house.jibun }}
    </b-row>
  </b-list-group-item>
</template>

<script>
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  filters: {
    toInt(val) {
      if (!val) return "";
      let num = parseInt(val);
      if (num == 0) return "";
      else return num;
    },
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    toprice(value) {
      let front = value.substr(0, value.length - 5);
      let back = value.substr(value.length - 5, value.length);
      if (back.charAt(0) == "0") {
        back = back.substr(2);
      }
      return front == "" ? `${back}` : `${front}억 ${back}`;
    },
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  font-size: 1rem;
}
.mouse-over-bgcolor {
  background-color: #ffc107;
}
.rd-2 {
  border-radius: 10px !important;
}
</style>
