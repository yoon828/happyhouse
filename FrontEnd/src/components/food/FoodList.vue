<template>
  <div>
    <b-row class="my-4 wc mx-auto">
      <b-col class="sm-3">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="getGugunList"
          id="sido"
        >
        </b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="getDongList"
          id="gugun"
        >
        </b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select v-model="dongCode" :options="dongs" id="dong">
        </b-form-select>
      </b-col>
    </b-row>
    <b-row>
      <b-form-input
        placeholder="음식 검색"
        v-model="searchfood"
        class="col-md-3 mr-1"
        @keyup.enter="getFoodList"
      ></b-form-input>
      <b-button type="button" variant="warning" @click="getFoodList">
        <b-icon icon="search"></b-icon>
        <!-- <b-icon icon="heart-fill"></b-icon> -->
      </b-button>
    </b-row>
    <div class="container">
      <b-row class="mt-5 mb-5">
        <b-col>
          <b-table-simple hover responsive class="mb-6">
            <b-thead head-variant="dark">
              <b-tr>
                <b-th>상호명</b-th>
                <b-th>카테고리</b-th>
                <b-th>지번 주소</b-th>
                <b-th>도로명 주소</b-th>
                <b-th>링크</b-th>
              </b-tr>
            </b-thead>
            <tbody>
              <food-list-item
                v-for="food in foods"
                :key="food.title"
                v-bind="food"
              />
            </tbody>
          </b-table-simple>
          <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import {
  sidoList,
  gugunList,
  dongList,
  getAddressName,
  getFoodList,
} from "@/api/food.js";
import FoodListItem from "@/components/food/FoodListItem.vue";

export default {
  name: "RestaurantSearchBar",
  components: {
    FoodListItem,
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      sidos: [{ value: null, text: "선택" }],
      guguns: [{ value: null, text: "선택" }],
      dongs: [{ value: null, text: "선택" }],
      searchfood: null,
      foods: [],
    };
  },
  created() {
    this.getSidoList();
    let params = this.$route.params;
    if (params.sidoCodeParam) {
      this.sidoCode = params.sidoCodeParam.substr(0, 2); //시도 설정
      //구군 불러오기
      this.getGugunList();
      this.gugunCode = params.gugunCodeParam.substr(0, 5); //구군 설정
      //동 불러오기
      this.getDongList();
      this.dongCode = params.dongCodeParam; //동 설정

      //음식 키워드 설정
      this.getDongList();
      this.searchfood = params.kewordParam; //음식 키워드 설정

      this.getFoodList();
    }
  },
  methods: {
    getSidoList() {
      sidoList((res) => {
        res.data.forEach((sido) => {
          this.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
        });
      });
    },
    getGugunList() {
      this.guguns = [{ value: null, text: "선택" }];
      gugunList({ sidoCode: this.sidoCode }, (res) => {
        res.data.forEach((gugun) => {
          if (gugun.gugunName != null) {
            this.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
          }
        });
      });
    },
    getDongList() {
      this.dongs = [{ value: null, text: "선택" }];
      dongList({ sidoCode: this.gugunCode }, (res) => {
        res.data.forEach((dong) => {
          if (dong.dongName != null) {
            this.dongs.push({ value: dong.dongCode, text: dong.dongName });
          }
        });
      });
    },
    getFoodList() {
      if (this.sidoCode == null || this.sidoCode == "") {
        alert("시, 도를 선택해주세요.");
      } else if (this.gugunCode == null || this.gugunCode == "") {
        alert("구, 군을 선택해주세요.");
      } else if (this.dongCode == null || this.dongCode == "") {
        alert("동을 선택해주세요.");
      } else if (this.searchfood == null || this.searchfood == "") {
        alert("음식명을 입력해주세요.");
      } else {
        getAddressName({ dongcode: this.dongCode }, (res) => {
          let search =
            res.data.sidoName +
            " " +
            res.data.gugunName +
            " " +
            res.data.dongName +
            " " +
            this.searchfood;
          getFoodList(search, (res) => {
            this.foods = res.data.items;
          });
        });
      }
    },
  },
};
</script>

<style>
.foods {
  text-align: center;
}

b {
  font-weight: normal;
}
</style>
