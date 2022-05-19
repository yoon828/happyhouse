<template>
  <b-row class="mt-4 mb-4 text-center d-flex justify-content-center">
    <b-form-input
      v-model="aptname"
      placeholder="아파트 이름"
      class="col-md-3 mr-1"
    ></b-form-input>
    <b-button
      type="button"
      variant="warning"
      @keyup.enter="search"
      @click="search"
      >검색</b-button
    >
  </b-row>
</template>

<script>
import { houseListByName } from "@/api/house.js";

export default {
  data() {
    return {
      aptname: "",
    };
  },
  methods: {
    search() {
      houseListByName(
        {
          aptName: this.aptname,
        },
        (res) => {
          console.log(res.data);
          // eslint-disable-next-line prettier/prettier
        }
      );
    },
  },
};
</script>

<style></style>
