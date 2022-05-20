<template>
  <div class="container">
    <div id="box" class="col-md-12">
      <div class="box-content"><h2>마이 페이지</h2></div>

      <div class="box-content">
        <md-icon>man</md-icon>
        <label for=""> 이름 :</label>
        {{ userInfo.username }}
      </div>
      <div class="box-content">
        <md-icon>face</md-icon>
        <label for=""> 아이디 :</label>
        {{ userInfo.userid }}
      </div>
      <div class="box-content">
        <md-icon>lock_outline</md-icon>
        <label for=""> 비밀번호 :</label>
        **********
      </div>

      <div class="box-content">
        <md-icon>email</md-icon>
        <label for=""> 이메일 : </label>
        {{ userInfo.useraddress }}
      </div>
      <div class="box-content">
        <md-icon>phone</md-icon>
        <label for=""> 전화 번호 : </label>
        {{ userInfo.usernumber }}
      </div>
      <div>
        <b-button variant="success" @click="moveUpdate">회원 수정</b-button
        ><b-button variant="warning">회원 탈퇴</b-button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
const memberStore = "memberStore";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm({ userid: this.userid, userpw: this.userpw });
      if (this.isLogin) {
        alert("내 정보를 조회하겠습니다.");
        this.$router.push({ name: "home" });
      } else {
        alert("비밀번호를 다시 확인하세요.");
      }
    },
    confirmCheck() {
      if (this.userpw === "null") {
        alert("비밀번호를 입력해주세요.");
      }
    },
    moveUpdate() {
      this.$router.push({ name: "update" });
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
  margin: 0 auto;
  padding: 15px;
}
table {
  margin-left: auto;
  margin-right: auto;
}
.box-content {
  padding: 15px;
}
button {
  margin: 15px;
}
</style>
