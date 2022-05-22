import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success) {
  await api
    .post(`/userapi/login`, JSON.stringify(user))
    .then(success)
    .catch((err) => {
      console.log(err);
      alert("문제가 발생했습니다.");
    });
}

async function findById(userid, success) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api
    .get(`/userapi/myInfo/${userid}`)
    .then(success)
    .catch((err) => {
      console.log(err);
      alert("문제가 발생했습니다.");
    });
}

// function logout(success)
//회원 가입
async function registMember(user, success) {
  api
    .post("/userapi/regist", JSON.stringify(user))
    .then(success)
    .catch((err) => {
      console.log(err);
      alert("문제가 발생했습니다.");
    });
}
//아이디 찾기
async function idFindMember(user, success) {
  api
    .post("/userapi/idFind", JSON.stringify(user))
    .then(success)
    .catch((err) => {
      console.log(err);
      alert("문제가 발생했습니다.");
    });
}
// 비밀 번호 찾기
async function pwFindMember(user, success) {
  api
    .post("/userapi/pwFind", JSON.stringify(user))
    .then(success)
    .catch((err) => {
      console.log(err);
      alert("문제가 발생했습니다.");
    });
}
//회원 수정
async function updateMember(user, success) {
  api
    .put("/userapi/update", JSON.stringify(user))
    .then(success)
    .catch((err) => {
      console.log(err);
      alert("문제가 발생했습니다.");
    });
}
//회원 삭제
async function deleteMember(userid, success) {
  api
    .delete(`/userapi/delete/${userid}`)
    .then(success)
    .catch((err) => {
      console.log(err);
      alert("문제가 발생했습니다.");
    });
}

export {
  login,
  findById,
  registMember,
  idFindMember,
  pwFindMember,
  updateMember,
  deleteMember,
};
