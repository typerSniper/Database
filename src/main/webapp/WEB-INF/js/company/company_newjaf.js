app.controller('companyJafController', function($scope, $http, $rootScope, $location,$filter) {
        $scope.date = '';
      $scope.pass='';
      $scope.newElig = {cpicutoff:'', deptid:'', programid:''};
     $scope.input={postname:'',
                  salary:'',
                  description:'',
                  location:'',
                  eligiblity:[],
                  deadline_date:'',
                  deadline_time:'',
                };
    $scope.deptid=["CSE","CSE","CSE","CSE"];
    $scope.programid=["Btech1","Mtech","PHD","Dual"];
    $scope.addElig = function(){
        $scope.input.eligiblity.push($rootScope.copyObject($scope.newElig));
    };
    $scope.delElig = function(index){
      $scope.input.eligiblity.splice(index,1);
    };

    $scope.submit = function(){
      $scope.input.deadline_date = $filter('date')($scope.date, 'yyyy-MM-dd');

      if($scope.input.eligiblity.length==0)
      {
        alert("You need to add atleast one eligiblity rule(Select all in the options for no eligiblity requirement)")
      }
        else{

          // var url = '/company/save_details';
        // var params = $rootScope.copyObject($scope.input);
        // $http.post(url, params)
        //     .success(function(response) {
        //         if(response.success){
        //             console.log(response);
        //             $rootScope.stage = response.stage;
        //             $route.reload();
        //         }
        //     })
        //     .error(function(response) {
        // });
        console.log($scope.input);
        }
    };

});