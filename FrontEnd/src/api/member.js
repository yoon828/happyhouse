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

//회원 리스트 가져오기
async function listMember(success, fail) {
  api
    .get(`/admin/list`)
    .then(success)
    .catch((fail) => {
      alert("문제가 발생했습니다");
      console.log(fail);
    });
}
// 회원 상세 정보 가져오기
async function getMember(userid, success, fail) {
  api
    .get(`/admin/${userid}`)
    .then(success)
    .catch(fail);
}
//관리자 등록
async function registAdmin(user, success, fail) {
  api
    .post("/admin/regist", JSON.stringify(user))
    .then(success)
    .catch(fail);
}
export {
  login,
  findById,
  registMember,
  idFindMember,
  pwFindMember,
  updateMember,
  deleteMember,
  listMember,
  getMember,
  registAdmin,
};
