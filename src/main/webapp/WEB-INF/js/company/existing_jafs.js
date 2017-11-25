app.controller('CompanyExistingJafsController', function($http, $scope, $interval,$location,$rootScope){
    $scope.jafList = [];
    $scope.selectedJafList = [];
    

    $scope.getExistingJaf = function(){
    	$rootScope.getCompanyjaf = null;
		var url = "/company/get_jobs";
        $('#example').DataTable( {
    } );
        $http.get(url)
            .success(function(response){
                $scope.jafList = $rootScope.copyObject(response.jaf);
                
            })
            .error(function(response) {
        });
    };
    $scope.final_selections = function(index)
    {
    	$rootScope.getCompanyjaf = index;
        $location.path("/company/selections");
    }
    $scope.publish_results = function(index)
    {
        $rootScope.getCompanyjaf = index;
        $location.path("/company/publishresults");
    }
});