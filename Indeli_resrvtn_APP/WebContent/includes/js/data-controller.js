(function(){
    var dataControllers = angular.module('dataControllers', [
        'dataServices'
    ]);

    dataControllers.controller('guestController', ['datReqService', function(datReqService){
        var self = this;
        self.reservation = {};
        self.reservationList = [];
        self.resDetails = {};
        self.loadDet = false;
        

        self.placeReservation = function(isValid){
            if(!isValid){
                alert("Please Correct the erors!");
            };

            if(isValid){
                datReqService.postData(self.reservation)
                    .success(function(data){
                        if(data.status === "success"){
                            console.dir(data);
                            self.resDetails = data.payload;
                            console.dir(self.resDetails);
                            self.reservation = {};
                            self.loadDet = true;
                        }
                        else if(data.status === "error"){
                            console.dir(data);
                            alert(data.message);
                        }
                    })
                    .error(function(error){
                        console.log(error);
                        alert("failed to contact the server!");
                    });
            }; 	
        };
        
        
        self.getAllReservations = function(){
        	datReqService.getAllData()
        		.success(function(data){
        			if(data.status === "success"){
        				console.dir(data);
        				self.reservationList = data.payload;
        			}
        			 else if(data.status === "error"){
                         console.dir(data);
                         alert(data.message);
                     }
        		})
        		.error(function(error){
                    console.log(error);
                    alert("failed to contact the server!");
                });
        };
        
       
    }]);
    
    dataControllers.controller('AuthController', ['authReqService', function(authReqService){
    	
    	var stat = this;
    	stat.auth = {};
    	stat.logDet = false;
    	stat.chkLogin = function(isValid){
    		 if(!isValid){
                 alert("Please Correct the erors!");
             };

             if(isValid){
    	
    	authReqService.authData(stat.auth)
         .success(function(data){
             if(data.status === "success"){
                 console.dir(data);
                 console.log(data.message);
                 //self.resDetails = data.payload;
                 stat.logDet = data.payload;
             }
             else if(data.status === "error"){
                 console.dir(data);
                 stat.auth = {};
                 alert(data.message);
             }
         })
         .error(function(error){
             console.log(error);
             alert("failed to contact the server!");
         });
    	
    	};
    	};
    	
    }]);
      
    })(); 

  