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
      alert("문제가 발생했습니다. ");
      console.log(error);
    });
}
function getAddressName(params, success) {
  api
    .get(`map/address?dongcode=${params.dongcode}`)
    .then(success)
    .catch((error) => {
      console.log(error);
    });
}
function getFoodList(search, success) {
  api
    .get(`naverapi/food/${search}`)
    .then(success)
    .catch((err) => {
      console.log(err);
    });
}
export { sidoList, gugunList, dongList, getAddressName, getFoodList };
