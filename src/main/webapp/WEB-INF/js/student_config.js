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

app.run( function($rootScope, $location, $http) {
    $rootScope.copyObject = function(object) {
        return JSON.parse(JSON.stringify(object));
    }

    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
        if(next.templateUrl == "views/student_login") {
            $http.get("/is_authenticated").success(function(response) {
                if(response.authenticated) {
                    $location.path("/student/home");
                }
            });
        }
        if(next.templateUrl == "views/student_home" || t === 1){
            console.log("here");
            if($rootScope.stage == null){
                $rootScope.stage=2;
                //send request and get stage
            }
            if($rootScope.stage == 1){
                next.templateUrl = "views/student_home_form"
            }
            if($rootScope.stage == 2){
                next.templateUrl = "views/student_home_fee"
            }
            if($rootScope.stage == 3){
                next.templateUrl = "views/student_home_fee"
            }
            if($rootScope.stage == 4){
                next.templateUrl = "views/student_home_resume"
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

app.directive('countdown', function (Util, $interval) {
    return {
        restrict: 'A',
        scope: { date: '@' },
        link: function (scope, element) {
            var future;
            future = new Date(scope.date);
            $interval(function () {
                var diff;
                diff = Math.floor((future.getTime() - new Date().getTime()) / 1000);
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