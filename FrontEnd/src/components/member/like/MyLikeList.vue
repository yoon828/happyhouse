<template>
  <b-container>
    <b-table-simple hover responsive class="t_center text-center">
      <colgroup>
        <col style="width: 10%" />
        <col style="width: 60%" />
        <col style="width: 10%" />
        <col style="width: 10%" />
      </colgroup>
      <b-thead>
        <b-tr>
          <b-th>번호</b-th>
          <b-th>지역</b-th>
          <b-th>좋아요</b-th>
          <b-th>조회</b-th>
          <b-th>삭제</b-th>
        </b-tr>
      </b-thead>
      <tbody>
        <my-like-list-item
          v-for="(region, idx) in likeList"
          :key="idx"
          v-bind="region"
          :idx="idx + 1"
        />
      </tbody>
    </b-table-simple>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import MyLikeListItem from "./MyLikeListItem.vue";
const memberStore = "memberStore";

export default {
  name: "MyLikeList",
  components: { MyLikeListItem },
  data() {
    return {};
  },
  created() {},
  computed: {
    ...mapState(memberStore, ["likeList"]),
  },
  methods: {},
};
</script>

<style scoped>
.t_center {
  width: 80%;
  margin: 0 auto 5rem;
}
</style>
