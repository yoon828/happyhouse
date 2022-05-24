/* eslint-disable prettier/prettier */

const houseStore = {
  namespaced: true,
  state: {
    houses: [], //전체 주택 목록
    housesfilter: [], //필터링 후 주택 목록
    house: null,
  },
  getters: {},
  mutations: {
    CLEAR_HOUSE_LIST: (state) => {
      state.houses = [];
    },
    CLEAR_HOUSE_FILTER_LIST: (state) => {
      state.housesfilter = [];
    },
    CLEAR_HOUSE: (state) => {
      state.house = null;
    },
    SET_HOUSE_LIST: (state, houses) => {
      //   console.log(houses);
      state.houses = houses;
    },
    SET_HOUSE_FILTER_LIST: (state, houses) => {
      //   console.log(houses);
      state.housesfilter = houses;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
  },
  actions: {
    clearHouses: ({ commit }) => {
      commit("CLEAR_HOUSE_LIST");
    },
    clearHousesFilter: ({ commit }) => {
      commit("CLEAR_HOUSE_FILTER_LIST");
    },
    clearHouse: ({ commit }) => {
      commit("CLEAR_HOUSE");
    },
    setHouses: ({ commit }, houses) => {
      commit("SET_HOUSE_LIST", houses);
    },
    setHousesFilter: ({ commit }, houses) => {
      commit("SET_HOUSE_FILTER_LIST", houses);
    },
    detailHouse: ({ commit }, house) => {
      console.log(house);
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
  },
};

export default houseStore;
