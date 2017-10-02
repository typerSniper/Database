var app = angular.module('pt-cell', ['ngRoute', ]);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when('/home', {
            templateUrl : 'views/home'
        })
        .when('/', {
        		redirectTo: '/login'
        	})
        .when('/login', {
            templateUrl : 'views/login',
            controller : 'LoginController'
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

