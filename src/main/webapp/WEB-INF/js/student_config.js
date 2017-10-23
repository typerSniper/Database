var app = angular.module('student', ['ngRoute', 'ui.bootstrap']);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when('/student',{
            templateUrl : 'views/student_login'
        })
        .when('/student/home', {
            templateUrl : 'views/student_home',
        })
        .when('/404', {
            templateUrl : 'views/404',
        })
        .otherwise({
            redirectTo: '/404'
        });
        $locationProvider.html5Mode(true);
});

app.run( function($rootScope, $location) {
    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
        if(next.templateUrl == "views/student_home"){
            if($rootScope.stage == null){
                $rootScope.stage=1;
                //send request and get stage
            }
            // next.templateUrl = "views/student_home_form"
            if($rootScope.stage == 1){
                next.templateUrl = "views/student_home_form"
            }
            if($rootScope.stage == 2){
                next.templateUrl = "views/student_home_fee"
            }
            if($rootScope.stage == 3){
                next.templateUrl = "views/student_home_resume"
            }
        }
    });
 });

app.directive('loading', function ($http){
    return {
        restrict: 'E',
        template: '<div class="loader"></div><div class="overlay"></div>',
        link: function (scope, elm, attrs)
        {
            scope.isLoading = function () {
                return $http.pendingRequests.length > 0;
            };
            scope.$watch(scope.isLoading, function (v) {
                if (v) {
                    elm.show();
                }
                else {
                    elm.hide();
                }
            });
        }
    };
});

app.directive('progressBar0', function(){
    return {
        restrict: 'E',
        scope: {
            currentActive: '=active'
        },
        templateUrl: 'views/progressBar'
    };
});