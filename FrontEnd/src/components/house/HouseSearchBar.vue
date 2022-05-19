<template>
  <div>
    <b-tabs content-class="mt-3" align="center">
      <b-tab title="지역 검색" active>
        <search-dong></search-dong>
      </b-tab>
      <b-tab title="아파트 명 검색">
        <search-name></search-name>
      </b-tab>
    </b-tabs></div
></template>

<script>
import SearchDong from "@/components/house/search/SearchDong.vue";
import SearchName from "@/components/house/search/SearchName.vue";
export default {
  components: { SearchDong, SearchName },
  name: "HouseSearchBar",
  data() {
    return {};
  },
  computed: {},
  created() {},
  methods: {},
};
</script>

<style></style>
