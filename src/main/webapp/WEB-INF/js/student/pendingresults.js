app.controller('StudentSignedJafController', function($http, $scope, $interval,$location,$rootScope){
    $scope.pendingjafs = [];

    $scope.pendingJafs = function(){
    	$scope.selectedJaf = null;
        var url = "/To Change/";
        $http.get(url)
            .success(function(response){
                $scope.pendingjafs = $rootScope.copyObject(response.jafs);
            })
            .error(function(response) {
        });
    };

    $scope.getJafDetails = function(jaf){
    	$rootScope.selectedJaf = jaf.jid;
        $rootScope.selectedCompany = jaf.company;
        $rootScope.setSigned = null;
    	$location.path("student/viewjaf");

    };
    
});
