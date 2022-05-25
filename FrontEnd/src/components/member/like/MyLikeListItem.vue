<template>
  <b-tr>
    <b-td class="a-c">{{ idx }}</b-td>
    <b-td class="a-c">{{ sidoName }} {{ gugunName }} {{ dongName }} </b-td>
    <b-td class="a-c">{{ this.count }} 명</b-td>
    <b-td class="a-c">
      <b-button
        type="button"
        variant="warning"
        @click="goLikeDong"
        class="wh my-2"
      >
        조회
      </b-button>
    </b-td>
    <b-td class="a-c">
      <b-button type="button" variant="danger" @click="deleteDong" class="wh">
        삭제
      </b-button>
    </b-td>
  </b-tr>
</template>

<script>
import { deleteLikeDong, countLike } from "@/api/member.js";
import { mapActions, mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "MyLikeListItem",
  props: {
    idx: Number,
    dongCode: String,
    dongName: String,
    gugunCode: String,
    gugunName: String,
    sidoCode: String,
    sidoName: String,
  },
  components: {},
  data() {
    return {
      count: null,
    };
  },
  created() {
    countLike(this.dongCode, ({ data }) => {
      this.count = data;
    });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["setLikeList"]),
    goLikeDong() {
      this.$router.push({
        name: "house",
        params: {
          sidoCodeParam: this.sidoCode,
          gugunCodeParam: this.gugunCode,
          dongCodeParam: this.dongCode,
        },
      });
    },
    deleteDong() {
      deleteLikeDong(
        {
          dongCode: this.dongCode,
          userid: this.userInfo.userid,
        },
        ({ data }) => {
          if (data == "success") {
            //vuex 리스트 다시 조회
            this.setLikeList(this.userInfo.userid);
          } else {
            alert("관심지역 해제에 실패하였습니다.");
          }
          // eslint-disable-next-line prettier/prettier
        }
      );
    },
  },
};
</script>

<style scoped>
.wh {
  color: white;
}
.a-c {
  vertical-align: baseline;
  padding: 0;
}
</style>
