app.controller('companyLoginController', function($scope, $http, $rootScope, $location) {
     $scope.input={company:'',
              };
    $scope.submit = function(){
        var url = '/company/save_details';
        var params = $rootScope.copyObject($scope.input);
        $http.post(url, params)
            .success(function(response) {
                if(response.success){
                    console.log(response);
                    $rootScope.stage = response.stage;
                    $route.reload();
                }
            })
            .error(function(response) {
        });
    };

});