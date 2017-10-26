var app = angular.module('ic', ['ngRoute', ]);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when('/ic',{
            templateUrl : 'views/ic_login'
        })
        // .when('/ic/home', {
        //     templateUrl : 'views/student_home',
        // })
        .when('/404', {
            templateUrl : 'views/404',
        })
        .otherwise({
            redirectTo: '/404'
        });
        $locationProvider.html5Mode(true);
});


