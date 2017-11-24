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

    $scope.getJafDetails = function(jaf){
    	$rootScope.setSigned = jaf.status;
    	$rootScope.selectedCompany=jaf.company;
    	$rootScope.selectedJaf = jaf.jid;
    	$location.path("student/viewjaf");

    };
    $scope.signJaf = function(jid){
    	$rootScope.setSigned = jaf.status;
    	$rootScope.selectedCompany=jaf.company;
		$rootScope.selectedJaf = jid;
    	$location.path("student/signjaf");

    };
});
