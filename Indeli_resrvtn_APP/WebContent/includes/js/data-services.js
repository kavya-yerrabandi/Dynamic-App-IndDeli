var dataServices = angular.module('dataServices',[]);

dataServices.service('datReqService', ['$http',function($http){
	var self = this;
	this.postData = function(data){
		console.dir(data);
		return $http.post('api/view-reservations/add',data);
	};
}]);