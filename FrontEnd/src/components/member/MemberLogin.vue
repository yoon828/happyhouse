<template>
  <div class="container">
    <div id="box" class="col-md-12 text-center">
      <h2>로그인</h2>
      <p slot="description" class="description">
        happy house에 오신 걸 환영 합니다.
      </p>
      <md-field class="md-form-group" slot="inputs">
        <md-icon>face</md-icon>
        <label>아이디</label>
        <md-input v-model="userid"></md-input>
      </md-field>
      <p></p>
      <md-field class="md-form-group" slot="inputs">
        <md-icon>lock_outline</md-icon>
        <label>비밀번호</label>
        <md-input
          type="password"
          v-model="userpw"
          @keyup.enter="confirm"
        ></md-input>
      </md-field>
      <md-button slot="footer" class="md-success" @click="confirm">
        로그인
      </md-button>
      <md-button slot="footer" class="md-warning" @click="initData">
        초기화
      </md-button>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      userid: null,
      userpw: null,
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo", "setLikeList"]),
    async confirm() {
      await this.userConfirm({ userid: this.userid, userpw: this.userpw });
      if (this.isLogin) {
        alert("로그인 성공");
        //로그인시 관심지역 불러오기
        this.setLikeList(this.userid);
        this.$router.push({ name: "home" });
      } else {
        alert("입력 정보를 다시 확인하세요.");
      }
    },
    initData() {
      this.userid = "";
      this.userpw = "";
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
