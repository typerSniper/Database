app.controller('StudentFeeController', function($http, $scope, $rootScope, $route){
    $scope.submit = function(){
        var url = '/student/fee_payment';
        $http.get(url)
            .success(function(response) {
                // console.log(response);
                if(response.success){
                    $rootScope.stage = response.stage; //stage -> 3
                    // $rootScope.stage = 2; //change this
                    $route.reload();
                }
            })
            .error(function(response) {
                // $scope.errorMessage = "Cannot Connect";
            });
    }
});