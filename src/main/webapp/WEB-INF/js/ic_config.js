var app = angular.module('ic', ['ngRoute', 'ui.bootstrap']);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when('/coordinator',{
            templateUrl : 'views/ic_login'
        })
        .when('/coordinator/home', {
            templateUrl : 'views/ic_home',
        })
        .when('/coordinator/fee', {
            templateUrl : 'views/ic_home_fee',
        })
        .when('/404', {
            templateUrl : 'views/404',
        })
        .otherwise({
            redirectTo: '/404'
        });
        $locationProvider.html5Mode(true);
});


