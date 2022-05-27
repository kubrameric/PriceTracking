import {signIn} from '../services/signinService'
import React, { useState, useEffect, useRef} from 'react';
import { useHistory } from "react-router-dom";
import LocalStorageUtil from "../utils/LocalStorageUtil";


export default function SignIn(props) {
  const password = useRef(null);
  const username = useRef(null);
  const history = useHistory();

  const signRequest = (username,password) => {
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

    fetch("http://localhost:8082/api/auth/signin", requestOptions)
        .then(response => response.json())
        .then(result => {
              LocalStorageUtil.setToken(result["token"]);
              LocalStorageUtil.setUsername(result["username"]);
              props.setIsLogged(true)
        })
        .catch(error => console.log('error', error));
  }

    return (
      <>
        <div className="min-h-full flex flex-col justify-center py-12 sm:px-6 lg:px-8">
          <div className="sm:mx-auto sm:w-full sm:max-w-md">
            <img
              className="mx-auto h-12 w-auto"
              src="https://tailwindui.com/img/logos/workflow-mark-indigo-600.svg"
              alt="Workflow"
            />
            <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">Sign in to your account</h2>
          </div>
  
          <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
            <div className="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
                <div>
                  <label htmlFor="email" className="block text-sm font-medium text-gray-700">
                    Username
                  </label>
                  <div className="mt-1">
                    <input
                      id="username"
                      name="username"
                      type="text"
                      autoComplete="username"
                      required
                      className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                      ref={username}

                    />
                  </div>
                </div>
  
                <div>
                  <label htmlFor="password" className="block text-sm font-medium text-gray-700">
                    Password
                  </label>
                  <div className="mt-1">
                    <input
                      id="password"
                      name="password"
                      type="password"
                      autoComplete="current-password"
                      required
                      className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                      ref={password}

                   />
                  </div>
                </div>
  
                <div className='mt-10'>
                  <button
                    onClick={()=> {
                      signRequest(username.current.value,password.current.value);     
                      window.localStorage.setItem("username", '')  
                      window.localStorage.setItem("token", '')  
                      history.push("/dashboard");
                      }
                    }
                    className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                  >
                    Sign in
                  </button>
                </div>
              
            </div>
          </div>
        </div>
      </>
    )
  }
  