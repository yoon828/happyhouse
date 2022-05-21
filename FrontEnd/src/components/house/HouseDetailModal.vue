<template>
  <b-container class="flex-direction-col">
    <h3>{{ house.apartmentName }}</h3>
    <b-list-group>
      <b-list-group-item>
        {{ this.house.sidoname }}
      </b-list-group-item>
    </b-list-group>
    <b-table> </b-table>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import { dealsByCode } from "@/api/house.js";

const houseStore = "houseStore";

export default {
  name: "HouseDetailModal",
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  data() {
    return {
      deals: [],
    };
  },
  filters: {},
  created() {
    dealsByCode({ aptCode: this.house.aptCode }, (res) => {
      this.deals = res.data;
    });
  },
};
</script>

<style></style>
