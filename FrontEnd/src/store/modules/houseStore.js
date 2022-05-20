/* eslint-disable prettier/prettier */

const houseStore = {
  namespaced: true,
  state: {
    houses: [],
    house: null,
  },
  getters: {},
  mutations: {
    CLEAR_HOUSE_LIST: (state) => {
      state.houses = [];
    },
    SET_HOUSE_LIST: (state, houses) => {
      //   console.log(houses);
      state.houses = houses;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
  },
  actions: {
    clearHouses: ({ commit }) => {
      commit("CLEAR_HOUSE_LIST");
    },
    setHouses: ({ commit }, houses) => {
      commit("SET_HOUSE_LIST", houses);
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
  },
};

export default houseStore;
