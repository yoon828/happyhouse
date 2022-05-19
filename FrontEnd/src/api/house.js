import { apiInstance } from "./index.js";

const api = apiInstance();

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
    .get(`/map/aptName?aptName=${params.aptName}`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}

export { sidoList, gugunList, dongList, houseListByDong, houseListByName };
