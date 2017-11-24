app.controller('companyJafController', function($scope, $http, $rootScope, $location,$filter) {
        $scope.date = '';
      $scope.pass='';
      $scope.newElig = {cpicutoff:'', deptid:'', programid:''};
     $scope.input={jname:'',
                  salary:'',
                  location:'',
                  description:'',
                  eligiblity:[],
                  comp_deadline:''
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
      $scope.input.comp_deadline = $filter('date')($scope.date, 'yyyy-MM-dd');

      if($scope.input.eligiblity.length==0)
      {
        alert("You need to add atleast one eligiblity rule(Select all in the options for no eligiblity requirement)")
      }
        else{

        var url = '/company/create_job';
        var params = $rootScope.copyObject($scope.input);
        $http.post(url, params)
            .success(function(response) {
                if(response.success){
                    $location.path("/recruiter/home");
                }
            })
            .error(function(response) {
        });
        }
    };

});