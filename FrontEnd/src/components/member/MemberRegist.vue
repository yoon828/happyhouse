<template>
  <div class="container">
    <div id="box" class="col-md-12 text-center">
      <h2>아이디 찾기</h2>
      <p slot="description" class="description">
        찾은 아이디 입니다.
      </p>
      <md-field class="md-form-group" slot="inputs">
        <md-icon>face</md-icon>
        <label>아이디</label>
        <md-input v-model="userid"></md-input>
      </md-field>
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
import { registMember } from "@/api/member";
export default {
  data() {
    return {
      username: "",
      userid: "",
      userpw: "",
      useraddress: "",
      usernumber: "",
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/house-bg.png"),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    initData() {
      this.username = "";
      this.userid = "";
      this.userpw = "";
      this.useraddress = "";
      this.usernumber = "";
    },
    registCheck() {
      let err = false;
      let msg = "";

      if (!this.username) {
        msg = "이름을 입력해주세요.";
        err = true;
      } else if (!this.userid) {
        msg = "아이디를 입력해주세요.";
        err = true;
      } else if (!this.userpw) {
        msg = "비밀번호를 입력해주세요.";
        err = true;
      } else if (!this.useraddress) {
        msg = "이메일을 입력해주세요.";
        err = true;
      } else if (!this.usernumber) {
        msg = "전화번호를 입력해주세요.";
        err = true;
      }
      if (err) {
        alert(msg);
      } else {
        this.regist();
      }
    },
    regist() {
      registMember(
        {
          username: this.username,
          userid: this.userid,
          userpw: this.userpw,
          useraddress: this.useraddress,
          usernumber: this.usernumber,
        },
        ({ data }) => {
          let msg = "회원 가입 시 문제가 발생했습니다.";
          let err = true;
          if (data === "success") {
            msg = "회원 가입이 완료되었습니다.";
            err = false;
          }
          alert(msg);
          if (!err) {
            this.$router.push({ name: "login" });
          } else {
            this.$router.push({ name: "regist" });
          }
        },
        (error) => {
          let msg = "서버에 문제가 발생했습니다.";
          console.log(error);
          alert(msg);
        },
      );
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
