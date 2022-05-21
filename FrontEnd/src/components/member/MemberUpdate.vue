<template>
  <div class="container">
    <div id="box" class="col-md-12 text-center">
      <h2>내 정보 수정</h2>
      <p slot="description" class="description">
        내 정보 수정 입니다.
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
        <label>비밀번호(수정 가능)</label>
        <md-input type="password" v-model="userpw" ref="userpw"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon>email</md-icon>
        <label>이메일(수정 가능)</label>
        <md-input type="email" v-model="userInfo.useraddress"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon>phone</md-icon>
        <label>전화 번호(수정 가능)</label>
        <md-input v-model="userInfo.usernumber"></md-input>
      </md-field>

      <md-button slot="footer" class="md-success" v-on:click="checkUpdate()">
        회원 수정
      </md-button>

      <md-button slot="footer" class="md-warning" v-on:click="deleteUser()">
        회원 탈퇴
      </md-button>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
const memberStore = "memberStore";
export default {
  data() {
    return {
      userpw: null,
      useraddress: null,
      usernumber: null,
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
    ...mapActions(memberStore, ["userConfirm", "userUpdate"]),
    async update() {
      await this.userUpdate({
        userid: this.userInfo.userid,
        userpw: this.userpw,
        username: this.userInfo.username,
        useraddress: this.userInfo.useraddress,
        usernumber: this.userInfo.usernumber,
      });
      if (this.isUpdate) {
        alert("정보 수정 성공");
        await this.userConfirm({
          userid: this.userInfo.userid,
          userpw: this.userpw,
        });
        this.$router.push({ name: "mypage" });
      } else {
        alert("정보 수정 실패.");
      }
    },
    checkUpdate() {
      let err = false;
      let msg = "";
      if (!this.userpw) {
        msg = "비밀 번호를 입력해주세요.";
        err = true;
      } else if (!this.userInfo.useraddress) {
        msg = "이메일을 입력해주세요";
        err = true;
      } else if (!this.userInfo.usernumber) {
        msg = "전화 번호를 입력해주세요.";
        err = true;
      }
      if (err) {
        alert(msg);
      } else {
        this.update();
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
  margin: 5px;
}
label {
  margin: 10px;
}
</style>
