(function(){
	'use strict';


	angular.module('cc-app-components',[])
		.service('thingsService', function ($http) {
			return {
				getAwesomeThings : function() {
					return $http.get('/api/things').then(function(resp) {
						return resp.data;
					});
				}
			};

		});

})();