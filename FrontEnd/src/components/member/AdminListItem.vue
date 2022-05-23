<template>
  <b-tr>
    <b-td>{{ userid }}</b-td>
    <b-td>************</b-td>
    <b-td v-if="this.usertype === 'A'">관리자</b-td>
    <b-td v-else>사용자</b-td>
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
};
</script>

<style></style>
