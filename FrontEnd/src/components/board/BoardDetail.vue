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
    <div>
      <b-form-textarea
        id="textarea"
        v-model="text"
        placeholder="댓글 작성..."
        rows="3"
        max-rows="6"
      ></b-form-textarea>

      <pre class="mt-3 mb-0">{{ text }}</pre>
    </div>
    <b-row class="mb-5">
      <b-col class="text-right">
        <md-button type="button" class="m-1 md-success">댓글 작성</md-button>
      </b-col>
    </b-row>
    <b-row class="mt-2">
      <b-col sm="2">
        <label for="textarea-no-auto-shrink"
          >작성자 : <br />
          작성일:</label
        >
      </b-col>
      <b-col sm="10">
        <b-form-textarea
          id="textarea-no-auto-shrink"
          rows="3"
          max-rows="8"
          no-auto-shrink
        ></b-form-textarea>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getArticle, hitAdd } from "@/api/board.js";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    hitAdd(this.$route.params.articleno, (res) => {});
    getArticle(this.$route.params.articleno, (res) => {
      this.article = res.data;
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      if (this.userInfo?.userid === undefined || this.userInfo.userid == null) {
        alert("로그인이 필요한 서비스입니다.");
        this.$router.push({ name: "login" });
      } else {
        if (this.userInfo?.userid != this.article.userid) {
          alert("다른 사용자의 글은 수정 할 수 없습니다.");
        } else {
          this.$router.replace({
            name: "boardModify",
            params: {
              articleno: this.article.articleno,
            },
          });
        }
      }

      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (this.userInfo?.userid === undefined || this.userInfo.userid == null) {
        alert("로그인이 필요한 서비스입니다.");
        this.$router.push({ name: "login" });
      } else {
        if (this.userInfo?.userid != this.article.userid) {
          alert("다른 사용자의 글은 삭제 할 수 없습니다.");
        } else {
          if (confirm("삭제하시겠습니까?")) {
            deleteArticle(this.$route.params.articleno, (res) => {
              let msg = "문제가 발생했습니다.";
              if (res.data == "success") {
                msg = "글이 삭제되었습니다.";
              }
              alert(msg);
              this.$router.push({ name: "boardList" });
            });
          }
        }
      }
    },
  },
};
</script>

<style></style>
