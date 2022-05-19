import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();

function sidoList(success, fail) {
  api
    .get(`/map/sido`)
    .then(success)
    .catch(fail);
}

function gugunList(params, success, fail) {
  api

    .get(`/map/gugun?sido=${params.sidoCode}`)
    .then(success)
    .catch(fail);
}
function dongList(params, success, fail) {
  api
    .get(`/map/dong?gugun=${params.sidoCode}`)
    .then(success)
    .catch(fail);
}

function houseListByDong(params, success, fail) {
  house
    .get(`/map/apt?dong=${params.dongCode}`)
    .then(success)
    .catch(fail);
}
function houseListByName(params, success, fail) {
  house
    .get(`/map/aptName?aptName=${params.aptName}`)
    .then(success)
    .catch(fail);
}

export { sidoList, gugunList, dongList, houseListByDong, houseListByName };
