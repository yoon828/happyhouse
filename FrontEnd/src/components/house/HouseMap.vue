<template>
  <b-container>
    <div id="map">지도</div>
  </b-container>
</template>

<script>
export default {
  name: "HouseList",
  components: {},
  data() {
    return {
      initPostion: [],
      markers: [],
    };
  },
  computed: {},
  methods: {
    mounted() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=60af92db1e1ad1752c5d6c3b2817ea5b&libraries=services";
      document.head.appendChilde(script);
    },
    created() {
      initMap();
    },
    initMap() {
      const mapContainer = document.getElementById("map"); // 지도를 표시할 div let
      mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };
      this.map = newkakao.maps.Map(mapContainer, mapOption);
    },
  },
};
</script>
