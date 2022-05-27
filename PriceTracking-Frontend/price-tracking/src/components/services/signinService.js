import LocalStorageUtil from "../utils/LocalStorageUtil";

export function signIn(username,password){
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    //myHeaders.append("Access-Control-Allow-Origin", "*")
    //myHeaders.append("Access-Control-Allow-Methods", "DELETE, POST, GET, OPTIONS")
    //myHeaders.append("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With")
    var raw = JSON.stringify({
        "username": username,
        "password": password
    });

    var requestOptions = {

    method: 'POST',
    headers: myHeaders,
    body: raw,
    redirect: 'follow'
    };

   
    return fetch("http://localhost:8080/api/auth/signin", requestOptions)
        .then(response => response.json())
        .then(result => {
            LocalStorageUtil.setToken(result["token"]);
            LocalStorageUtil.setUsername(result["username"]);
        })
        .catch(error => console.log('error', error));
    
       
}