<template>
  <div class="container">
    <div id="box" class="col-md-12 text-center">
      <h2>아이디 찾기</h2>
      <p slot="description" class="description">
        아이디 찾기 결과 입니다.
      </p>
      <br />
      <h5>찾은 아이디 : {{ findId }}</h5>
      <br />
      <md-button slot="footer" class="md-success" v-on:click="moveLogin()">
        로그인 창으로
      </md-button>
      <md-button slot="footer" class="md-warning" v-on:click="movePwFind()">
        비밀번호 찾기
      </md-button>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const memberStore = "memberStore";
export default {
  ...mapActions(memberStore, ["idFindSet"]),
  computed: {
    ...mapState(memberStore, ["findId"]),
  },
  methods: {
    moveLogin() {
      this.$router.push({ name: "login" });
    },
    movePwFind() {
      this.$router.push({ name: "pwfind" });
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
