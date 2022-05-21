import { login, deleteMember, updateMember } from "@/api/member.js";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    findPassword: null,
    findId: null,
    isUpdate: false,
    isDelete: false,
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
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {},
      );
    },
    async userUpdate({ commit }, user) {
      await updateMember(
        user,
        (response) => {
          if (response.data === "success") {
            commit("SET_IS_UPDATE", true);
          } else {
            commit("SET_IS_UPDATE", false);
          }
        },
        () => {},
      );
    },
  },
};
export default memberStore;
