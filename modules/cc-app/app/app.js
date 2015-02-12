(function(){
	'use strict';

	angular.module('cc-app', [ 'ngRoute', 'cc-app-main' ])
	  .config(function ($routeProvider) {
	    $routeProvider
	      .otherwise({
	        redirectTo: '/'
	      });
	  });
	  
})();