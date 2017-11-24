app.controller('companyJafController', function($scope, $http, $rootScope, $location,$filter) {
        $scope.date = '';
        $scope.curr="";
        $scope.salnumber="";
      $scope.pass='';
      $scope.newElig = {cpicutoff:'0', deptid:'', programid:''};
     $scope.input={jname:'',
                  salary:'',
                  location:'',
                  description:'',
                  eligibilities:[],
                  comp_deadline:''
                };
    $scope.currlist=["INR","USD","GBP"];
    $scope.deptid=["CS","Chemical","Elec", "Mech", "Meta", "All"];

    // $scope.deptid=["CSE","Chemical Engineering","Electrical Engineering","Electrical Engineering Dual","Mechanical Engineering","Mechanical Engineering Dual","Metallurgy"];
    $scope.programid=["Btech1","Btech2","Btech3","Btech4","Mtech1", "Mtech2", "All"];
    $scope.addElig = function(){
        $scope.input.eligibilities.push($rootScope.copyObject($scope.newElig));
    };
    $scope.delElig = function(index){
      $scope.input.eligibilities.splice(index,1);
    };

    $scope.submit = function(){
      $scope.input.comp_deadline = $filter('date')($scope.date, 'yyyy-MM-dd');
      $scope.input.salary = $scope.salnumber + " "+ $scope.curr;
      if($scope.input.eligibilities.length==0)
      {
        alert("You need to add atleast one eligibilities rule(Select all in the options for no eligibilities requirement)")
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