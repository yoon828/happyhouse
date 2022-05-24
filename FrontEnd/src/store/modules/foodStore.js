const foodStore = {
  namespaced: true,
  state: {
    foods: [],
  },
  mutations: {
    CLEAR_FOOD_LIST: (state) => {
      state.foods = [];
    },
    SET_FOOD_LIST: (state, foods) => {
      state.foods = foods;
    },
  },
  actions: {
    clearFoods: ({ commit }) => {
      commit("CLEAR_FOOD_LIST");
    },
    setFoods: ({ commit }, foods) => {
      commit("SET_FOOD_LIST", foods);
    } 
  },
};

export default foodStore;