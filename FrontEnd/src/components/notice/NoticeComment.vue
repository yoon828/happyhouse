<template
  ><b-row class="my-3">
    <b-col sm="2">
      <label for="textarea-no-auto-shrink"
        ><strong>{{ userid }}</strong
        ><br />
        {{ regtime | dateFormat }}
      </label>
    </b-col>
    <b-col sm="10" class="d-flex bor">
      <span id="textarea-no-auto-shrink" rows="3" max-rows="8">{{
        content
      }}</span>
      <b-col class="text-right">
        <b-icon
          v-if="userInfo && userInfo.userid == userid"
          class="poi"
          icon="trash-fill"
          variant="danger"
          @click="checkDelete"
        ></b-icon>
      </b-col>
    </b-col>
  </b-row>
</template>

<script>
import moment from "moment";
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
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
  methods: {
    checkDelete() {
      if (confirm("정말로 삭제 하시겠습니까?")) {
        deleteComment(this.commentno, (res) => {
          if (res.data == "success") {
            this.$emit("reListComment");
          } else {
            alert("문제가 발생했습니다.");
          }
        });
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
