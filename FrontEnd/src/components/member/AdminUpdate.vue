<template>
  <div class="container">
    <div id="box" class="col-md-12 text-center">
      <h2>회원 정보 수정</h2>
      <p slot="description" class="description">
        회원 정보 수정입니다.
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
        <md-input type="password" v-model="userpw" ref="userpw"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="mailbox"></b-icon></md-icon>
        <label>이메일</label>
        <md-input type="email" v-model="user.useraddress"></md-input>
      </md-field>
      <md-field class="md-form-group" slot="inputs">
        <md-icon><b-icon icon="telephone-fill"></b-icon></md-icon>
        <label>전화 번호</label>
        <md-input v-model="user.usernumber"></md-input>
      </md-field>
      <md-button slot="footer" class="md-success" v-on:click="moveList()">
        목록으로
      </md-button>
      <md-button slot="footer" class="md-success" v-on:click="updateCheck()">
        회원 수정
      </md-button>

      <md-button slot="footer" class="md-warning" v-on:click="deleteUser()">
        회원 탈퇴
      </md-button>
    </div>
  </div>
</template>

<script>
import { getMember, updateMember, deleteMember } from "@/api/member.js";
export default {
  data() {
    return {
      user: {},
      userpw: null,
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
    updateCheck() {
      let err = false;
      let msg = "";
      if (!this.userpw) {
        msg = "비밀 번호를 입력해주세요.";
        err = true;
      } else if (!this.user.useraddress) {
        msg = "이메일을 입력해주세요";
        err = true;
      } else if (!this.user.usernumber) {
        msg = "전화 번호를 입력해주세요.";
        err = true;
      }
      if (err) {
        alert(msg);
      } else {
        this.updateAdmin();
      }
    },
    updateAdmin() {
      updateMember(
        {
          userid: this.user.userid,
          userpw: this.userpw,
          username: this.user.username,
          useraddress: this.user.useraddress,
          usernumber: this.user.usernumber,
        },
        ({ data }) => {
          let msg = "회원 수정 시 문제가 발생했습니다.";
          let err = true;
          if (data === "success") {
            msg = "회원 수정이 완료되었습니다.";
            err = false;
          }
          alert(msg);
          if (!err) {
            this.$router.push({
              name: "adminDetail",
              params: { userid: this.user.userid },
            });
          } else {
            this.$router.push({
              name: "adminUpdate",
              params: { userid: this.user.userid },
            });
          }
          // eslint-disable-next-line prettier/prettier
        }
      );
    },
    deleteUser() {
      if (confirm("탈퇴 하시겠습니까?")) {
        deleteMember(this.user.userid),
          ({ data }) => {
            let msg = "회원 탈퇴 시에 문제가 발생했습니다.";
            let err = true;
            if (data === "success") {
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
          },
          (error) => {
            console.log(error);
          };
      }
    },
  },
};
</script>

<style></style>
