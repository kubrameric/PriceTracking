const LocalStorageService = (function () {
    const TOKEN = "token";
    const USERNAME = "username";

    function _setUsername(username) {
      localStorage.setItem(USERNAME, username);
    }
  
    function _getUsername() {
      return localStorage.getItem(USERNAME);
    }
  
    function _clearUsername() {
      localStorage.removeItem(USERNAME);
    }
  
    function _setToken(token) {
      localStorage.setItem(TOKEN, token);
    }
  
    function _getToken() {
      return localStorage.getItem(TOKEN);
    }
  
    function _clearToken() {
      localStorage.removeItem(TOKEN);
    }
  
    return {
      setToken: _setToken,
      getToken: _getToken,
      clearToken: _clearToken,
      setUsername: _setUsername,
      getUsername: _getUsername,
      clearUsername: _clearUsername
    };
  })();
  
  export default LocalStorageService;