import {Link} from "react-router-dom"
import { useHistory } from "react-router-dom";
import LocalStorageUtil from "./utils/LocalStorageUtil";

export default function Header(props) {
  const history = useHistory();
    return (
      <header className="bg-indigo-600">
        <nav className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8" aria-label="Top">
          <div className="w-full py-6 flex items-center justify-between border-b border-indigo-500 lg:border-none">
            <div className="flex items-center">
              <Link to="/">
                <span className="sr-only">Workflow</span>
                <img
                  className="h-10 w-auto"
                  src="https://tailwindui.com/img/logos/workflow-mark.svg?color=white"
                  alt=""
                />
              </Link>
              
             {props.isLogged === true ?  <div className="hidden ml-10 space-x-8 lg:block">
                <Link key='Dashboard' 
                  to='/dashboard' 
                  className="text-base font-medium text-white hover:text-indigo-50"
                >
                  Dashboard
                </Link>
              </div> : ''}
            </div>
            {props.isLogged === true  ? <div className="ml-10 space-x-4">
              <Link
                onClick={() =>{
                  props.setIsLogged(false);
                  LocalStorageUtil.clearToken();
                  LocalStorageUtil.clearUsername();
                  history.push("/");
                }}
                className="inline-block bg-indigo-500 py-2 px-4 border border-transparent rounded-md text-base font-medium text-white hover:bg-opacity-75"
              >
                Logout
              </Link>
             
            </div> : <div className="ml-10 space-x-4">
              <Link
                to="/signIn"
                className="inline-block bg-indigo-500 py-2 px-4 border border-transparent rounded-md text-base font-medium text-white hover:bg-opacity-75"
              >
                Sign in
              </Link>
              <Link
                to="/signUp"
                className="inline-block bg-white py-2 px-4 border border-transparent rounded-md text-base font-medium text-indigo-600 hover:bg-indigo-50"
              >
                Sign up
              </Link>
            </div>}
          </div>
          <div className="py-4 flex flex-wrap justify-center space-x-6 lg:hidden">
            <a key='Dashboard' href='/dashboard' className="text-base font-medium text-white hover:text-indigo-50">
             Dashboard
            </a> 
          </div>
        </nav>
      </header>
    )
  }
  