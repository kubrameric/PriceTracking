import LocalStorageUtil from "../utils/LocalStorageUtil";

export function createProduct(name,url,marketPlace,status,price,maxPrice,minPrice){
    var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({
  "username": LocalStorageUtil.getUsername(),
  "name": name,
  "url": url,
  "marketPlace": marketPlace,
  "productStatus": status,
  "price": price,
  "maxPriceToCondition": maxPrice,
  "minPriceToCondition": minPrice
});

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw,
  redirect: 'follow'
};

fetch("http://localhost:8080/api/product/createProduct", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result))
  .catch(error => console.log('error', error));
   
}