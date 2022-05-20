<template>
  <b-container class="flex-direction-col">
    <h3>{{ house.apartmentName }}</h3>
    <b-list-group>
      <b-list-group-item v-for="(info, idx) in infos" :key="idx">
        <strong>{{ info.title }}</strong>
        <span>{{ info.content }}</span>
      </b-list-group-item>
    </b-list-group>
    <b-table> </b-table>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetailModal",
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  data() {
    return {
      infos: [
        {
          title: "도로명주소",
          content: `${this.house.sidoname} ${this.house.gugunname} ${this.house.roadName}`,
        },
        {
          title: "지번",
          content: `${this.house.sidoname} ${this.house.gugunname} ${this.house.dong} ${this.house.jubun}`,
        },
        {
          title: "거래 금액",
          content: `${this.house.recentPrice} `,
        },
        {
          title: "건설년도",
          content: `${this.house.bulidYear} `,
        },
      ],
    };
  },
  filters: {},
};
</script>

<style></style>
