app.controller('StudentSignJafController', function($http, $scope, $interval,$location,$rootScope){
    $scope.resume_type = "";

    $scope.signjaf = function() {
        
        var f = document.getElementById('res').files;
        if(f.length>0)
        {
            var r = new FileReader();
            r.onloadend = function(e) {
                var data = e.target.result;
                var url = "/To Change";
                var params = {company:$rootScope.selectedCompany,jid:$rootScope.selectedJaf,resume: index, sopData: data,sop:true};
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
            var url = "/To Change";
            var params = {company:$rootScope.selectedCompany,jid:$rootScope.selectedJaf,resume: index, sopData: "",sop:false};
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
    };

    $scope.unsignjaf = function()
    {
        var url = "//To Change";
            var params = {company:$rootScope.selectedCompany,jid:$rootScope.selectedJaf};
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
   
});
