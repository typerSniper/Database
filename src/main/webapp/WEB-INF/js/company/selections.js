app.controller('CompanySelectionsController', function($http, $scope, $interval,$location,$rootScope){
    $scope.studentList = [];    

    $scope.getStudentlist = function(){
        if($rootScope.getCompanyjaf == null)
        {
            $location.path("/comapny/existingjafs");
        }
        var url = "/comapny/all_students";
        var params = $rootScope.copyObject({jafID: $rootScope.getCompanyjaf})
        
        $http.post(url,params)
            .success(function(response){
                $scope.studentList = $rootScope.copyObject(response.allStudents);
                
            })
            .error(function(response) {
        });
    };
    
    
    $scope.back = function()
    {
        
        $location.path("/comapny/existingjafs");
        
    }
});