app.controller('StudentFeeController', function($http, $scope, $rootScope, $route){
    $scope.ic = {
        name: '',
        number: ''
    };
    $scope.submit = function(){
        var url = '/student/fee_payment';
        $http.get(url)
            .success(function(response) {
                // console.log(response);
                if(response.success){
                    $rootScope.stage = response.stage; //stage -> 3
                    // $rootScope.stage = 2; //change this
                    $scope.ic.name = response.name;
                    $scope.ic.number = response.number;
                    $route.reload();
                }
            })
            .error(function(response) {
                // $scope.errorMessage = "Cannot Connect";
            });
    };

    $scope.getIC = function(){
        // console.log("pop");
        var url = "/student/get_ic";
        $http.get(url)
            .success(function(response){
                $scope.ic.name = response.name;
                $scope.ic.number = response.number;
            });
    };
});