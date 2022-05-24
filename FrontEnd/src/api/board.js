import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(success) {
  api
    .get(`/qna`)
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}
function listComment(articleno, success) {
  api
    .get(`/qna/comment/${articleno}`)
    .then(success)
    .catch((err) => {
      console.log(err);
    });
}
function writeArticle(article, success) {
  api
    .post(`/qna`, JSON.stringify(article))
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}

function writeComment(comment, success) {
  api
    .post(`/qna/comment`, JSON.stringify(comment))
    .then(success)
    .catch((err) => {
      console.log(err);
    });
}

function getArticle(articleno, success) {
  api
    .get(`/qna/${articleno}`)
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}

function modifyArticle(article, success) {
  api
    .put(`/qna/${article.articleno}`, JSON.stringify(article))
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}

function deleteArticle(articleno, success) {
  api
    .delete(`/qna/${articleno}`)
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}
function deleteComment(commentno, success) {
  api
    .delete(`/qna/comment/${commentno}`)
    .then(success)
    .catch((err) => {
      console.log(err);
    });
}
function getArticleById(userid, success) {
  api
    .get(`/qna/searchid/${userid}`)
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}

function getArticleBySubject(subject, success) {
  api
    .get(`/qna/searchtitle/${subject}`)
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}
function hitAdd(articleno, success, fail) {
  api
    .put(`/qna/hitadd/${articleno}`)
    .then(success)
    .catch(fail);
}

export {
  listArticle,
  writeArticle,
  getArticle,
  modifyArticle,
  deleteArticle,
  getArticleById,
  getArticleBySubject,
  hitAdd,
  writeComment,
  listComment,
  deleteComment,
};
