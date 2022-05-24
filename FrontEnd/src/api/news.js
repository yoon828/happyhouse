import { apiInstance } from "./index.js";

const api = apiInstance();

async function getNewsList(success) {
  api
    .get(`/naverapi/news`)
    .then(success)
    .catch((err) => {
      console.log(err);
    });
}
export { getNewsList };
