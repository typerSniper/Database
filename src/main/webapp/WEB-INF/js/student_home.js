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
        var url = '/student/set_details';
        $http.post(url, $scope.input)
            .success(function(response) {
                console.log(response);
                $rootScope.stage = 2; //change this
                $route.reload();
            })
            .error(function(response) {
                $scope.errorMessage = "Cannot Connect";
            });
    };

    // $scope.login = function() {
    //     var url = '/app/login';
    //     var params = {username :$scope.username, password : $scope.password, type : "Student"}
    //     $http.post(url, params)
    //         .success(function(response) {
    //             if(response.authenticated) {
    //                 $location.path('/student/home');
    //                 $cookieStore.put("loggedIn", true);
    //             }
    //             else
    //                  $scope.errorMessage = "Invalid username or password.";
    //         })
    //         .error(function(response) {
    //             $scope.errorMessage = "Cannot Connect";
    //         });
    // };

});