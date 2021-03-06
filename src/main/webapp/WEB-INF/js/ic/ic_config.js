var app = angular.module('ic', ['ngRoute', 'ui.bootstrap']);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when('/coordinator',{
            templateUrl : 'views/ic/ic_login'
        })
        .when('/coordinator/home', {
            templateUrl : 'views/ic/ic_home',
        })
        .when('/coordinator/fee', {
            templateUrl : 'views/ic/ic_home_fee',
        })
        .when('/coordinator/resume_verify',{
            templateUrl : 'views/ic/ic_resume_verify'
        })
        .when('/',{
            templateUrl : '/',
        })
        .when('/404', {
            templateUrl : 'views/404',
        })
        .otherwise({
            redirectTo: '/404'
        });
        $locationProvider.html5Mode(true);
});

app.run( function($rootScope, $location, $http, $route, $window) {
    $rootScope.copyObject = function(object) {
        return JSON.parse(JSON.stringify(object));
    }

    $rootScope.logout = function(){
        $http.get("/logout").success(function(response) {
            $rootScope.loggedIn = false;
            $location.path("/coordinator/");
        });
    }

    $rootScope.isCurrentPage = function(str){
        if($location.path() === str){
            return true;
        }
        return false;
    }

    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
        if(next.templateUrl == "/"){
            $window.location.reload();
        }
        if(next.templateUrl == "views/ic_login") {
            $rootScope.loggedIn = false;
            $http.get("/coordinator/is_authenticated").success(function(response) {
                if(response.authenticated) {
                    $rootScope.loggedIn = true;
                    $location.path("/coordinator/home");
                }
            });
        }
        else{
            $rootScope.loggedIn = true;
        }
    });
});



