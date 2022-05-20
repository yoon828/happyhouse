<template>
  <div class="container">
    <div id="box" class="col-md-12">
      <div class="box-content"><h2>내 정보 수정</h2></div>

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
        <label for=""> 비밀번호 : </label>
        <input type="password" v-model="userpw" />
      </div>

      <div class="box-content">
        <md-icon>email</md-icon>
        <label for=""> 이메일 : </label>
        <input type="text" v-model="useraddress" />
      </div>
      <div class="box-content">
        <md-icon>phone</md-icon>
        <label for=""> 전화 번호 : </label>
        <input type="password" v-model="usernumber" />
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
    return {
      userid: userInfo.userid,
      userpw: null,
      username: userInfo.username,
      useraddress: null,
      usernumber: null,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
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
  margin: 15px;
}
button {
  margin: 5px;
}
label {
  margin: 10px;
}
</style>
