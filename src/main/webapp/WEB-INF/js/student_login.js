app.controller('StudentLoginController', function($scope, $http, $rootScope, $location) {
    $scope.login = function() {
        var username = 'username=' + $scope.username;
        var password = 'password=' + $scope.password;
        var url = '/app/login?' +username + '&' +password + "&type=student";
        $http.post(url)
            .success(function(response) {
            console.log(response);
                if(response.authenticated) {
                    $location.path('/student/home');
                }
                else
                     $scope.errorMessage = "Invalid username or password.";
            })
            .error(function(response) {
                $scope.errorMessage = "Cannot Connect";
            });
    };

});
