app.controller('ICPendingJafController', function($scope, $http, $rootScope, $location) {
    $scope.jafList = [];
    $scope.selectedJafList = [];

    $scope.getjafRequests = function(){
        var url = "/To be published";
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
    //deadline: //TODO
    $scope.publishjaf = function(index){
         var url = "/Publsih jaf";
         params = $rootScope.copyObject({jaf: $scope.jafList[index]});
         $http.post(url, params)
            .success(function(response) {
                if(response.success){
                    $scope.jafList = [];
                    $scope.selectedJafList = [];
                    $scope.getjafRequests();
                }
            })
            .error(function(response) {
        });
    };
});