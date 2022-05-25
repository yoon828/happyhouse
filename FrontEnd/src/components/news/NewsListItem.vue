<template>
  <b-tr>
    <b-th class="text-left" v-html="title"> </b-th>
    <b-th class="text-left" v-html="description"> </b-th>
    <b-th class="text-left"
      ><b-button type="button" variant="warning" @click="moveLink">
        <b-icon icon="search"></b-icon> </b-button
    ></b-th>
  </b-tr>
</template>

<script>
export default {
  name: "NewsListItem",
  props: {
    title: String,
    link: String,
    description: String,
  },
  methods: {
    moveLink() {
      window.open(this.link);
    },
  },
};
</script>

<style>
b {
  font-weight: normal;
}
</style>
