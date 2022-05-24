<template>
  <b-container class="bv-example-col mt-3 mb-10 wrapper flex-direction-col">
    <b-row>
      <b-col>
        <div class="mb-1">
          <b-button @click="moveWrite()">글쓰기</b-button>
        </div>
      </b-col>
      <b-col class="d-flex justify-content-end">
        <b-form class="form-inline">
          <b-form-select class="form-control" v-model="searchQnA">
            <option value="userid"> 아이디 </option
            ><option value="articleno"> 글번호 </option
            ><option value="subject"> 제목 </option></b-form-select
          >

          <b-form-input
            type="text"
            class="ml-1"
            v-model="keyword"
            @keyup.enter="searchBoard"
          />
          <b-button
            type="button"
            class="ml-1 btn md-warning md-sm"
            @click="searchBoard"
            variant="warning"
            >search</b-button
          >
        </b-form>
      </b-col>
    </b-row>
    <b-row class="mt-3 mb-5">
      <b-col>
        <b-table-simple class="mb-6 text-center" id="my-table">
          <colgroup>
            <col style="width: 10%" />
            <col style="width: 60%" />
            <col style="width: 10%" />
            <col style="width: 10%" />
            <col style="width: 10%" />
          </colgroup>
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
        <b-pagination
          v-model="pg"
          :total-rows="rows"
          :per-page="pageSize"
          aria-controls="my-table"
        ></b-pagination>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import {
  listArticle,
  getArticleById,
  getArticle,
  getArticleBySubject,
} from "@/api/board.js";
import BoardListItem from "@/components/board/BoardListItem";

import { Pagination } from "@/components";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
      keyword: "",
      pg: 1,
      totalPage: 0,
      pageSize: 10,
      rows: 0,
      searchQnA: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    listArticle((res) => {
      this.articles = res.data;
      this.rows = this.articles.length;
      this.totalPage = this.articles.length / this.pageSize + 1;
    });
  },
  methods: {
    moveWrite() {
      if (!this.userInfo?.userid) {
        alert("로그인이 필요한 서비스입니다.");
        this.$router.push({ name: "login" });
      } else {
        this.$router.push({ name: "boardRegister" });
      }
    },
    searchBoard(event) {
      event.preventDefault();
      if (!this.searchQnA) {
        alert("카테고리를 선택해주세요");
        return;
      }
      this.articles = [];
      //검색기능
      if (this.searchQnA == "userid") {
        //아이디 검색
        getArticleById(this.keyword, (res) => {
          this.articles = res.data;
        });
      } else if (this.searchQnA == "articleno") {
        if (isNaN(this.keyword)) {
          alert("숫자를 입력해주세요.");
          return;
        }
        //글번호 검색
        getArticle(this.keyword, (res) => {
          this.articles.push(res.data);
        });
      } else if (this.searchQnA == "subject") {
        //제목 검색
        getArticleBySubject(this.keyword, (res) => {
          this.articles = res.data;
        });
      } else {
        listArticle((res) => {
          this.articles = res.data;
        });
      }
      this.totalPage = this.articles.length / this.pageSize + 1;
    },
  },
};
</script>

<style scope></style>
