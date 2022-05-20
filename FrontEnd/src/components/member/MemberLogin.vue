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
        <md-input v-model="user.userid"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon>lock_outline</md-icon>
        <label>비밀번호</label>
        <md-input
          type="password"
          v-model="user.userpw"
          @keyup.enter="confirm"
        ></md-input>
      </md-field>
      <md-button slot="footer" class="md-success" @click="confirm">
        로그인
      </md-button>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStroe = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        userid: null,
        userpw: null,
      },
    };
  },
  computed: {
    ...mapState(memberStroe, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStroe, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
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
