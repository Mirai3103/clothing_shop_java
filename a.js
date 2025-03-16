import { readFileSync, writeFileSync } from "fs";
import axios from "axios";
import * as cheerio from "cheerio";

const filePath = "data.json";

// Đọc file JSON
let data = JSON.parse(readFileSync(filePath, "utf8"));
// {
//     "product_id": 1,
//     "name": "Daily Short Excool V2",
//     "display_image": "https://media.coolmate.me/cdn-cgi/image/quality=80,format=auto/uploads/August2022/122_0.jpg"
//   },
// const searchEndpoint ="https://www.coolmate.me/load-product?home_search=1&limit=4&page=1&sort=created_at&type=spotlight-header&keyword=Daily%20Short%20Excool%20V2&_=1742102151526"

data = data.map((item) => {
  return {
    ...item,
    searchEndpoint: `https://www.coolmate.me/load-product?home_search=1&limit=4&page=1&sort=created_at&type=spotlight-header&keyword=${item.name}&_=1742102151526`,
  };
});
const processedData = [];
for await (const item of data) {
  const response = await axios.get(item.searchEndpoint);
  const $ = cheerio.load(response.data.html);
  // tìm thẻ img đầu tiên
  const img = $("img").first();
  const src = img.attr("src");
  processedData.push({
    ...item,
    display_image: src,
  });
}
// tạo câu sql update
const sql = processedData
  .map((item) => {
    return `UPDATE product SET display_image = '${item.display_image}' WHERE product_id = ${item.product_id};`;
  })
  .join("\n");

writeFileSync("update.sql", sql);
