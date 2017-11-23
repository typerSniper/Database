var app = angular.module('student', ['ngRoute', 'ui.bootstrap']);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when('/student',{
            templateUrl : 'views/student/student_login'
        })
        .when('/student/home', {
            templateUrl : 'views/student/student_home',
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
            delete $rootScope.stage;
            $rootScope.loggedIn = false;
            $location.path("/student/");
        });
    }

    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
        if(next.templateUrl == "/"){
            $window.location.reload();
        }
        if(next.templateUrl == "views/student/student_login") {
            $rootScope.loggedIn = false;
            $http.get("/student/is_authenticated").success(function(response) {
                if(response.authenticated) {
                    $rootScope.loggedIn = true;
                    $location.path("/student/home");
                }
            });
        }
        if(next.templateUrl == "views/student/student_home"){
            if($rootScope.stage == null){
                $http.get("/student/stage").success(function(response){
                    if(response.authenticated){
                        $rootScope.loggedIn = true;
                        $rootScope.stage = response.stage;
                        $location.path("/student/home");
                        $route.reload();
                    }
                    else{
                        $rootScope.loggedIn = false;
                        $location.path("/student");
                    }
                });
            }
            else{
                $rootScope.loggedIn = true;
            }
            if($rootScope.stage == 1){
                next.templateUrl = "views/student/student_home_form"
            }
            if($rootScope.stage == 2){
                next.templateUrl = "views/student/student_home_fee"
            }
            if($rootScope.stage == 3){
                next.templateUrl = "views/student/student_home_fee"
            }
            if($rootScope.stage == 4){
                next.templateUrl = "views/student/student_home_resume"
            }
            if($rootScope.stage == 5){
                next.templateUrl = "views/student/student_home_verification"
            }
            if($rootScope.stage == 6){
                next.templateUrl = "views/student/student_home_final"
            }
        }
    });
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

app.directive('countdown', function (Util, $interval, $rootScope) {
    return {
        restrict: 'A',
        scope: { date: '@' },
        link: function (scope, element) {
            var future;
            future = new Date(scope.date);
            $interval(function () {
                var diff;
                diff = Math.floor((future.getTime() - new Date().getTime()) / 1000);
                if(diff <= 0){
                    $rootScope.resumeDeadlinePassed = true;
                    return element.text("Deadline Passed");
                }
                return element.text(Util.dhms(diff));
            }, 1000);
        }
    };
});

app.factory('Util', function () {
    return {
        dhms: function (t) {
            var days, hours, minutes, seconds;
            days = Math.floor(t / 86400);
            t -= days * 86400;
            hours = Math.floor(t / 3600) % 24;
            t -= hours * 3600;
            minutes = Math.floor(t / 60) % 60;
            t -= minutes * 60;
            seconds = t % 60;
            return [
                days + 'd',
                hours + 'h',
                minutes + 'm',
                seconds + 's'
            ].join(' ');
        }
    };
});