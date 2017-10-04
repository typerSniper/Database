var app = angular.module('pt-cell', ['ngRoute', 'ngCookies', ]);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'views/app_home',
        })
        .when('/student/login', {
            templateUrl : 'views/student_login',
            controller : 'StudentLoginController'
         })
         .when('/ic/login', {
            templateUrl : 'views/ic_login',
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

