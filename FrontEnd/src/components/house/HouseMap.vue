<template>
  <b-container>
    <div id="map" class="map">지도</div>
  </b-container>
</template>

<script>
export default {
  name: "HouseMap",
  components: {},
  data() {
    return {
      initPostion: [],
      markers: [],
    };
  },
  computed: {},
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  methods: {
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=60af92db1e1ad1752c5d6c3b2817ea5b&libraries=services";
      document.head.appendChild(script);
    },
    initMap() {
      var mapContainer = document.getElementById("map"); // 지도를 표시할 div let
      var mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };
      var map = new kakao.maps.Map(mapContainer, mapOption);
    },
  },
};
</script>
<style>
.map {
  width: 100%;
  height: 400px;
}
</style>
