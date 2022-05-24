import Vue from "vue";
import Router from "vue-router";
import HomeView from "./views/HomeView.vue";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import BoardView from "./views/BoardView.vue";
import HouseView from "./views/HouseView.vue";
import MemberView from "./views/MemberView.vue";
import NewsView from "./views/NewsView.vue";
import FoodView from "./views/FoodView.vue";
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
      ],
    },
    {
      path: "/news",
      name: "news",
      components: {
        default: NewsView,
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
      redirect: "/news/list",
      children: [
        {
          path: "list",
          name: "list",
          component: () => import("@/components/news/NewsList.vue"),
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
          path: "idfindResult",
          name: "idfindResult",
          component: () => import("@/components/member/MemberIdFindResult.vue"),
        },
        {
          path: "pwfind",
          name: "pwfind",
          component: () => import("@/components/member/MemberPwFind.vue"),
        },
        {
          path: "pwfindResult",
          name: "pwfindResult",
          component: () => import("@/components/member/MemberPwFindResult.vue"),
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
          path: "adminList",
          name: "adminList",
          component: () => import("@/components/member/AdminList.vue"),
        },
        {
          path: "adminDetail/:userid",
          name: "adminDetail",
          component: () => import("@/components/member/AdminDetail.vue"),
        },
        {
          path: "adminRegist",
          name: "adminRegist",
          component: () => import("@/components/member/AdminRegist.vue"),
        },
        {
          path: "adminUserRegist",
          name: "adminUserRegist",
          component: () => import("@/components/member/AdminUserRegist.vue"),
        },
        {
          path: "adminUpdate/:userid",
          name: "adminUpdate",
          component: () => import("@/components/member/AdminUpdate.vue"),
        },
      ],
    },
    {
      path: "/food",
      name: "food",
      components: {
        default: FoodView,
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
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
