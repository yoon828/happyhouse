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

function writeArticle(article, success) {
  api
    .post(`/qna`, JSON.stringify(article))
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
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

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
