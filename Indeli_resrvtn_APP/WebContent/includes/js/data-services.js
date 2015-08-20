(function(){

var dataServices = angular.module('dataServices',[]);

dataServices.service('datReqService', ['$http',function($http){
	var self = this;
	self.postData = function(data){
		console.dir(data);
		return $http.post('api/view-reservations/add',data);
	};
	self.getAllData = function(){
		return $http.get('api/view-reservations/all');
	};
}]);

dataServices.service('authReqService', ['$http',function($http){
	var self = this;
	self.authData = function(data){
		console.dir(data);
		return $http.post('api/staff/login',data);
	};
	
}]);

})();
