<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="
              md-layout-item
              md-size-33
              md-small-size-66
              md-xsmall-size-100
              md-medium-size-40
              mx-auto
            "
          >
            <login-card header-color="green">
              <h4 slot="title" class="card-title">아이디 찾기</h4>
              <p slot="description" class="description">
                아이디를 찾기 위해 정보를 입력해주세요.
              </p>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>face</md-icon>
                <label>이름</label>
                <md-input v-model="username"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>email</md-icon>
                <label>이메일</label>
                <md-input v-model="useraddress"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>phone</md-icon>
                <label>전화 번호</label>
                <md-input v-model="usernumber"></md-input>
              </md-field>
              <md-button
                slot="footer"
                class="md-success"
                v-on:click="idFindCheck()"
              >
                아이디 찾기
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
import { idFindMember } from "@/api/member";
export default {
  components: {
    LoginCard,
  },
  bodyClass: "login-page",
  data() {
    return {
      username: null,
      useraddress: null,
      usernumber: null,
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
      this.useraddress = "";
      this.usernumber = "";
    },
    idFindCheck() {
      let err = true;
      let msg = "";

      if (!this.username) {
        msg = "이름을 입력해주세요.";
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
        this.idFind();
      }
    },
    idFind() {
      idFindMember(
        {
          username: this.username,
          useraddress: this.useraddress,
          usernumber: this.usernumber,
        },
        ({ data }) => {
          let msg = "아이디 찾기에 성공했습니다.";
          let err = false;
          if (data === "fail") {
            msg = "입력 정보를 확인하세요.";
          }
          let findId = data;
          alert(msg);
          alert(findId);
          if (!err) {
            this.$router.push({ name: "login" });
          } else {
            this.$router.push({ name: "idFind" });
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

<style></style>
