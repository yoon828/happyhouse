<template>
  <b-container class="bv-example-col mt-3 mb-10 wrapper flex-direction-col">
    <b-row>
      <b-col> </b-col>
      <b-col class="d-flex justify-content-end"> </b-col>
    </b-row>
    <b-row class="mt-3 mb-5">
      <b-col>
        <b-table-simple hover responsive class="mb-6">
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>제목</b-th>
              <b-th>내용</b-th>
              <b-th></b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <news-list-item
              v-for="(item, idx) in items"
              :key="idx"
              v-bind="item"
            />
          </tbody>
        </b-table-simple>
        <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { getNewsList } from "@/api/news.js";
import NewsListItem from "@/components/news/NewsListItem";
export default {
  name: "NewsList",
  components: {
    NewsListItem,
  },
  data() {
    return {
      items: [],
    };
  },
  created() {
    getNewsList((res) => {
      this.items = res.data.items;
    });
  },
};
</script>

<style></style>
