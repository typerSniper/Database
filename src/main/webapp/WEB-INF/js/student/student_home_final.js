app.controller('StudentFinalController', function($http, $scope, $interval,$location,$rootScope){
    $scope.jafList = [];

    $scope.getJafs = function(){
    	$scope.selectedJaf = null;
        var url = "/To Change/";
        $http.get(url)
            .success(function(response){
                $scope.jafList = $rootScope.copyObject(response.jafs);
            })
            .error(function(response) {
        });
    };

    $scope.getJafDetails = function(jid){
    	$rootScope.selectedJaf = jid;
    	$location.path();

    };
    $scope.signJaf = function(jid){
    	$rootScope.selectedJaf = jid;
    };
});
