(function(){
  'use strict';


  angular.module('cc-app-main',['ngRoute', 'cc-app-config', 'cc-app-components'])
    .config(function ($routeProvider, appConfig) {
      $routeProvider
        .when('/', {
          templateUrl: appConfig.templateRootPath + 'main/main.html',
          controller: 'MainCtrl'
        });
    })
    .controller('MainCtrl', function ($scope, thingsService) {
		  thingsService.getAwesomeThings().then(function(data) {
			  $scope.awesomeThings = data;
		  });

    });

})();