app.controller('ICLoginController', function($scope, $http, $rootScope, $location) {
    $scope.login = function() {
        var url = '/app/login';
        var params = {username :$scope.username, password : $scope.password, type : "IC"}
        $http.post(url, params)
            .success(function(response) {
                if(response.authenticated) {
                    $rootScope.stage = response.stage;
                    $rootScope.username = $scope.username;
                    $location.path('/IC/home');

                    // $cookieStore.put("loggedIn", true);
                }
                else
                     $scope.errorMessage = "Invalid username or password.";
            })
            .error(function(response) {
                $scope.errorMessage = "Cannot Connect";
            });
    };

});