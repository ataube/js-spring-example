(function(){
  'use strict';


  angular.module('cc-app-main',['ngRoute'])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/', {
          templateUrl: 'main/main.html',
          controller: 'MainCtrl'
        });
    })
    .controller('MainCtrl', function ($scope, $http) {
		  $http.get('/api/things').then(function(resp) {
			  $scope.awesomeThings = resp.data;
		  });

    });

})();