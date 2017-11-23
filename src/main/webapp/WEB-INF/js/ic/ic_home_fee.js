app.controller('ICFeeController', function($scope, $http, $rootScope, $location) {
    $scope.studentList = [];
    $scope.selectedStudentList = [];
    $scope.getFeeRequests = function(){
        var url = "/ic/fee_students";
        $http.get(url)
            .success(function(response){
                $scope.studentList = $rootScope.copyObject(response.students);
            })
            .error(function(response) {
        });
    };

    $scope.selectStudent = function(student){
        if($scope.isSelected(student)){
            var index = $scope.selectedStudentList.indexOf(student);
            $scope.selectedStudentList.splice(index, 1);
        }
        else{
            $scope.selectedStudentList.push(student);
        }
    }

    $scope.isSelected = function(student){
        var index = $scope.selectedStudentList.indexOf(student);
        if(index == -1){
            return false;
        }
        return true;
    }

    $scope.sendFeeVerifications = function(){
         var url = "/ic/advance_fee";
         params = $rootScope.copyObject({students: $scope.selectedStudentList});
         $http.post(url, params)
            .success(function(response) {
                if(response.success){
                    console.log("Done");
                    $scope.studentList = [];
                    $scope.selectedStudentList = [];
                    $scope.getFeeRequests();
                }
            })
            .error(function(response) {
        });
    };
});