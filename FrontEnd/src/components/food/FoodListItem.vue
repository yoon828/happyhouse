<template>
  <b-tr>
    <b-td v-html="title"></b-td>
    <b-td>{{ category }}</b-td>
    <b-td>{{ address }}</b-td>
    <b-td>{{ roadAddress }}</b-td>
    <b-td @click="moveLink">{{ link }}</b-td>
  </b-tr></template
>
<script>
export default {
  name: "FoodListItem",
  props: {
    title: String,
    category: String,
    address: String,
    roadAddress: String,
    link: String,
  },
  methods: {
    moveLink() {
      window.location.href = this.link;
    },
  },
};
</script>

<style></style>
