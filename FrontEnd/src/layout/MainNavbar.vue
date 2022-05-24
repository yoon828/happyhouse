<template>
  <md-toolbar
    id="toolbar"
    md-elevation="0"
    class="md-transparent md-absolute"
    :class="extraNavClasses"
    :color-on-scroll="colorOnScroll"
  >
    <div class="md-toolbar-row md-collapse-lateral">
      <div class="md-toolbar-section-start">
        <a href="/">
          <h3 class="md-title">HappyHouse</h3>
        </a>
      </div>
      <div class="md-toolbar-section-end">
        <md-button
          class="md-just-icon md-simple md-toolbar-toggle"
          :class="{ toggled: toggledClass }"
          @click="toggleNavbarMobile()"
        >
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </md-button>

        <div class="md-collapse">
          <div class="md-collapse-wrapper">
            <mobile-menu nav-mobile-section-start="false">
              <!-- Here you can add your items from the section-start of your toolbar -->
            </mobile-menu>
            <md-list>
              <md-list-item href="#/house">
                <p>거래조회</p>
              </md-list-item>
              <md-list-item href="#/mylike" v-if="userInfo">
                <p>관심지역</p>
              </md-list-item>
              <md-list-item href="#/news">
                <p>뉴스</p>
              </md-list-item>
              <md-list-item href="#/board">
                <p>Q&A 게시판</p>
              </md-list-item>
              <md-list v-if="!userInfo">
                <md-list-item href="#/member/regist">
                  <p>회원 가입</p>
                </md-list-item>

                <md-list-item href="#/member/login">
                  <p>로그인</p>
                </md-list-item>

                <md-list-item>
                  <a href="#/member/idfind">아이디/</a>
                  <a href="#/member/pwfind">비밀번호 찾기</a>
                </md-list-item></md-list
              >

              <md-list v-if="userInfo">
                <md-list-item href="#/food">
                  <p>동네 맛집</p>
                </md-list-item>
              </md-list>

              <md-list v-if="userInfo && userInfo.usertype === 'G'">
                <md-list-item href="#/member/mypage">
                  <p>마이 페이지</p>
                </md-list-item>
              </md-list>
              <md-list v-if="userInfo && userInfo.usertype === 'A'">
                <md-list-item href="#/member/adminList">
                  <p>회원 관리</p>
                </md-list-item>
              </md-list>
              <md-list v-if="userInfo">
                <md-list-item @click="OnclickLogout">
                  <b-icon icon="box-arrow-in-right"></b-icon>
                </md-list-item>
              </md-list>
            </md-list>
          </div>
        </div>
      </div>
    </div>
  </md-toolbar>
</template>

<script>
let resizeTimeout;
function resizeThrottler(actualResizeHandler) {
  // ignore resize events as long as an actualResizeHandler execution is in the queue
  if (!resizeTimeout) {
    resizeTimeout = setTimeout(() => {
      resizeTimeout = null;
      actualResizeHandler();

      // The actualResizeHandler will execute at a rate of 15fps
    }, 66);
  }
}
import { mapState, mapMutations, mapActions } from "vuex";
import MobileMenu from "@/layout/MobileMenu";
const memberStore = "memberStore";
export default {
  components: {
    MobileMenu,
  },
  props: {
    type: {
      type: String,
      default: "white",
      validator(value) {
        return [
          "white",
          "default",
          "primary",
          "danger",
          "success",
          "warning",
          "info",
        ].includes(value);
      },
    },
    colorOnScroll: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      extraNavClasses: "",
      toggledClass: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["clearLikeList"]),
    bodyClick() {
      let bodyClick = document.getElementById("bodyClick");

      if (bodyClick === null) {
        let body = document.querySelector("body");
        let elem = document.createElement("div");
        elem.setAttribute("id", "bodyClick");
        body.appendChild(elem);

        let bodyClick = document.getElementById("bodyClick");
        bodyClick.addEventListener("click", this.toggleNavbarMobile);
      } else {
        bodyClick.remove();
      }
    },
    toggleNavbarMobile() {
      this.NavbarStore.showNavbar = !this.NavbarStore.showNavbar;
      this.toggledClass = !this.toggledClass;
      this.bodyClick();
    },
    handleScroll() {
      let scrollValue =
        document.body.scrollTop || document.documentElement.scrollTop;
      let navbarColor = document.getElementById("toolbar");
      this.currentScrollValue = scrollValue;
      if (this.colorOnScroll > 0 && scrollValue > this.colorOnScroll) {
        this.extraNavClasses = `md-${this.type}`;
        navbarColor.classList.remove("md-transparent");
      } else {
        if (this.extraNavClasses) {
          this.extraNavClasses = "";
          navbarColor.classList.add("md-transparent");
        }
      }
    },
    scrollListener() {
      resizeThrottler(this.handleScroll);
    },
    scrollToElement() {
      let element_id = document.getElementById("downloadSection");
      if (element_id) {
        element_id.scrollIntoView({ block: "end", behavior: "smooth" });
      }
    },
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    OnclickLogout() {
      sessionStorage.removeItem("token");
      this.clearLikeList();
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      this.$router.push({ name: "home" });
    },
  },
  mounted() {
    document.addEventListener("scroll", this.scrollListener);
  },
  beforeDestroy() {
    document.removeEventListener("scroll", this.scrollListener);
  },
};
</script>

<style scoped>
.md-title {
  font-size: 2rem !important;
  font-weight: bold !important;
}

p {
  font-size: 1rem !important;
}
</style>
