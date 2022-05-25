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
            @keyup.enter="searchNotice"
          />
          <b-button
            type="button"
            class="ml-1 btn md-warning md-sm"
            @click="searchNotice"
            variant="warning"
            >search</b-button
          >
        </b-form>
      </b-col>
    </b-row>

    <b-row>
      <b-col>
        <b-table
          class="mb-6 text-center"
          id="my-table"
          :fields="fields"
          :items="articles"
          :per-page="perPage"
          :current-page="currentPage"
        >
          <template #cell(subject)="data">
            <router-link
              :to="{
                name: 'noticeDetail',
                params: { articleno: data.item.articleno },
              }"
              >{{ data.item.subject }}</router-link
            >
          </template>
        </b-table>
        <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
      </b-col>
    </b-row>
    <b-row class="mb-5">
      <b-col class="d-flex justify-content-center">
        <b-pagination
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
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
} from "@/api/notice.js";
import NoticeListItem from "@/components/notice/NoticeListItem";

import { Pagination } from "@/components";
import { mapState } from "vuex";
const memberStore = "memberStore";
import moment from "moment";

export default {
  name: "NoticeList",
  components: {
    // NoticeListItem,
  },
  data() {
    return {
      articles: [],
      keyword: "",
      currentPage: 1,
      perPage: 10,
      rows: 0,
      searchQnA: "",
      fields: [
        { key: "articleno", label: "글번호", thClass: "w10" },
        {
          key: "subject",
          label: "제목",
          thClass: "w60",
        },
        { key: "hit", label: "조회수", thClass: "w10" },
        { key: "userid", label: "작성자", thClass: "w10" },
        {
          key: "regtime",
          label: "작성일",
          thClass: "w10",
          formatter: (value, key, item) => {
            return moment(new Date(value)).format("YY.MM.DD");
          },
        },
      ],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    listArticle((res) => {
      this.articles = res.data;
    });
  },
  methods: {
    moveWrite() {
      if (!this.userInfo?.userid) {
        alert("로그인이 필요한 서비스입니다.");
        this.$router.push({ name: "login" });
        return;
      } else if (this.userInfo.usertype == "A") {
        this.$router.push({ name: "noticeRegister" });
        return;
      } else {
        alert("공지 사항은 관리자만 등록할 수 있습니다.");
        return;
      }
    },
    searchNotice(event) {
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
    },
  },
};
</script>

<style scope></style>
