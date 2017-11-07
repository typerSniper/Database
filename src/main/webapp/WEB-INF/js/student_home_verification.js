app.controller('StudentVerificationController', function($http, $scope, $interval){

    $scope.comments = 2;
    $scope.type = 'res0';
    console.log($scope.comments);
    $scope.input = [];
    $scope.submitResume = function(index) {
        var id = 'res' + index;
        var f = document.getElementById(id).files[0];

        var r = new FileReader();
        r.onloadend = function(e) {
            var data = e.target.result;
            var url = "/student/save_resume";
            var params = {type: index, resumeData: data};
            // $http.post(url, params)
            //     .success(function(response) {
            //     if(response.success){
            //         console.log("Done");
            //     }
            // })
            // .error(function(response) {
            //     // $scope.errorMessage = "Cannot Connect";
            // });
            $scope.resumeString = data;
             console.log(data);
        }
        r.readAsDataURL(f);
    }
     $scope.submitResumeVer = function() {
        var f = document.getElementById('ver').files[0];

        var r = new FileReader();
        r.onloadend = function(e) {
            var data = e.target.result;
            var url = "/student/save_resume_ver";
            var params = {resumeData: data};
            // $http.post(url, params)
            //     .success(function(response) {
            //     if(response.success){
            //         console.log("Done");
            //     }
            // })
            // .error(function(response) {
            //     // $scope.errorMessage = "Cannot Connect";
            // });
            $scope.resumeString = data;
             console.log(data);
        }
        r.readAsDataURL(f);
    }
});