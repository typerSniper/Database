app.controller('CompanyExistingJafsController', function($http, $scope, $interval,$location,$rootScope){
    $scope.jafList = [];
    $scope.selectedJafList = [];
    

    $scope.getExistingJaf = function(){
    	$rootScope.getCompanyjaf = null;
		var url = "/comapny/get_jobs";
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
        $location.path("/comapny/selections");
    }
    $scope.publish_results = function(index)
    {
        $rootScope.getCompanyjaf = index;
        $location.path("/comapny/publishresults");
    }
});