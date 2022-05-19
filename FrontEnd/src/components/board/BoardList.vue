<template>
  <b-container class="bv-example-col mt-3 mb-10 wrapper flex-direction-col">
    <b-row>
      <b-col>
        <div class="mb-1">
          <md-button @click="moveWrite()">글쓰기</md-button>
        </div>
      </b-col>
      <b-col class="d-flex justify-content-end">
        <b-form class="form-inline">
          <b-form-select class="form-control" v-model="searchQnA">
            <option value="userid"> 아이디 </option
            ><option value="articleno"> 글번호 </option
            ><option value="subject"> 제목 </option></b-form-select
          >

          <b-form-input type="text" class="ml-1" v-model="keyword" />
          <md-button
            type="button"
            class="ml-1 btn md-warning md-sm"
            @click="searchBoard()"
            >search</md-button
          >
        </b-form>
      </b-col>
    </b-row>
    <b-row class="mt-3 mb-5">
      <b-col>
        <b-table-simple hover responsive class="mb-6">
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>조회수</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <board-list-item
              v-for="article in articles"
              :key="article.articleno"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
        <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
      </b-col>
    </b-row>
    <b-row class="mb-5">
      <b-col class="d-flex justify-content-center">
        <pagination type="warning" v-model="pg" :page-count="totalPage">
        </pagination>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import { listArticle } from "@/api/board.js";
import BoardListItem from "@/components/board/BoardListItem";
import { Pagination } from "@/components";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
    Pagination,
  },
  data() {
    return {
      articles: [],
      keyword: "",
      pg: 1,
      totalPage: 0,
      pageSize: 10,
      searchQnA: "",
    };
  },
  created() {
    http.get(`/qna/`).then(({ data }) => {
      this.articles = data;
      this.totalPage = this.articles.length / this.pageSize + 1;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    searchBoard() {
      this.articles = [];
      //검색기능
      if (this.searchQnA == "userid") {
        //아이디 검색
        http.get(`/qna/searchid/${this.keyword}`).then(({ data }) => {
          this.articles = data;
        });
      } else if (this.searchQnA == "articleno") {
        //글번호 검색
        http.get(`/qna/searchno/${this.keyword}`).then(({ data }) => {
          this.articles.push(data);
        });
      } else if (this.searchQnA == "subject") {
        //제목 검색
        http.get(`/qna/searchtitle/${this.keyword}`).then(({ data }) => {
          this.articles = data;
        });
      } else {
        http.get(`/qna/`).then(({ data }) => {
          this.articles = data;
        });
      }
      this.totalPage = this.articles.length / this.pageSize + 1;
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
