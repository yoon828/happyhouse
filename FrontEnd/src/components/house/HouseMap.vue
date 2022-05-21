/* eslint-disable prettier/prettier */
<template>
  <b-container>
    <div id="map" class="map" style="height:600px"></div>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseMap",
  components: {},
  data() {
    return {
      initPostion: [],
      markers: [],
      map: null,
      places: null,
      infoWindow: null,
      customOverlay: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "house"]),
    // listUpdate: function() {
    //   return displayMarkers(this.houses);
    // },
  },
  watch: {
    houses: function() {
      if (this.houses.length != 0 && this.houses) {
        this.displayMarkers();
      }
    },
    house: function() {
      this.setMapCenter();
    },
  },
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    //카카오 map api를 사용하기 위한 초기 설정 메소드
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=60af92db1e1ad1752c5d6c3b2817ea5b&libraries=services";
      document.head.appendChild(script);
    },
    initMap() {
      const mapContainer = document.getElementById("map"); // 지도를 표시할 div let
      let mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };
      this.map = new kakao.maps.Map(mapContainer, mapOption);
      this.infoWindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    },
    displayMarkers() {
      let map = this.map;
      let bounds = new kakao.maps.LatLngBounds();
      this.removeMarker();

      this.houses.map((house) => {
        let placePosition = new kakao.maps.LatLng(
          house.lat,
          // eslint-disable-next-line prettier/prettier
          house.lng
        );
        let mk = this.addMarker(placePosition);
        //지도 범위를 재설정하기 위해 LatLngBounds에 좌표를 추가
        bounds.extend(placePosition);

        //마커와 검색 결과 항목에 mouseover 했을때 해당 장소에 인포윈도우에 장소명 표시
        let content = `<div class="infowindow">
                          <span class="title">
                            ${house.apartmentName}
                            </span>
                      </div>`;
        let overlay = new kakao.maps.CustomOverlay({
          content: content,
          map: map,
          position: mk.getPosition(),
        });
        overlay.setMap(null);
        //마커 클릭시 vuex에 해당 아파트 정보 저장하고, 모달창 띄우기
        kakao.maps.event.addListener(mk, "click", () => {
          this.setDetail(house);
        });
        // kakao.maps.event.addListener(mk, "click", function() {
        //   //마커 클릭시 해당 좌표를 중심으로 이동 , 레벨 변경
        //   map.setCenter(placePosition);
        //   if (map.getLevel() > 4) {
        //     map.setLevel(3, { anchor: placePosition });
        //   }
        //   overlay.setMap(map);
        // });
        kakao.maps.event.addListener(mk, "mouseover", function() {
          overlay.setMap(map);
        });
        kakao.maps.event.addListener(mk, "mouseout", function() {
          overlay.setMap(null);
        });
      });
      //검색된 장소 위치를 기준으로 지도 범위 재설정
      this.map.setBounds(bounds);
    },
    setDetail(house) {
      this.detailHouse(house);
      this.$emit("show-modal");
    },
    removeMarker() {
      for (let i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    //마커 추가하기
    addMarker(position) {
      let imageSrc =
          "https://images.vexels.com/media/users/3/142675/isolated/lists/84e468a8fff79b66406ef13d3b8653e2-house-location-marker-icon.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(50, 50), // 마커 이미지의 크기
        imgOptions = {
          // offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          // eslint-disable-next-line prettier/prettier
          imgOptions
        ),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },
    setMapCenter() {
      let moveLatLon = new kakao.maps.LatLng(this.house.lat, this.house.lng);
      this.map.setCenter(moveLatLon);
      this.map.setLevel(2);
    },
  },
};
</script>
<style>
.map {
  width: 100%;
  height: 400px;
}
.infowindow {
  padding: 5px 10px;
  background-color: white;
  position: relative;
  bottom: 70px;
  border-radius: 10px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
</style>
