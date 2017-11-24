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
        .when('/coordinator/approvejaf', {
            templateUrl : 'views/ic/ic_home_approvejaf',
        })
        .when('/coordinator/pendingjaf', {
            templateUrl : 'views/ic/ic_home_pendingjaf',
        })
        .when('/coordinator/company', {
            templateUrl : 'views/ic/ic_home_company',
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
        if(next.templateUrl == "views/ic/ic_login") {
            console.log("here");
            $rootScope.loggedIn = false;
            $http.get("/coordinator/is_authenticated").success(function(response) {
                    console.log(response);
                if(response.authenticated) {
                    console.log("here");
                    $rootScope.loggedIn = true;
                    $location.path("/coordinator/home");
                }
                else{
                    $rootScope.loggedIn = false;
                }
            });
        }
        else{
            $rootScope.loggedIn = true;
        }
    });
});



