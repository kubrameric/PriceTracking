export function signUp(username,password,name,surname,email,phoneNumber){
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    var raw = JSON.stringify({
        "username": username,
        "password": password,
        "name": name,
        "surname": surname,
        "email": email,
        "phoneNumber": phoneNumber,
    });

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
      };
      
    return fetch("http://localhost:8080/api/auth/signup", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}