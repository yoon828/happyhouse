<template>
  <div class="container">
    <div id="box" class="col-md-12 text-center">
      <h2>마이 페이지</h2>
      <p slot="description" class="description">
        내 정보 입니다.
      </p>
      <md-field class="md-form-group" slot="inputs">
        <md-icon>man</md-icon>
        <label>이름</label>
        <md-input v-model="userInfo.username" :readonly="true"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon>face</md-icon>
        <label>아이디</label>
        <md-input v-model="userInfo.userid" :readonly="true"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon>lock_outline</md-icon>
        <label>비밀번호</label>
        <md-input
          type="password"
          v-model="defaultpw"
          ref="userpw"
          :readonly="true"
        ></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon>email</md-icon>
        <label>이메일</label>
        <md-input
          type="email"
          v-model="userInfo.useraddress"
          :readonly="true"
        ></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon>phone</md-icon>
        <label>전화 번호</label>
        <md-input v-model="userInfo.usernumber" :readonly="true"></md-input>
      </md-field>

      <md-button slot="footer" class="md-success" v-on:click="moveUpdate()">
        회원 수정
      </md-button>

      <md-button slot="footer" class="md-warning" v-on:click="deleteUser()">
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
      defaultpw: "***********",
    };
  },
  computed: {
    ...mapState(memberStore, [
      "userInfo",
      "isLogin",
      "isLoginError",
      "isUpdate",
    ]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_USER_INFO", "SET_IS_LOGIN"]),
    moveUpdate() {
      this.$router.push({ name: "update" });
    },
    deleteUser() {
      if (confirm("탈퇴 하시겠습니까?")) {
        if (this.isLogin) {
          http
            .delete(`userapi/delete/${this.userInfo.userid}`)
            .then(({ data }) => {
              alert("탈퇴가 완료 되었습니다.");
              this.SET_USER_INFO(null);
              this.SET_IS_LOGIN(false);
              this.$router.push({ name: "home" });
            });
        }
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
