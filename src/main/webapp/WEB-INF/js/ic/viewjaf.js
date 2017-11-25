app.controller('ICViewJafController', function($scope, $http, $rootScope, $location, $filter) {
    $scope.jafDetails = {};
    $scope.jafID = '';
    $scope.deadline_date = '';
    $scope.deadline_time='';

    $scope.verifyjaf = function(){
         var url = "/ic/verify_jaf";
         $scope.date = $filter('date')($scope.deadline_date, 'yyyy-MM-dd');
         $scope.time = $filter('date')($scope.deadline_time, 'hh:mm:ss')
         params = $rootScope.copyObject({jafID: $scope.jafID, advance: true, deadline: $scope.date + " " + $scope.time});
         $http.post(url, params)
            .success(function(response) {
                if(response.success){
                    $rootScope.selectedJafID = null;
                    $location.path("/coordinator/approvejaf");
                }
            })
            .error(function(response) {
        });
    };

    $scope.declinejaf = function(){
         var url = "/ic/verify_jaf";
         params = $rootScope.copyObject({jafID: $scope.jafID, advance: false, deadline: null});
         $http.post(url, params)
            .success(function(response) {
                if(response.success){
                    $rootScope.selectedJafID = null;
                    $location.path("/coordinator/approvejaf");
                }
            })
            .error(function(response) {
        });
    };

    $scope.viewjaf = function(jid){
        if(jid == null){
            $location.path("/coordinator/approvejaf");
        }
        var url = "/ic/get_jaf";
        $scope.jafID = jid;
         params = $rootScope.copyObject({jafID: jid});
         $http.post(url, params)
            .success(function(response) {
                console.log(response);
                $scope.jafDetails = response.jaf;
                var date = new Date($scope.jafDetails.companyDeadline);
                $scope.jafDetails.companyDeadline = $filter('date')(date, 'yyyy-MM-dd');
            })
            .error(function(response) {
        });
    }
});