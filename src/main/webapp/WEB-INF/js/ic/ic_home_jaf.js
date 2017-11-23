app.controller('ICJafController', function($scope, $http, $rootScope, $location) {
    $scope.jafList = [];
    $scope.selectedJafList = [];
    $scope.getjafRequests = function(){
        var url = "/To Change/";
        $http.get(url)
            .success(function(response){
                $scope.jafList = $rootScope.copyObject(response.jaf);
            })
            .error(function(response) {
        });
    };

    $scope.selectJaf = function(jaf){
        if($scope.isSelected(jaf)){
            var index = $scope.selectedJafList.indexOf(jaf);
            $scope.selectedJafList.splice(index, 1);
        }
        else{
            $scope.selectedJafList.push(jaf);
        }
    }

    $scope.isSelected = function(jaf){
        var index = $scope.selectedJafList.indexOf(jaf);
        if(index == -1){
            return false;
        }
        return true;
    }

    $scope.sendFeeVerifications = function(){
         var url = "/To Change/";
         params = $rootScope.copyObject({jaf: $scope.selectedJafList});
         $http.post(url, params)
            .success(function(response) {
                if(response.success){
                    console.log("Done");
                    $scope.jafList = [];
                    $scope.selectedJafList = [];
                    $scope.getFeeRequests();
                }
            })
            .error(function(response) {
        });
    };
});