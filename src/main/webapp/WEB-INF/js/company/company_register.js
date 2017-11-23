app.controller('companyRegisterController', function($scope, $http, $rootScope, $location) {
      $scope.pass='';
     $scope.input={company_name:'',
                  POC:'',
                  POC_contact:'',
                  POC_email:'',
                  passw:'',
              };
    $scope.submit = function(){
        // var url = '/company/save_details';
        // var params = $rootScope.copyObject($scope.input);
        // $http.post(url, params)
        //     .success(function(response) {
        //         if(response.success){
        //             console.log(response);
        //             $rootScope.stage = response.stage;
        //             $route.reload();
        //         }
        //     })
        //     .error(function(response) {
        // });
        console.log($scope.input);
    };

});