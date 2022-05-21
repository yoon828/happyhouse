<template>
  <div class="container">
    <div id="box" class="col-md-12 text-center">
      <h2>회원 탈퇴</h2>
      <p slot="description" class="description">
        정말로 탈퇴하시겠습니까?
      </p>
      <md-button slot="footer" class="md-warning" @click="deleteConfirm">
        회원 탈퇴
      </md-button>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import http from "@/api/http";
const memberStore = "memberStore";
export default {
  data() {
    return {
      userid: null,
      userpw: null,
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo", "isDelete"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm"]),
    ...mapMutations(memberStore, ["SET_IS_DELETE"]),
    async deleteConfirm() {
      if (this.isLogin) {
        http
          .delete(`/userapi/delete/${this.userInfo.userid}`)
          .then(({ data }) => {
            if (data === "success") {
              this.SET_IS_DELETE(true);
            }
          });
      }
      if (this.isDelete) {
        alert("회원 탈퇴 성공");
        this.$router.push({ name: "home" });
      } else {
        alert("비밀 번호를 다시 확인하세요.");
      }
    },
  },
};
</script>

<style>
#box {
  padding: 100px;
  margin: 5px;
}
.md-form-group {
  width: 50%;
  margin: 0 auto;
  padding: 15px;
}
</style>
