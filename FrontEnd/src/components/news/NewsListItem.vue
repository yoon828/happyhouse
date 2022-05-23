<template>
  <b-tr @click="moveLink">
    <b-th class="text-left" v-html="title"> </b-th>
    <b-th class="text-left" v-html="description"> </b-th>
    <b-th class="text-left">상세보기</b-th>
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
      window.location.href = this.link;
    },
  },
};
</script>

<style></style>
