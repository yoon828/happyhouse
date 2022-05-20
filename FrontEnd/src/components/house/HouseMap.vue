/* eslint-disable prettier/prettier */
<template>
  <b-container>
    <div id="map" class="map" style="height:600px">지도</div>
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
      geocoder: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  methods: {
    ...mapActions(houseStore, ["clearHouses"]),
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
      this.ps = new kakao.maps.services.Places();
      this.infoWindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      this.geocoder = new kakao.maps.services.Geocoder();
    },
    displayMarkers() {
      console.log("마커 그리기");
      let fragment = document.createDocumentFragment();
      let bounds = new kakao.maps.LatLngBounds();
      var listStr = "";
      removeMarker();

      for (let i = 0; i < this.houses.length; i++) {
        let placePosition = new kakao.maps.LatLng(
          this.houses[i].lat,
          // eslint-disable-next-line prettier/prettier
          this.houses[i].lng
        );
        let marker = addMarker(placePosition, i);
        let itemEl = getListItem(i, this.houses[i]); // 검색 결과 항목 Element를 생성합니다
        //지도 범위를 재설정하기 위해 LatLngBounds에 좌표를 추가
        bounds.extend(placePosition);
        //마커와 검색 결과 항목에 mouseover 했을때 해당 장소에 인포윈도우에 장소명 표시
        (function(marker, name, code, place) {
          //클릭하면 InfoWindow 보이기
          kakao.maps.event.addListener(marker, "click", function() {
            displayInfowindow(marker, name, place);
          });

          kakao.maps.event.addListener(this.map, "click", function() {
            console.log(this.customOverlay);
            this.customOverlay.setMap(null);
          });

          itemEl.onmouseover = function() {
            displayInfowindow(marker, name);
          };

          itemEl.onmouseout = function() {
            this.customOverlay.setMap(null);
          };
        })(
          marker,
          this.houses[i].aptName,
          this.houses[i].aptCode,
          // eslint-disable-next-line prettier/prettier
          this.houses[i]
        );
        fragment.appendChild(itemEl);
      }
      //검색된 장소 위치를 기준으로 지도 범위 재설정
      this.map.setBounds(bounds);
    },
    removeMarker() {
      for (let i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    displayInfowindow(marker, name, place) {
      let content = `
		<div class="overlaybox">
			<div class="boxtitle">${name}</div>
			<div class="first"><img src="${root}/img/apt.png" style="width:247px; height:136px;" alt=""></div>
			<ul>
				<li class="up">
					<span class="title">건축년도</span>
					<span class="count">${place.buildYear}</span>
				</li>
				<li>
					<span class="title">주소</span>
					<span class="count">${place.sidoName} ${place.gugunName} ${place.dongName} ${place.jibun}</span>
				</li>
				<li>
					<span class="title">최신거래금액</span>
					<span class="count">${place.recentPrice}</span>
				</li>
				<li>
					<span class="last" id="recenthistor" data-toggle="modal" data-target="#myModal">아파트정보 update</span>
				</li>
			</ul>
		</div>
	`;
      let position = new kakao.maps.LatLng(
        marker.getPosition().getLat() + 0.00033,
        // eslint-disable-next-line prettier/prettier
        marker.getPosition().getLng() - 0.00003
      );
      this.customOverlay = new kakao.maps.CustomOverlay({
        position: position,
        content: content,
        xAnchor: 0.3,
        yAnchor: 0.91,
      });
      this.customOverlay.setMap(this.map);
    },

    addMarker(position, idx, title) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
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
    removeAllChildNods(el) {
      while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
      }
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
