import React, { useState, useEffect, useRef} from 'react';
import { useHistory } from "react-router-dom";
import {createProduct} from "./services/createProductService"
export default function SignUp() {
  const marketPlace = useRef(null);
  const url = useRef(null);
  const name = useRef(null);
  const productStatus = useRef(null);
  const price = useRef(null);
  const maxPriceToCondition = useRef(null);
  const minPriceToCondition = useRef(null);
  const history = useHistory();

    return (
      <>
        <div className="min-h-full flex flex-col justify-center py-12 sm:px-6 lg:px-8">
          <div className="sm:mx-auto sm:w-full sm:max-w-md">
            <img
              className="mx-auto h-12 w-auto"
              src="https://tailwindui.com/img/logos/workflow-mark-indigo-600.svg"
              alt="Workflow"
            />
            <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">Create a product</h2>
          </div>
  
          <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
            <div className="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
                <div>
                  <label htmlFor="name" className="block text-sm font-medium text-gray-700">
                    Product Name
                  </label>
                  <div className="mt-1">
                    <input
                      id="name"
                      name="name"
                      type="name"
                      autoComplete="name"
                      required
                      ref={name}
                      className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                    />
                  </div>
                </div>

                <div>
                  <label htmlFor="surname" className="block text-sm font-medium text-gray-700">
                    Product URL
                  </label>
                  <div className="mt-1">
                    <input
                      id="url"
                      name="url"
                      type="url"
                      autoComplete="url"
                      required
                      ref={url}
                      className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                    />
                  </div>
                </div>

                <div>
                  <label htmlFor="name" className="block text-sm font-medium text-gray-700">
                    Market Place
                  </label>
                  <div className="mt-1">
                    <input
                      id="marketPlace"
                      name="marketPlace"
                      type="marketPlace"
                      autoComplete="marketPlace"
                      required
                      ref={marketPlace}
                      className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                    />
                  </div>
                </div>

                <div>
                  <label htmlFor="phone" className="block text-sm font-medium text-gray-700">
                    Product Status
                  </label>
                  <div className="mt-1">
                    <input
                      id="productStatus"
                      name="productStatus"
                      type="productStatus"
                      autoComplete="productStatus"
                      required
                      ref={productStatus}
                      className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                    />
                  </div>
                </div>

                <div>
                  <label htmlFor="email" className="block text-sm font-medium text-gray-700">
                    Price
                  </label>
                  <div className="mt-1">
                    <input
                      id="price"
                      name="price"
                      type="price"
                      autoComplete="price"
                      required
                      ref={price}
                      className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                    />
                  </div>
                </div>
  
                <div>
                  <label htmlFor="password" className="block text-sm font-medium text-gray-700">
                   Max Price
                  </label>
                  <div className="mt-1">
                    <input
                      id="maxPriceToCondition"
                      name="maxPriceToCondition"
                      type="maxPriceToCondition"
                      autoComplete="maxPriceToCondition"
                      required
                      ref={maxPriceToCondition}
                      className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                    />
                  </div>
                </div>
                <div>
                  <label htmlFor="password" className="block text-sm font-medium text-gray-700">
                   Min Price
                  </label>
                  <div className="mt-1">
                    <input
                      id="minPriceToCondition"
                      name="minPriceToCondition"
                      type="minPriceToCondition"
                      autoComplete="minPriceToCondition"
                      required
                      ref={minPriceToCondition}
                      className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                    />
                  </div>
                </div>
                <div className='mt-10'>
                  <button
                    onClick={()=>{
                      createProduct(name.current.value,url.current.value,marketPlace.current.value,
                        productStatus.current.value,price.current.value,maxPriceToCondition.current.value,
                        minPriceToCondition.current.value);
                      history.push("/dashboard");
                      }                    
                    }
                      className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                  >
                    Create
                  </button>
                </div>
              
            </div>
          </div>
        </div>
      </>
    )
  }
  