<template>
  <b-container class="bv-example-row mt-3 flex-direction-col">
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="
            `<h3>${article.articleno}.
          ${article.subject} [${article.hit}]</h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`
          "
          class="mb-2"
          border-variant="gray"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <b-row class="mb-5">
      <b-col class="text-left">
        <md-button type="button" class="m-1" @click="listArticle"
          >목록</md-button
        >
      </b-col>
      <b-col class="text-right">
        <md-button type="button" class="m-1 md-info" @click="moveModifyArticle"
          >글수정</md-button
        >
        <md-button type="button" class="m-1 md-danger" @click="deleteArticle"
          >글삭제</md-button
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import http from "@/api/http";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    http.get(`/qna/${this.$route.params.articleno}`).then(({ data }) => {
      this.article = data;
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("삭제하시겠습니까?")) {
        http.delete(`/qna/${this.$route.params.articleno}`).then(({ data }) => {
          alert("글이 삭제되었습니다.");
          this.$router.push({ name: "boardList" });
        });
      }
    },
  },
};
</script>

<style></style>
