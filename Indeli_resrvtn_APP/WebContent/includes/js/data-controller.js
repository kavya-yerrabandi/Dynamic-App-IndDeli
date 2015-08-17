(function(){
    var dataControllers = angular.module('dataControllers', [
        'dataServices'
    ]);

    dataControllers.controller('guestController', ['datReqService', function(datReqService){
        var self = this;
        self.tabNum = 1;
        self.reservation = {};
        self.reservationList = [];
        self.Id = false;
        self.pop_up2 = false;

        self.setTab = function(tabNumber){
            self.tabNum = tabNumber;
        };

        self.isSet = function(tabSelected){
            return self.tabNum === tabSelected;
        };

        self.placeReservation = function(isValid){
            if(!isValid){
                alert("Please Correct the erors!");
            };

            if(isValid){
                datReqService.postData(self.reservation)
                    .success(function(data){
                        if(data.status === "success"){
                            console.log(data);
                            self.reservation = {};
                            self.pop_up1 = true;
                        }
                        else if(data.status === "error"){
                            console.log(data);
                            alert(data.message);
                        }
                    })
                    .error(function(error){
                        console.log(error);
                        alert("There is an error contacting the server!");
                    });

            };

        };
        
    }]);
      
    })(); 

  