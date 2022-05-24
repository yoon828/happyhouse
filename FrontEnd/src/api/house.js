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

function dealsByCode(params, success) {
  api
    .get(`/map/apt-deals?aptCode=${params.aptCode}`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}

//평점 최초 등록
function sendReviewApi(params, success) {
  api
    .post(`/map/review`, params)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}

//아파트와 사용자의 평점 정보 가져오기
async function getReviewApi(params, success) {
  await api
    .get(`/map/review?aptCode=${params.aptCode}&userid=${params.userid}`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}

//아파트 정보 가져오기
async function getHouseApi(params, success) {
  await api
    .get(`/map/aptinfo?aptCode=${params.aptCode}`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}

export {
  sidoList,
  gugunList,
  dongList,
  houseListByDong,
  houseListByName,
  dealsByCode,
  sendReviewApi,
  getReviewApi,
  getHouseApi,
};
