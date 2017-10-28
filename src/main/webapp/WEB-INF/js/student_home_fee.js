app.controller('StudentFeeController', function($http, $scope, $rootScope, $route){
    $scope.submit = function(){
        console.log("Fee paid");
        $rootScope.stage = 3;
        $route.reload();
    }
});