<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="green">
              <h4 slot="title" class="card-title">회원 가입</h4>
              <p slot="description" class="description">
                happy house에 오신 걸 환영 합니다.
              </p>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>man</md-icon>
                <label>이름</label>
                <md-input v-model="username"></md-input>
              </md-field>
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
                  ref="userpw"
                ></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>email</md-icon>
                <label>이메일</label>
                <md-input type="email" v-model="useraddress"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>phone</md-icon>
                <label>전화 번호</label>
                <md-input v-model="usernumber"></md-input>
              </md-field>

              <md-button
                slot="footer"
                class="md-success"
                v-on:click="registCheck()"
              >
                회원 가입
              </md-button>

              <md-button
                slot="footer"
                class="md-success"
                v-on:click="initData()"
              >
                초기화
              </md-button>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import { registMember } from "@/api/member";
export default {
  components: {
    LoginCard,
  },
  bodyClass: "login-page",
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
      let err = true;
      let msg = "";

      if (!this.username) {
        msg = "이름을 입력해주세요.";
        err = false;
      } else if (!this.userid) {
        msg = "아이디를 입력해주세요.";
        err = false;
      } else if (!this.userpw) {
        msg = "비밀번호를 입력해주세요.";
        err = false;
      } else if (!this.useraddress) {
        msg = "이메일을 입력해주세요.";
        err = false;
      } else if (!this.usernumber) {
        msg = "전화번호를 입력해주세요.";
        err = false;
      }
      if (!err) {
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

<style lang="css"></style>
