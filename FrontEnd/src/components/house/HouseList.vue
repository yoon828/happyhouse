<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <house-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
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
    ...mapState(["houses"]),
  },
  methods: {
    ...mapActions(["clearHouses"]),
  },
};
</script>

<style></style>
