app.controller('ICCompanyController', function($scope, $http, $rootScope, $location) {
    $scope.companyList = [];
    $scope.selectedCompanyList = [];
    $scope.getcompanyRequests = function(){
        var url = "/ic/get_pending_company";
        $http.get(url)
            .success(function(response){
                $scope.companyList = $rootScope.copyObject(response.companies);
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

    $scope.sendFeeVerifications = function(){
         var url = "/ic/advance_fee";
         params = $rootScope.copyObject({company: $scope.selectedCompanyList});
         $http.post(url, params)
            .success(function(response) {
                if(response.success){
                    console.log("Done");
                    $scope.studentList = [];
                    $scope.selectedStudentList = [];
                    $scope.getFeeRequests();
                }
            })
            .error(function(response) {
        });
    };
});