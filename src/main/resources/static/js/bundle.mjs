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
/******/ 	return __webpack_require__(__webpack_require__.s = "./src/bundle.js");
/******/ })
/************************************************************************/
/******/ ({

/***/ "./src/bundle.js":
/*!***********************!*\
  !*** ./src/bundle.js ***!
  \***********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _searchBlogPosts_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./searchBlogPosts.js */ "./src/searchBlogPosts.js");
/* harmony import */ var _searchBlogPosts_js__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_searchBlogPosts_js__WEBPACK_IMPORTED_MODULE_0__);


/***/ }),

/***/ "./src/searchBlogPosts.js":
/*!********************************!*\
  !*** ./src/searchBlogPosts.js ***!
  \********************************/
/*! no static exports found */
/***/ (function(module, exports) {

document.getElementById("match").addEventListener("input", function () {
  let search = $('#match').val();

  if (search === null) {
    return;
  }

  let imageURL = "imageUrl";

  if (search.length > 2) {
    $.ajax({
      dataType: 'json',
      type: "GET",
      url: 'blog/search',
      data: {
        "q": search
      }
    }).done(function (data) {
      $('#container').empty();
      $(data).each(function (index, element) {
        if (element.category.name === "Sport") {
          imageURL = "../images/noah-silliman-246027-unsplash.jpg";
        } else if (element.category.name === "Cultura") {
          imageURL = "../images/annie-spratt-749650-unsplash.jpg";
        } else if (element.category.name === "Scienza") {
          imageURL = "../images/elevate-755051-unsplash.jpg";
        }

        $('#container').append('<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12" data-aos="fade-right" >' + '<article class="card text-center" >' + '<div>' + '<img class="card-img-top" src=' + imageURL + ' alt="" width="100%"/>' + '</div>' + '<div class="card-block">' + '<h4 class="card-title" >' + element.title + '</h4>' + '<p class="card-text"  >' + element.text + '</p>' + '<a class="btn btn-default" href="../blog/' + element.id + '">Read More</a>' + '</div>' + '</article>' + '</div>');
      });
    });
  }
});
document.getElementById("submitSearch").addEventListener('click', function (e) {
  e.preventDefault();
  let search = $('#match').val();

  if (search === null) {
    return;
  }

  let imageURL = "imageUrl";
  $.ajax({
    dataType: 'json',
    type: "GET",
    url: 'blog/search',
    data: {
      "q": search
    }
  }).done(function (data) {
    $('#container').empty();
    $(data).each(function (index, element) {
      if (element.category.name === "Sport") {
        imageURL = "../images/noah-silliman-246027-unsplash.jpg";
      } else if (element.category.name === "Cultura") {
        imageURL = "../images/annie-spratt-749650-unsplash.jpg";
      } else if (element.category.name === "Scienza") {
        imageURL = "../images/elevate-755051-unsplash.jpg";
      }

      $('#container').append('<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12" data-aos="fade-right" >' + '<article class="card text-center" >' + '<div>' + '<img class="card-img-top" src=' + imageURL + ' alt="" width="100%"/>' + '</div>' + '<div class="card-block">' + '<h4 class="card-title" >' + element.title + '</h4>' + '<p class="card-text"  >' + element.text + '</p>' + '<a class="btn btn-default" href="../blog/' + element.id + '">Read More</a>' + '</div>' + '</article>' + '</div>');
    });
  });
});

/***/ })

/******/ });
//# sourceMappingURL=bundle.mjs.map