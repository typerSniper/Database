app.controller('StudentHomeController', function($http, $scope, $rootScope, $route, $filter) {
    $scope.date = '';
    $scope.input={dob: '',
                  sex:'',
                  category:'',
                  nationality:'',
                  univemail:'',
                  peremail:'',
                  hosteladdress:'',
                  contact1:'',
                  contact2:'',
                  homecontact:'',
                  homeaddress:{
                    state:'',
                    city:'',
                    pin:'',
                    locality:'',
                    country:'',
                  },
                  skypeid:'',
                  detail12th:{
                    university:'',
                    institute:'',
                    year:'',
                    cpi:'',
                  },
                  detail10th:{
                    university:'',
                    institute:'',
                    year:'',
                    cpi:'',
                  },
                  others:{
                    university:'',
                    institute:'',
                    year:'',
                    cpi:'',
                  }
              };

    $scope.categories =["Gen","SC","ST","OBC","PH"];

    $scope.submit = function(){
      $scope.input.dob = $filter('date')($scope.date, 'dd-MM-yyyy')
        var url = '/student/save_details';
        var params = $rootScope.copyObject($scope.input);
        console.log(params);
        $http.post(url, params)
            .success(function(response) {
                if(response.success){
                    console.log(response);
                    $rootScope.stage = 2; //change this
                    // $rootScope.stage = response.stage;
                    $route.reload();
                }
            })
            .error(function(response) {
                // $scope.errorMessage = "Cannot Connect";
            });
    };

});