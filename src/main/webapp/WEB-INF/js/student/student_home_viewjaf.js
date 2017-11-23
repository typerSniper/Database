app.controller('StudentViewJafController', function($http, $scope, $interval,$location,$rootScope){
    $scope.jaf = {};

    $scope.getJaf = function(){
        var url = "/To Change/";
        var params = {jid:$rootScope.selectedJaf};
        $http.post(url,params)
            .success(function(response){
                $scope.jaf = $rootScope.copyObject(response.jaf);
            })
            .error(function(response) {
        });
    };

   
});
