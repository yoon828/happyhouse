<template>
  <b-list-group-item
    class="m-2 rd-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-row>
      <strong>{{ house.apartmentName }}</strong> {{ house.recentPrice }}만원
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
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
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
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: #ffc107;
}
.rd-2 {
  border-radius: 10px !important;
}
</style>
