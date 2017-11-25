app.controller('StudentFinalController', function($http, $scope, $interval,$location,$rootScope){
    $scope.jafList = [];

    $scope.getJafs = function(){
    	$scope.selectedJaf = null;
        var url = "/student/get_all_jafs";
        $http.get(url)
            .success(function(response){
                $scope.jafList = $rootScope.copyObject(response.jafRepresentatives);
            })
            .error(function(response) {
        });
    };

    $scope.getJafDetails = function(jaf){
    	$rootScope.setSigned = jaf.status;
        $rootScope.setEligible = jaf.eligible;

    	$rootScope.selectedJaf = jaf.jid;
    	$location.path("/student/viewjaf");

    };
    $scope.signJaf = function(jid){
        $rootScope.setSigned = jaf.isSigned;
		$rootScope.selectedJaf = jaf.jid;
    	$location.path("/student/signjaf");

    };
});
