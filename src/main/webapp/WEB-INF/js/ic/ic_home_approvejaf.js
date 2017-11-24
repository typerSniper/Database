app.controller('ICApproveJafController', function($scope, $http, $rootScope, $location) {
    $scope.jafList = [];
    $scope.selectedJafList = [];
    $scope.show = [];
    $scope.content = [];

    $scope.getjafRequests = function(){
        var url = "/ic/get_pending_jaf";
        $http.get(url)
            .success(function(response){
                $scope.jafList = $rootScope.copyObject(response.jaf);
                for(int i=0; i<$scope.jafList.length; i++){
                    $scope.show[i]=false;
                    $scope.content[i]="";
                }
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
    $scope.verifyjaf = function(index){
         var url = "/ic/verify_jaf";
         params = $rootScope.copyObject({jaf: $scope.jafList[index], advance: true});
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
//deadline: //TODO
    $scope.declinejaf = function(index){
         var url = "/ic/verify_jaf";
         params = $rootScope.copyObject({jaf: $scope.jafList[index], advance: false});
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

    $scope.viewjaf = function(index){
        var url = "/ic/get_jaf";
        //  params = $rootScope.copyObject({jaf: $scope.selectedJafList});
        //  $http.post(url, params)
        //     .success(function(response) {
        //         if(response.success){
        //             $scope.show[index] = true;
        //             $scope.content[index] = "jaf value";
        //         }
        //     })
        //     .error(function(response) {
        // });
    }
});