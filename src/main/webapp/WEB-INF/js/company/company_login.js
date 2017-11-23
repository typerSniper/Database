app.controller('companyLoginController', function($scope, $http, $rootScope, $location) {
    $scope.login = function() {
        var username = 'username=' + $scope.username;
        var password = 'password=' + $scope.password;
        var url = '/app/login?' +username + '&' +password + "&type=recruiter";
        $http.post(url)
            .success(function(response) {
                if(response.authenticated) {
                    $rootScope.loggedIn = true;
                    $location.path('/recruiter/home');
                }
                else
                    $scope.errorMessage = "Invalid username or password.";
            })
            .error(function(response) {
                $scope.errorMessage = "Cannot Connect";
            });
    };

});