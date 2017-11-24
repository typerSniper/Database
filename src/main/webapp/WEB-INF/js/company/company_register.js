app.controller('companyRegisterController', function($scope, $http, $rootScope, $location) {
      $scope.pass='';
     $scope.input={name:'',
                  contact:'',
                  email:'',
                  representative:'',
                  password:'',
              };
    $scope.submit = function(){
      if($scope.pass == $scope.input.password){
        var url = '/company/register';
        var params = $rootScope.copyObject($scope.input);
        $http.post(url, params)
            .success(function(response) {
                if(response.success){
                    $location.path("/recruiter/login")
                }
            })
            .error(function(response) {
        });
      }
      else{
        alert("Passwords dont match");
      }
    };
});