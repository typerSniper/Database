app.controller('ICHomeController', function($scope, $http, $rootScope, $location) {
    $scope.getResume = function(student){
        var url = "/ic/get_resume";
        var params = {username: student};
        $http.post(url, params)
            .success(function(response){
                console.log(response.resume[0].resume);
            })
            .error(function(response){
                console.log("error");
                console.log(response);
            })
    }
});