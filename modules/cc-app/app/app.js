(function(){
	'use strict';

	angular.module('cc-app', [ 'ngRoute','cc-app-main','templates' ])
	  .config(function ($routeProvider) {
	    $routeProvider
	      .otherwise({
	        redirectTo: '/'
	      });
	  });
	  
})();