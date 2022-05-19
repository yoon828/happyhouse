<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="green">
              <h4 slot="title" class="card-title">내 프로필 수정</h4>
              <p slot="description" class="description">
                내 프로필 수정입니다.
              </p>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>man</md-icon>
                <label>이름</label>
                <md-input v-model="username" readonly></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>face</md-icon>
                <label>아이디</label>
                <md-input v-model="userid" readonly></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>비밀번호</label>
                <md-input type="password" v-model="userpw"></md-input>
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

              <md-button slot="footer" class="md-success">
                회원 가입
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

export default {
  components: {
    LoginCard,
  },
  bodyClass: "login-page",
  data() {
    return {
      username: null,
      userid: null,
      userpw: null,
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
};
</script>

<style lang="css"></style>
