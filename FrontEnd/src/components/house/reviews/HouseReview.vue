<template>
  <div v-if="this.mode === 'show'" class="flex-direction-col">
    <star-rating
      :rating="showRating"
      :star-size="20"
      :read-only="true"
      :increment="0.1"
    ></star-rating>
    평점수 : {{ cntRating }}건
  </div>
  <b-container v-else class="flex-direction-col">
    <h4 class="my-3">평점</h4>
    <div class="dis">
      <span v-if="!userInfo">
        <div class="alert alert-danger d-flex align-items-center">
          <div class="alert-icon">
            <md-icon>info_outline</md-icon>
          </div>
          로그인 후 이용해주세요.
        </div>
      </span>
      <span v-else-if="isRating">
        <div class="alert alert-info d-flex align-items-center">
          <div class="alert-icon">
            <md-icon>info_outline</md-icon>
          </div>
          등록된 평점이 존재합니다.
        </div>
      </span>
      <span v-else class="text-center">
        <star-rating
          v-model="myRating"
          :increment="0.5"
          :border-width="4"
          border-color="#d8d8d8"
          :rounded-corners="true"
          :show-rating="false"
          @rating-selected="selectedRating"
          :star-points="[
            23,
            2,
            14,
            17,
            0,
            19,
            10,
            34,
            7,
            50,
            23,
            43,
            38,
            50,
            36,
            34,
            46,
            19,
            31,
            17,
          ]"
        ></star-rating>
        <b-button
          type="button"
          variant="warning"
          @click="sendReview"
          class="mt-2 "
          >평점 등록</b-button
        >
      </span>
    </div>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import StarRating from "vue-star-rating";
import { sendReviewApi, getReviewApi, getHouseApi } from "@/api/house.js";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "HouseList",
  components: { StarRating },
  props: {
    mode: String,
  },
  computed: {
    ...mapState(houseStore, ["house", "houses"]),
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  data() {
    return {
      showRating: 0,
      cntRating: 0,
      isRating: false,
      myRating: 0,
    };
  },
  watch: {
    house: function() {
      this.showRating = this.house.avgRate;
      this.cntRating = this.house.cnt;
      this.getMyRating();
    },
  },

  created() {
    this.showRating = this.house.avgRate;
    this.cntRating = this.house.cnt;
    this.getMyRating(); //아파트에 내가 등록한 리뷰있는지 조회
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse", "setHouses"]),
    selectedRating(rating) {
      this.sendRating = rating;
    },
    sendReview() {
      sendReviewApi(
        {
          aptCode: this.house.aptCode,
          userid: this.userInfo.userid,
          rate: this.sendRating,
        },
        (res) => {
          if (res.data === "success") {
            alert("평점이 등록되었습니다.");
            this.resetHouse();
          } else {
            alert("문제가 발생했습니다.");
          }
          // eslint-disable-next-line prettier/prettier
        }
      );
    },
    //현재 아파트와 사용자의 리뷰 정보 가져오기
    getMyRating() {
      if (!this.userInfo) return;
      getReviewApi(
        {
          aptCode: this.house.aptCode,
          userid: this.userInfo.userid,
        },
        (res) => {
          if (res.data) this.isRating = true;
          // eslint-disable-next-line prettier/prettier
        }
      );
    },
    //평점 등록 후 house 정보 다시 세팅하기
    resetHouse() {
      getHouseApi(
        {
          aptCode: this.house.aptCode,
        },
        (res) => {
          this.detailHouse(res.data);
          let newhouses = this.houses;
          let idx = newhouses.findIndex((h) => h.aptCode == this.house.aptCode);
          newhouses[idx] = res.data;
          this.setHouses(newhouses);
          // eslint-disable-next-line prettier/prettier
        }
      );
    },
  },
};
</script>

<style scoped>
.dis {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
