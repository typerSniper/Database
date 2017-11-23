app.controller('StudentFeeController', function($http, $scope, $rootScope, $route){
    $scope.ic = {
        name: '',
        number: ''
    };
    $scope.submit = function(){
        var url = '/student/fee_payment';
        $http.get(url)
            .success(function(response) {
                if(response.success){
                    $rootScope.stage = response.stage; //stage -> 3
                    $scope.ic.name = response.name;
                    $scope.ic.number = response.number;
                    $route.reload();
                }
            })
            .error(function(response) {
            });
    };

    $scope.getIC = function(){
        var url = "/student/allocated_ic";
        $http.get(url)
            .success(function(response){
                if(response.status){
                    $scope.ic.name = response.coordinatorName;
                    $scope.ic.number = response.phoneNumber;
                }
            });
    };
});