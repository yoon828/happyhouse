import {
  login,
  deleteMember,
  updateMember,
  selectLikeDong,
} from "@/api/member.js";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    findPw: null,
    findId: null,
    isUpdate: false,
    isDelete: false,
    likeList: [],
  },
  getters: {
    checkUserInfo: function(state) {
      return state.userInfo;
    },
    getUserName: function(state) {
      return state.userInfo.username;
    },
    getUserId: function(state) {
      return state.userInfo.userid;
    },
    getUserPw: function(state) {
      return state.userInfo.userpw;
    },
    getUserAddress: function(state) {
      return state.userInfo.useraddress;
    },
    getUserNumber: function(state) {
      return state.userInfo.usernumber;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_IS_UPDATE: (state, isUpdate) => {
      state.isUpdate = isUpdate;
    },
    SET_IS_DELETE: (state, isDelete) => {
      state.isDelete = isDelete;
    },
    SET_FINDID: (state, findId) => {
      state.findId = findId;
    },
    SET_FINDPW: (state, findPw) => {
      state.findPw = findPw;
    },
    SET_LIKE_LIST: (state, likeList) => {
      state.likeList = likeList;
    },
    CLEAR_LIKE_LIST: (state) => {
      state.likeList = [];
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(user, (response) => {
        if (response.data.message === "success") {
          commit("SET_IS_LOGIN", true);
          commit("SET_IS_LOGIN_ERROR", false);
          commit("SET_USER_INFO", response.data.userInfo);
          sessionStorage.setItem("token", response.data.token);
        } else {
          commit("SET_IS_LOGIN", false);
          commit("SET_IS_LOGIN_ERROR", true);
        }
      });
    },
    async userUpdate({ commit }, user) {
      await updateMember(user, (response) => {
        if (response.data === "success") {
          commit("SET_IS_UPDATE", true);
        } else {
          commit("SET_IS_UPDATE", false);
        }
      });
    },
    async userDelete({ commit }, userid) {
      await deleteMember(userid, (response) => {
        if (response.data === "success") {
          commit("SET_IS_DELETE", true);
        } else {
          commit("SET_IS_DELETE", false);
        }
      });
    },
    async idFindSet({ commit }, userid) {
      commit("SET_FINDID", userid);
    },
    async pwFindSet({ commit }, userpw) {
      commit("SET_FINDPW", userpw);
    },
    setLikeList: ({ commit }, userid) => {
      selectLikeDong(
        {
          userid: userid,
        },
        ({ data }) => {
          console.log(data);
          commit("SET_LIKE_LIST", data);
          // eslint-disable-next-line prettier/prettier
        }
      );
    },
    clearLikeList({ commit }) {
      commit("CLEAR_LIKE_LIST");
    },
  },
};
export default memberStore;
