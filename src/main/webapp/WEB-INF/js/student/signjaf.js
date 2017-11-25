app.controller('StudentSignJafController', function($http, $scope, $interval,$location,$rootScope){
    $scope.resume_type = "";

    $scope.signjaf = function() {
        
         
            var url = "/To Change";
            var params = $rootScope.copyObject({jafID: $rootScope.selectedJaf,resume: $scope.resume_type});
            $http.post(url, params)
                    .success(function(response) {
                    if(response.success){
                        console.log("Done");
                    }
                })
                .error(function(response) {
                    // $scope.errorMessage = "Cannot Connect";
                });
       
    };

    $scope.unsignjaf = function()
    {
        var url = "//To Change";
            var params = $rootScope.copyObject({jafID:$rootScope.selectedJaf});
            $http.post(url, params)
                    .success(function(response) {
                    if(response.success){
                        console.log("Done");
                    }
                })
                .error(function(response) {
                    // $scope.errorMessage = "Cannot Connect";
                });

    }  
   
});
