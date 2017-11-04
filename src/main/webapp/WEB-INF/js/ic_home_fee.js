app.controller('ICFeeController', function($scope, $http, $rootScope, $location) {
    $scope.studentList = {};
    $scope.selectedStudentList = {};
    $scope.getFeeRequests = function(){
        // var url = "/ic_fee_students";
        // $http.get(url)
        //     .success(function(response){
        //         $scope.studentList = $rootScope.copyObject(response.students);
        //     })
        //     .error(function(response) {
        //     });
    };

    $scope.sendFeeVerifications = function(){ //TODO
        // var url = "/ic_advance_fee";
        // for(){
        //     var params = {name: "pop"}
        //     $http.post(url, params)
        //         .success(function(response){

        //         })
        //         .error(function(response){

        //         });
        // }
    };
});