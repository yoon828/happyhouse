<template>
  <div class="container">
    <div id="box" class="col-md-12 text-center">
      <h2>아이디 찾기</h2>
      <p slot="description" class="description">
        아이디를 찾기 위해 정보를 입력해주세요.
      </p>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="person-circle"></b-icon></md-icon>
        <label>이름</label>
        <md-input v-model="username"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="mailbox"></b-icon></md-icon>
        <label>이메일</label>
        <md-input v-model="useraddress"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="telephone-fill"></b-icon></md-icon>
        <label>전화 번호</label>
        <md-input v-model="usernumber"></md-input>
      </md-field>
      <md-button slot="footer" class="md-success" v-on:click="idFindCheck()">
        아이디 찾기
      </md-button>
      <md-button slot="footer" class="md-warning" v-on:click="initData()">
        초기화
      </md-button>
    </div>
  </div>
</template>

<script>
import { idFindMember } from "@/api/member";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
export default {
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
    ...mapState(memberStore, ["findId"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    ...mapActions(memberStore, ["idFindSet"]),
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
            err = true;
          }
          alert(msg);
          if (!err) {
            this.idFindSet(data);
            this.$router.push({ name: "idfindResult" });
          } else {
            this.$router.push({ name: "idfind" });
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
