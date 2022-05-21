<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row">
    <b-list-group class="con wt-100">
      <house-list-item
        v-for="(house, index) in houses"
        :key="index"
        :house="house"
      />
    </b-list-group>
  </b-container>
  <b-container v-else class="bv-example-row mt-3 center">
    <b-row>
      <b-col>
        <div class="alert alert-info d-flex align-items-center">
          <div class="alert-icon">
            <md-icon>info_outline</md-icon>
          </div>
          주택 목록이 없습니다.
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapActions, mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },

  data() {
    return {};
  },
  created() {
    this.clearHouses();
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  methods: {
    ...mapActions(houseStore, ["clearHouses"]),
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
  display: flex;
  justify-content: center;
}
</style>
