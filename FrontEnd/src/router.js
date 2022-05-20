import Vue from "vue";
import Router from "vue-router";
import HomeView from "./views/HomeView.vue";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import BoardView from "./views/BoardView.vue";
import HouseView from "./views/HouseView.vue";
import MemberView from "./views/MemberView.vue";

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters[""];
};
Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "home",
      components: { default: HomeView, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
    },
    {
      path: "/house",
      name: "house",
      components: {
        default: HouseView,
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
    },
    {
      path: "/board",
      name: "board",
      components: {
        default: BoardView,
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
      redirect: "/board/list",
      children: [
        {
          path: "list",
          name: "boardList",
          component: () => import("@/components/board/BoardList.vue"),
        },
        {
          path: "regist",
          name: "boardRegister",
          component: () => import("@/components/board/BoardRegister.vue"),
        },
        {
          path: "detail/:articleno",
          name: "boardDetail",
          component: () => import("@/components/board/BoardDetail.vue"),
        },
        {
          path: "modify/:articleno",
          name: "boardModify",
          component: () => import("@/components/board/BoardModify.vue"),
        },
        {
          path: "delete/:articleno",
          name: "boardDelete",
          component: () => import("@/components/board/BoardDelete.vue"),
        },
      ],
    },
    {
      path: "/member",
      name: "member",

      components: {
        default: MemberView,
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
      redirect: "/member/login",
      children: [
        {
          path: "login",
          name: "login",
          component: () => import("@/components/member/MemberLogin.vue"),
        },
        {
          path: "idfind",
          name: "idfind",
          component: () => import("@/components/member/MemberIdFind.vue"),
        },
        {
          path: "pwfind",
          name: "pwfind",
          component: () => import("@/components/member/MemberPwFind.vue"),
        },
        {
          path: "mypage",
          name: "mypage",
          component: () => import("@/components/member/MemberMyPage.vue"),
        },
        {
          path: "regist",
          name: "regist",
          component: () => import("@/components/member/MemberRegist.vue"),
        },
        {
          path: "update",
          name: "update",
          component: () => import("@/components/member/MemberUpdate.vue"),
        },
        {
          path: "delete",
          name: "delete",
          component: () => import("@/components/member/MemberDelete.vue"),
        },
      ],
    },
  ],
  scrollBehavior: (to) => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  },
});
