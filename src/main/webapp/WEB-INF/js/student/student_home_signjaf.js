app.controller('StudentSignJafController', function($http, $scope, $interval,$location,$rootScope){
    $scope.resume_type = "";

    $scope.signjaf = function(index) {
        
        var f = document.getElementById('res').files;
        if(f.length>0)
        {
            var r = new FileReader();
            r.onloadend = function(e) {
                var data = e.target.result;
                var url = "/student/save_resume";
                var params = {resume: index, resumeData: data,sop:true};
                $http.post(url, params)
                    .success(function(response) {
                    if(response.success){
                        console.log("Done");
                    }
                })
                .error(function(response) {
                    // $scope.errorMessage = "Cannot Connect";
                });
            }
            r.readAsDataURL(f[0]);
        }
        else
        {   
            var url = "/student/save_resume";
            var params = {resume: index, resumeData: "",sop:false};
            $http.post(url, params)
                    .success(function(response) {
                    if(response.success){
                        console.log("Done");
                    }
                })
                .error(function(response) {
                    // $scope.errorMessage = "Cannot Connect";
                });
        }
    }
   
});
