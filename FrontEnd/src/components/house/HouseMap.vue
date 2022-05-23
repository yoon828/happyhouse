/* eslint-disable prettier/prettier */
<template>
  <b-container class="flex-direction-col">
    <b-list-group horizontal id="category" class="mb-2">
      <b-list-group-item
        class="category"
        :class="{ selected: bankSelected }"
        @click="selectBank(0)"
      >
        은행
        <!-- <b-button type="button">은행</b-button -->
      </b-list-group-item>
      <b-list-group-item
        class="category"
        :class="{ selected: martSelected }"
        @click="selectMarket"
      >
        편의점
      </b-list-group-item>
      <b-list-group-item
        class="category"
        :class="{ selected: hpSelected }"
        @click="selectHospital"
      >
        병원
      </b-list-group-item>
      <b-list-group-item
        class="category"
        :class="{ selected: subwaySelected }"
        @click="selectSubway"
      >
        지하철
      </b-list-group-item>
    </b-list-group>
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
      markers: [], //마커를 담는 배열
      map: null,
      places: null,
      infoWindow: null,
      customOverlay: null,
      ps: null, //장소 검색 객체
      sideMarkers: [[], [], [], []], //순서대로 은행, 병원, 마트 마커를 담을 배열
      bankSelected: false,
      martSelected: false,
      hpSelected: false,
      subwaySelected: false,
      imgSrcs: [
        "https://github.com/yoon828/happyhouse/blob/main/FrontEnd/src/assets/img/bank.png?raw=true",
        "https://github.com/yoon828/happyhouse/blob/main/FrontEnd/src/assets/img/market.png?raw=true",
        "https://github.com/yoon828/happyhouse/blob/main/FrontEnd/src/assets/img/hospital.png?raw=true",
        "https://github.com/yoon828/happyhouse/blob/main/FrontEnd/src/assets/img/subway.png?raw=true",
      ],
      codes: ["BK9", "CS2", "HP8", "SW8"],
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "house", "housesfilter"]),
    // listUpdate: function() {
    //   return displayMarkers(this.housesfilter);
    // },
  },
  watch: {
    housesfilter: function() {
      if (this.housesfilter.length != 0 && this.housesfilter) {
        this.displayMarkers();
      }
    },
    house: function() {
      this.setMapCenter();
    },
    houses: function() {
      this.bankSelected = false;
      this.martSelected = false;
      this.hpSelected = false;
      this.subwaySelected = false;
      for (let i = 0; i < 4; i++) {
        this.removeMarkerSide(i);
      }
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
      this.ps = new kakao.maps.services.Places(this.map);
      //지도를 이동할때마다 편의시설 조회해서 지도에 표기
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);
    },
    setDetail(house) {
      this.detailHouse(house);
      this.$emit("show-modal");
    },
    setMapCenter() {
      let moveLatLon = new kakao.maps.LatLng(this.house.lat, this.house.lng);
      this.map.setCenter(moveLatLon);
      this.map.setLevel(2);
    },
    //집 관련 마커 추가
    displayMarkers() {
      let map = this.map;
      let bounds = new kakao.maps.LatLngBounds();
      this.removeMarker();

      this.housesfilter.map((house) => {
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

    //편의시설 관련 메소드
    //은행 선택
    selectBank() {
      if (this.bankSelected) {
        this.bankSelected = false;
        //마커에 은행 제거하기
        this.removeMarkerSide(0);
      } else {
        this.bankSelected = true;
        this.findSide(0, this.codes[0]);
      }
    },
    //편의점
    selectMarket() {
      if (this.martSelected) {
        this.martSelected = false;
        //마커에 은행 제거하기
        this.removeMarkerSide(1);
      } else {
        this.martSelected = true;
        this.findSide(1, this.codes[1]);
      }
    },
    //병원
    selectHospital() {
      if (this.hpSelected) {
        this.hpSelected = false;
        //마커에 은행 제거하기
        this.removeMarkerSide(2);
      } else {
        this.hpSelected = true;
        this.findSide(2, this.codes[2]);
      }
    },
    //지하철
    selectSubway() {
      if (this.subwaySelected) {
        this.subwaySelected = false;
        //마커에 은행 제거하기
        this.removeMarkerSide(3);
      } else {
        this.subwaySelected = true;
        this.findSide(3, this.codes[3]);
      }
    },

    //idle함수
    searchPlaces() {
      if (this.bankSelected) this.findSide(0, this.codes[0]);
      if (this.martSelected) this.findSide(1, this.codes[1]);
      if (this.hpSelected) this.findSide(2, this.codes[2]);
      if (this.subwaySelected) this.findSide(3, this.codes[3]);
    },

    //시설 찾기
    findSide(idx, code) {
      this.ps.categorySearch(
        code,
        (data, status) => {
          if (status === kakao.maps.services.Status.OK) {
            this.displayMarkerSide(idx, data);
          }
        },
        // eslint-disable-next-line prettier/prettier
        { useMapBounds: true }
      );
    },

    //편의시설 관련 마커 추가
    displayMarkerSide(idx, data) {
      this.removeMarkerSide(idx);
      // idle함수가 실행되는 동안에 초기화 시키기 때문에 한번더 체크 해야함
      if (
        (idx == 0 && !this.bankSelected) ||
        (idx == 1 && !this.martSelected) ||
        (idx == 2 && !this.hpSelected) ||
        (idx == 3 && !this.subwaySelected)
      )
        return;

      let imgSrc = this.imgSrcs[idx],
        imgSize = new kakao.maps.Size(40, 40),
        markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      data.map((item) => {
        let itemPositon = new kakao.maps.LatLng(item.y, item.x);
        let mk = new kakao.maps.Marker({
          position: itemPositon, // 마커의 위치
          image: markerImage,
        });
        mk.setMap(this.map);
        this.sideMarkers[idx].push(mk);
      });
    },

    removeMarkerSide(idx) {
      console.log("지워라", idx);
      console.log(this.sideMarkers[idx]);
      for (let i = 0; i < this.sideMarkers[idx].length; i++) {
        this.sideMarkers[idx][i].setMap(null);
      }
      this.sideMarkers[idx] = [];
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

.selected {
  background-color: #ffc107 !important;
  color: whitesmoke;
}
.category {
  height: 40px;
}
</style>
