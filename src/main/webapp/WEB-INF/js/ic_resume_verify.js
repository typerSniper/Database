app.controller('ICResumeController', function($scope, $http, $rootScope, $location) {
    $scope.studentList = [{username: 'student1', rtype: '0'}, {username: 'KJ', rtype: '0'}, {username: 'KJ!', rtype: '0'}, {username: 'KJoppo', rtype: '0'}];
    $scope.selectedStudent = {};
    $scope.getResumeRequests = function(){ //TODO
    };

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
        var params = {username: $scope.selectedStudent.username};
        $http.post(url, params)
            .success(function(response){
                if(response.resume.length > 0){
                    document.getElementById("resume").src = "data:application/pdf;base64,"+ response.resume[0].resume;
                }
                else{

                }
            })
            .error(function(response){
                console.log("error");
                console.log(response);
            })
    }

});