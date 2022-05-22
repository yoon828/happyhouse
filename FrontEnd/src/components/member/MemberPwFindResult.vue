<template>
  <div class="container">
    <div id="box" class="col-md-12 text-center">
      <h2>임시 비밀번호 발급</h2>
      <p slot="description" class="description">
        임시 비밀번호로 로그인 후 마이페이지에서 비밀 번호 수정 가능합니다.
      </p>
      <br />
      <h5>임시 비밀번호 : {{ findPw }}</h5>
      <br />
      <md-button slot="footer" class="md-success" v-on:click="moveLogin()">
        로그인 창으로
      </md-button>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const memberStore = "memberStore";
export default {
  ...mapActions(memberStore, ["pwFindSet"]),
  computed: {
    ...mapState(memberStore, ["findPw"]),
  },
  methods: {
    moveLogin() {
      this.$router.push({ name: "login" });
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
