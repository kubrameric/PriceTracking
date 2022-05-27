import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import { useState, useEffect } from 'react';
import Header from './components/Header'
import SignUp from './components/authentication/SignUp'
import SignIn from './components/authentication/SignIn'
import Dashboard from './components/Dashboard';
import Product from './components/Product'
import Content from "./components/Content.jsx"
function App() {

  const [isLogged, setIsLogged] = useState(false)

  useEffect(() => {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");  
    var requestOptions = {
      method: 'GET',
      headers: myHeaders,
      redirect: 'follow'
    };

    fetch("http://localhost:8082/api/auth/checkJwt?token=" + window.localStorage.getItem('token'), requestOptions)
      .then(response => response.text())
      .then(result => {
  
        if(result == "true"){
          setIsLogged(true)
        }else{
          setIsLogged(false)
          window.localStorage.setItem('token', null)
          window.localStorage.setItem('username', null)
        }
      })
      .catch(error => console.log('error', error));
  },[])

  return (
    <>
    <Router>
      <Header  isLogged={isLogged} setIsLogged={setIsLogged}/>
      <Switch>
        <Route exact path="/">
          <Content />
        </Route>
        <Route exact path="/dashboard" > <Dashboard isLogged={isLogged} setIsLogged={setIsLogged}/> </Route>
        <Route exact path="/signIn" > <SignIn isLogged={isLogged} setIsLogged={setIsLogged}/> </Route>
        <Route exact path="/signUp" > <SignUp/> </Route>
        <Route exact path="/product"> <Product/> </Route>
        </Switch>
    </Router>
    </>
  )

}
export default App;
