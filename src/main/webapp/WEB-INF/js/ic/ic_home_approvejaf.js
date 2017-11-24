app.controller('ICApproveJafController', function($scope, $http, $rootScope, $location) {
    $scope.jafList = [];

    $scope.getjafRequests = function(){
        var url = "/ic/get_pending_jaf";
        $http.get(url)
            .success(function(response){
            console.log(response);
                $scope.jafList = $rootScope.copyObject(response.jafs);
               for(var i=0; i<$scope.jafList.length; i++){
                   $scope.show[i]=false;
                   $scope.content[i]="";
               }
            })
            .error(function(response) {
        });
    };

    $scope.selectJaf = function(jaf){
        $rootScope.selectedJafID = jaf.jid;
        $location.path("/coordinator/viewjaf");
    }
});