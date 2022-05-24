<template>
  <div class="container">
    <div id="box" class="col-md-12 text-center">
      <h2>관리자 등록</h2>
      <p slot="description" class="description">
        happy house에 오신 걸 환영 합니다.
      </p>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="pencil"></b-icon></md-icon>
        <label>이름</label>
        <md-input v-model="username"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="person-circle"></b-icon></md-icon>
        <label>아이디</label>
        <md-input v-model="userid"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="lock"></b-icon></md-icon>
        <label>비밀번호</label>
        <md-input type="password" v-model="userpw" ref="userpw"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="mailbox"></b-icon></md-icon>
        <label>이메일</label>
        <md-input type="email" v-model="useraddress"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="telephone-fill"></b-icon></md-icon>
        <label>전화 번호</label>
        <md-input v-model="usernumber"></md-input>
      </md-field>

      <md-button slot="footer" class="md-success" v-on:click="registCheck()">
        관리자 등록
      </md-button>

      <md-button slot="footer" class="md-warning" v-on:click="initData()">
        초기화
      </md-button>
    </div>
  </div>
</template>

<script>
import { registAdmin } from "@/api/member";
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
      registAdmin(
        {
          username: this.username,
          userid: this.userid,
          userpw: this.userpw,
          useraddress: this.useraddress,
          usernumber: this.usernumber,
        },
        ({ data }) => {
          let msg = "관리자 등록 시 문제가 발생했습니다.";
          let err = true;
          if (data === "success") {
            msg = "관리자 등록이 완료되었습니다.";
            err = false;
          }
          alert(msg);
          if (!err) {
            this.$router.push({ name: "adminList" });
          } else {
            this.$router.push({ name: "adminRegist" });
          }
        },
        (error) => {
          console.log(error);
          // eslint-disable-next-line prettier/prettier
        }
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
