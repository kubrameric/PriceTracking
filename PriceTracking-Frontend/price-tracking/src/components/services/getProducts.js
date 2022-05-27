import LocalStorageUtil from "../utils/LocalStorageUtil";

export function getProducts(){
  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  
  var raw = JSON.stringify({
    "username": LocalStorageUtil.getUsername()
  });
  
  var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    //body: raw,
    redirect: 'follow'
  };
  
  return fetch("http://localhost:8080/api/product/getProductByStatus?productStatus=ACTIVE&username="+window.localStorage.getItem('username'), requestOptions)
    .then(response => response.json()) 
    .then(result => result)
    .catch(error => console.log('error', error));
   
}