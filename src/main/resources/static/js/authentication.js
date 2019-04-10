/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = "./src/authentication.js");
/******/ })
/************************************************************************/
/******/ ({

/***/ "./src/authentication.js":
/*!*******************************!*\
  !*** ./src/authentication.js ***!
  \*******************************/
/*! no static exports found */
/***/ (function(module, exports) {

eval("var password = document.getElementById('password');\nvar passwordConfirmed = document.getElementById('confirmedPassword');\nvar name = document.getElementById('name');\nvar surname = document.getElementById('surname');\nvar username = document.getElementById('username');\nvar flagPassword, flagName, flagSurname, flagUsername, flagConfirmedPassword;\nvar button = document.createElement(\"INPUT\");\nbutton.setAttribute(\"type\", \"submit\");\nbutton.setAttribute(\"value\", \"Register\");\nbutton.setAttribute(\"class\", \"btn float-left login_btn\");\nbutton.setAttribute(\"id\", \"confirm\");\nvar link =\n/*[[@{/}]]*/\n'';\nbutton.setAttribute(\"href\", link);\n\npassword.onkeyup = function () {\n  if (password.value.length >= 8 && password.value.length <= 15) {\n    password.style.borderColor = \"green\";\n    flagPassword = true;\n  } else {\n    password.style.borderColor = \"red\";\n    flagPassword = false;\n    if (document.getElementById('validationContainer').contains(button) !== null) document.getElementById('validationContainer').removeChild(button);\n  }\n\n  checkFlags();\n};\n\npasswordConfirmed.onkeyup = function () {\n  if (passwordConfirmed.value.length >= 8 && passwordConfirmed.value.length <= 15 && passwordConfirmed.value === password.value) {\n    passwordConfirmed.style.borderColor = \"green\";\n    flagConfirmedPassword = true;\n  } else {\n    passwordConfirmed.style.borderColor = \"red\";\n    flagConfirmedPassword = false;\n    if (document.getElementById('validationContainer').contains(button) !== null) document.getElementById('validationContainer').removeChild(button);\n  }\n\n  checkFlags();\n};\n\nname.onkeyup = function () {\n  //il nome può contenere sia maiuscole che minuscole\n  var letters = /^[A-Za-z]+$/;\n\n  if (name.value.match(letters)) {\n    name.style.borderColor = \"green\";\n    flagName = true;\n  } else {\n    name.style.borderColor = \"red\";\n    flagName = false;\n    if (document.getElementById('validationContainer').contains(button) !== null) document.getElementById('validationContainer').removeChild(button);\n  }\n\n  checkFlags();\n};\n\nsurname.onkeyup = function () {\n  //il cognnome può contenere sia maiuscole che minuscole\n  var letters = /^[A-Za-z]+$/;\n\n  if (surname.value.match(letters)) {\n    surname.style.borderColor = \"green\";\n    flagSurname = true;\n  } else {\n    surname.style.borderColor = \"red\";\n    flagSurname = false;\n    if (document.getElementById('validationContainer').contains(button) !== null) document.getElementById('validationContainer').removeChild(button);\n  }\n\n  checkFlags();\n};\n\nusername.onkeyup = function () {\n  //l'username può contenere lettere, numeri e _\n  var letters = /([A-Za-z0-9\\_]+)/;\n\n  if (username.value.match(letters)) {\n    username.style.borderColor = \"green\";\n    flagUsername = true;\n  } else {\n    username.style.borderColor = \"red\";\n    flagUsername = false;\n    if (document.getElementById('validationContainer').contains(button) !== null) document.getElementById('validationContainer').removeChild(button);\n  }\n\n  checkFlags();\n};\n\nfunction checkFlags() {\n  //se tutti i flag sono true (i campi sono stati collegati correttamente) allora viene creato il bottone\n  if (flagName === true && flagSurname === true && flagUsername === true && flagPassword === true && flagConfirmedPassword === true && flagPassword.value === flagConfirmedPassword.value) {\n    document.getElementById('validationContainer').appendChild(button);\n  }\n}\n\n;\n\n//# sourceURL=webpack:///./src/authentication.js?");

/***/ })

/******/ });