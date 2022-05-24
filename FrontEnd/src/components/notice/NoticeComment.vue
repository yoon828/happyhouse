<template
  ><b-row class="mt-2">
    <b-col sm="2">
      <label for="textarea-no-auto-shrink"
        >작성자 : {{ userid }}<br />
        작성일: {{ regtime }}
      </label>
    </b-col>
    <b-col sm="10">
      <b-form-textarea
        id="textarea-no-auto-shrink"
        rows="3"
        max-rows="8"
        no-auto-shrink
        v-model="content"
        :readonly="true"
      ></b-form-textarea>
      <b-col class="text-right">
        <md-button type="button" class="m-1 md-rose md-sm" @click="checkDelete"
          ><b>답변 삭제</b></md-button
        >
      </b-col>
    </b-col>
  </b-row>
</template>

<script>
import { deleteComment } from "@/api/notice.js";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "NoticeComment",
  props: {
    userid: String,
    regtime: String,
    content: String,
    articleno: Number,
    commentno: Number,
  },
  methods: {
    checkDelete() {
      if (this.userInfo?.usertype != "A") {
        alert("관리자만 삭제할 수 있습니다.");
      } else {
        if (confirm("정말로 삭제 하시겠습니까?")) {
          deleteComment(this.commentno, (res) => {
            let msg = "문제가 발생했습니다.";
            if (res.data == "success") {
              msg = "답변이 삭제되었습니다.";
            }
            alert(msg);
            this.$router.go();
          });
        } else {
        }
      }
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style>
.mt-2 {
  padding: auto;
  margin: 30px;
}
</style>
