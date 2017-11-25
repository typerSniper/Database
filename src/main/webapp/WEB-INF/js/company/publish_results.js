app.controller('CompanyPublishResultController', function($http, $scope, $interval,$location,$rootScope){
    $scope.studentList = [];
    $scope.selectedStudentList = [];
    

    $scope.getStudentlist = function(){
        $('#example').DataTable( {
        } );
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
     $scope.selectStudent = function(student){
        if($scope.isSelected(jaf)){
            var index = $scope.selectedStudentList.indexOf(student);
            $scope.selectedStudentList.splice(index, 1);
        }
        else{
            $scope.selectedStudentList.push(jaf);
        }
    }
    $scope.isSelected = function(student){
        var index = $scope.selectedStudentList.indexOf(student);
        if(index == -1){
            return false;
        }
        return true;
    }
    
    $scope.results = function()
    {
        var url = "/comapny/selected_students";
        var params = $rootScope.copyObject({jafID: $rootScope.getCompanyjaf,selections: $scope.selectedStudentList});
        $http.post(url,params)
            .success(function(response){
                $location.path("/comapny/existingjafs");
                
            })
            .error(function(response) {
        });
    }
});