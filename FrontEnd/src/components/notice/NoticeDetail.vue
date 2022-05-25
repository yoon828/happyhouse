<template>
  <b-container class="bv-example-row mt-3 flex-direction-col">
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="
            `<h4>${article.articleno}.
          ${article.subject} [${article.hit}]</h4><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`
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
        v-model="content"
        placeholder="답변 작성(관리자만 답변)..."
        rows="3"
        max-rows="6"
      ></b-form-textarea>
    </div>
    <b-row class="mb-5">
      <b-col class="text-right">
        <md-button type="button" class="m-1 md-success" @click="commentCheck"
          >답변 작성</md-button
        >
      </b-col>
    </b-row>
    <notice-comment
      v-for="comment in comments"
      :key="comment.commentno"
      v-bind="comment"
    />
  </b-container>
</template>

<script>
// import moment from "moment";
import { getArticle, hitAdd, writeComment, listComment } from "@/api/notice.js";
import { mapState } from "vuex";
import NoticeComment from "@/components/notice/NoticeComment";

const memberStore = "memberStore";
export default {
  name: "NoticeDetail",
  components: {
    NoticeComment,
  },
  data() {
    return {
      article: {},
      comments: [],
      content: null,
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
    hitAdd(this.$route.params.articleno, (res) => {
      if (res.data == "success") {
        getArticle(this.$route.params.articleno, (res) => {
          this.article = res.data;
        });
        listComment(this.$route.params.articleno, (res) => {
          this.comments = res.data;
        });
      }
    });
  },
  methods: {
    commentCheck() {
      if (this.content != null && this.content != "") {
        this.commentRegist();
      } else {
        alert("답변을 작성해주세요.");
      }
    },
    commentRegist() {
      let comment = {
        userid: this.userInfo?.userid,
        articleno: this.article.articleno,
        content: this.content,
      };
      let userType = this.userInfo?.usertype;

      writeComment(comment, (res) => {
        let msg = "답변 등록시 문제가 발생했습니다.";
        if (res.data === "success") {
          msg = "답변 등록이 완료되었습니다.";
        }
        alert(msg);
        this.$router.go();
      });
    },
    listArticle() {
      this.$router.push({ name: "noticeList" });
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
            name: "noticeModify",
            params: {
              articleno: this.article.articleno,
            },
          });
        }
      }
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
              this.$router.push({ name: "noticeList" });
            });
          }
        }
      }
    },
  },
};
</script>

<style></style>
