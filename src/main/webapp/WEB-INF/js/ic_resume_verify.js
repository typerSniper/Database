app.controller('ICResumeController', function($scope, $http, $rootScope, $location) {
    $scope.studentList = [];
    $scope.selectedStudent = {};
    $scope.getResumeRequests = function(){
        var url = "/ic/get_student_resume";
        $http.get(url)
            .success(function(response)){
                if(response.students.length > 0){
                    $scope.studentList = $rootScope.copyObject(response.students);
                }
            }
    }

    $scope.selectStudent = function(student){
        if($scope.isSelected(student)){
            $scope.selectedStudent = {};
        }
        else{
            $scope.selectedStudent = student;
        }
    }

    $scope.isSelected = function(student){
        if($scope.selectedStudent == student){
            return true;
        }
        return false;
    }

    $scope.seeResume = function(){
        var url = "/ic/get_resume";
        var params = {username: $scope.selectedStudent.username, rtype: $scope.selectedStudent.rtype};
        $http.post(url, params)
            .success(function(response){
                if(response.resume != null){
                    document.getElementById("resume").src = "data:application/pdf;base64,"+ response.resume;
                }
                else{

                }
            })
            .error(function(response){
            })
    }

});