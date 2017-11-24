app.controller('ICCompanyController', function($scope, $http, $rootScope, $location) {
    $scope.companyList = [];
    $scope.selectedCompanyList = [];
    $scope.getcompanyRequests = function(){
        var url = "/ic/get_pending_company";
        $http.get(url)
            .success(function(response){
                $scope.companyList = $rootScope.copyObject(response.companies).filter(function(t){return t.stage == "unregistered"});
                console.log($scope.companyList);
            })
            .error(function(response) {
        });
    };

    $scope.selectCompany = function(company){
        if($scope.isSelected(company)){
            var index = $scope.selectedCompanyList.indexOf(company);
            $scope.selectedCompanyList.splice(index, 1);
        }
        else{
            $scope.selectedCompanyList.push(company);
        }
    }

    $scope.isSelected = function(company){
        var index = $scope.selectedCompanyList.indexOf(company);
        if(index == -1){
            return false;
        }
        return true;
    }

    $scope.register_company = function(index){
         var url = "/ic/verify_company";
         params = $rootScope.copyObject({companyID: $scope.companyList[index].cid});
         $http.post(url, params)
            .success(function(response) {
                if(response.success){
                    $scope.companyList = [];
                    $scope.getcompanyRequests();
                }
            })
            .error(function(response) {
                alert("An error occured");
                $scope.getcompanyRequests();
        });
    };
});