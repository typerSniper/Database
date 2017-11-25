app.controller('ICHomeController', function($scope, $http, $rootScope, $location) {
   $scope.companies = [];
       $scope.fun = function(){
           var url = "/ic/getcompany";
           $http.get(url).success(function(response){
               $scope.companies = $rootScope.copyObject(response.companies);
           })
       }

});