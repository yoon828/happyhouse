<template>
  <b-container class="bv-example-col mt-3 mb-10 wrapper flex-direction-col">
    <br />
    <h2>회원 리스트</h2>
    <br />
    <div class="mb-1">
      <md-button @click="moveRegist()">회원 등록</md-button>
      <md-button @click="moveAdminRegist()">관리자 등록</md-button>
    </div>
    <b-row class="mt-3 mb-5">
      <b-col>
        <b-table-simple hover responsive class="mb-6">
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>아이디</b-th>
              <b-th>비밀 번호</b-th>
              <b-th>유저 타입</b-th>
              <b-th></b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <admin-list-item
              v-for="member in memberList"
              :key="member.userid"
              v-bind="member"
            />
          </tbody>
        </b-table-simple>
        <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listMember } from "@/api/member.js";
import AdminListItem from "@/components/member/AdminListItem";
export default {
  name: "AdminList",
  components: {
    AdminListItem,
  },
  data() {
    return {
      memberList: [],
    };
  },
  created() {
    listMember((response) => {
      this.memberList = response.data;
    });
  },
  methods: {
    moveRegist() {
      this.$router.push({ name: "adminUserRegist" });
    },
    moveAdminRegist() {
      this.$router.push({ name: "adminRegist" });
    },
  },
};
</script>

<style></style>
