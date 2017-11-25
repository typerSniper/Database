app.controller('StudentViewJafController', function($http, $scope, $interval,$location,$rootScope, $filter, $route){
    $scope.jafDetails = {};
    $scope.eligiblities =[];
    $scope.jafID = '';
    $scope.viewjaf = function(jid){
        if(jid == null){
            $location.path("/student/home");
        }
        var url = "/student/get_jaf_details";
        $scope.jafID = jid;
         params = $rootScope.copyObject({jafID: jid});
         $http.post(url, params)
            .success(function(response) {
                console.log(response);
                $scope.jafDetails = response.jaf;
                $scope.eligibilities = response.eligiblities;
                var date = new Date($scope.jafDetails.jafDeadline);
                $scope.jafDetails.jafDeadline = $filter('date')(date, 'yyyy-MM-dd');
            })
            .error(function(response) {
        });
    }
    $scope.signJaf = function(jid){
        $rootScope.setSigned = $rootScope.setSigned;
        $rootScope.selectedJaf = $scope.jafID;
        $location.path("/student/signjaf");

    };
    $scope.back = function(){
        $location.path("/student/home");
        $route.reload();
    }
   
});
