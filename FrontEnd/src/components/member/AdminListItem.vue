<template>
  <b-tr>
    <b-td>{{ userid }}</b-td>
    <b-td>{{ useraddress }}</b-td>
    <b-td>
      <b-form-select v-if="userid === userInfo.userid" disabled>
        <b-form-select-option value="관리자">관리자</b-form-select-option>
      </b-form-select>
      <b-form-select
        v-else
        v-model="selected"
        :options="options"
        value-field="item"
        text-field="name"
        @change="changeGrade"
      ></b-form-select>
    </b-td>
    <b-th
      ><router-link
        :to="{
          name: 'adminDetail',
          params: { userid: userid },
        }"
        >상세 정보</router-link
      ></b-th
    >
  </b-tr>
</template>

<script>
import { mapState } from "vuex";
import { gradeAdmin } from "@/api/member.js";

const memberStore = "memberStore";

export default {
  name: "AdminListItem",
  props: {
    userid: String,
    userpw: String,
    useraddress: String,
    usernumber: String,
    username: String,
    usertype: String,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      selected: this.usertype === "A" ? "A" : "G",
      options: [
        { item: "G", name: "사용자" },
        { item: "A", name: "관리자" },
      ],
    };
  },
  methods: {
    changeGrade() {
      gradeAdmin(
        {
          type: this.selected,
          userid: this.userid,
        },
        ({ data }) => {
          if (data == "fail") {
            alert("문제가 발생했습니다.");
            if (this.selected == "A") this.selected = "G";
            else this.selected = "A";
          }
          // eslint-disable-next-line prettier/prettier
        }
      );
    },
  },
};
</script>

<style></style>
