app.controller('StudentHomeController', function($http, $scope, $rootScope, $route) {
    $scope.input = {
        name:'',
        dob:'',
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
        collegedetails:{
          university:'',
          institute:'',
          year:'',
          cpi:'',
        },
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
        var url = '/student/save_details';
<<<<<<< HEAD
        $http.post(url, {name: $scope.input.name})
=======
        var params = $rootScope.copyObject($scope.input);
        $http.post(url, params)
>>>>>>> c23970044770ddce43b7f201f3cafffd4c039fa0
            .success(function(response) {
                console.log(response);
                $rootScope.stage = 2; //change this
                $route.reload();
            })
            .error(function(response) {
                $scope.errorMessage = "Cannot Connect";
            });
    };

});