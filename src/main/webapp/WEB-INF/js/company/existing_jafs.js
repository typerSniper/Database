app.controller('CompanyExistingJafsController', function(){
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

    $scope.student_interview = function(index)
    {
    	$rootScope.getCompanyjaf = index;
        $scope.getExistingJaf();
        $location.path("comapny/interview");


    };
    $scope.final_selections = function(index)
    {
    	$rootScope.getCompanyjaf = index;
        $scope.getExistingJaf();
        $location.path("comapny/selections");



    }
});