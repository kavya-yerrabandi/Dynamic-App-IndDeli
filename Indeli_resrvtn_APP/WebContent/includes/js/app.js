
(function(){
	'use strict';

var app = angular.module('indApp',['ngRoute','dataControllers']);
app.config(['$routeProvider',
            function ($routeProvider) {
            $routeProvider
            .when('/home', {
            		templateUrl: 'partials/home-page.html',
            		
                    })
           .when('/guest-reservation',{
			templateUrl: 'partials/guest-reservation.html',
				controller: 'guestController',
				controllerAs: 'guestCtrl'
		})      
		.when('/staff-login',{
			templateUrl: 'partials/staff-login.html'
		})
		.when('/get-reservations',{
			templateUrl: 'partials/all-reservations.html'
		})
		.otherwise({
			redirectTo: '/home'
		});
   
		
}]);


})();
