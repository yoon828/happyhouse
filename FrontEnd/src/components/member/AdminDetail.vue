<template>
  <div class="container">
    <div id="box" class="col-md-12 text-center">
      <h2>회원 정보</h2>
      <p slot="description" class="description">
        회원 정보 입니다.
      </p>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="pencil"></b-icon></md-icon>
        <label>이름</label>
        <md-input v-model="user.username" :readonly="true"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="person-circle"></b-icon></md-icon>
        <label>아이디</label>
        <md-input v-model="user.userid" :readonly="true"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="lock"></b-icon></md-icon>
        <label>비밀번호</label>
        <md-input
          type="password"
          v-model="userpw"
          ref="userpw"
          :readonly="true"
        ></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="mailbox"></b-icon></md-icon>
        <label>이메일</label>
        <md-input
          type="email"
          v-model="user.useraddress"
          :readonly="true"
        ></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="telephone-fill"></b-icon></md-icon>
        <label>전화번호</label>
        <md-input v-model="user.usernumber" :readonly="true"></md-input>
      </md-field>
      <md-button slot="footer" class="md-success" v-on:click="moveList()">
        목록으로
      </md-button>
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
import { getMember, deleteMember } from "@/api/member.js";
export default {
  name: "detailMember",
  data() {
    return {
      userpw: "***********",
      user: {},
    };
  },
  created() {
    getMember(this.$route.params.userid, (res) => {
      this.user = res.data;
    });
  },
  methods: {
    moveList() {
      this.$router.push({ name: "adminList" });
    },
    moveUpdate() {
      this.$router.push({
        name: "adminUpdate",
        params: { userid: this.user.userid },
      });
    },
    deleteUser() {
      if (confirm("탈퇴 하시겠습니까?")) {
        deleteMember(this.user.userid, (res) => {
          let msg = "회원 탈퇴 시에 문제가 발생했습니다.";
          let err = true;
          if (res.data == "success") {
            msg = "회원 탈퇴가 완료되었습니다.";
            err = false;
          }
          alert(msg);
          if (!err) {
            this.$router.push({ name: "adminList" });
          } else {
            this.$router.push({
              name: "adminDetail",
              params: { userid: this.user.userid },
            });
          }
        });
        (error) => {
          console.log(error);
        };
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
