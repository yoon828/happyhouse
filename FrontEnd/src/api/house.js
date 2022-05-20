import { apiInstance } from "./index.js";

const api = apiInstance();
// const house = houseInstance();

function sidoList(success) {
  api
    .get(`/map/sido`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}

function gugunList(params, success) {
  api

    .get(`/map/gugun?sido=${params.sidoCode}`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}
function dongList(params, success) {
  api
    .get(`/map/dong?gugun=${params.sidoCode}`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}

function houseListByDong(params, success) {
  api
    .get(`/map/apt?dong=${params.dongCode}`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}
function houseListByName(params, success) {
  api
    .get(`/map/apt-name?aptName=${params.aptName}`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}

function dealByCode(params, success) {
  api
    .get(`/map/apt-deals?aptCode=${params.aptCode}`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}

// //공동 데이터 아파트 실거래 조회 api사용
// function houseList(params, success) {
//   house
//     .get(``, { params: params })
//     .then(success)
//     .catch((error) => {
//       alert("문제가 발생했습니다.");
//       console.log(error);
//     });
// }

export {
  sidoList,
  gugunList,
  dongList,
  houseListByDong,
  houseListByName,
  dealByCode,
  // houseList,
};
