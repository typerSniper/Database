app.controller('StudentResumeController', function($http, $scope, $interval){

    $scope.submitResume = function(index) {
        var id = 'res' + index;
        var f = document.getElementById(id).files[0];

        var r = new FileReader();
        r.onloadend = function(e) {
            var data = e.target.result;
            var url = "/student/save_resume";
            var params = {type: index, resumeData: data};
            $http.post(url, params)
                .success(function(response) {
                if(response.success){
                    console.log("Done");
                }
            })
            .error(function(response) {
                // $scope.errorMessage = "Cannot Connect";
            });
            // console.log(data);
        }
        r.readAsDataURL(f);
    }
});