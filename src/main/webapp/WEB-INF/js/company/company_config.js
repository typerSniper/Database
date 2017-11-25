var app = angular.module('company', ['ngRoute', 'ui.bootstrap']);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
        .when('/recruiter/home',{
            templateUrl : 'views/company/company_home'
        })
        .when('/recruiter/login',{
            templateUrl : 'views/company/company_login'
        })
        .when('/recruiter/register', {
            templateUrl : 'views/company/company_register',
        })
        .when('/recruiter/newjaf', {
            templateUrl : 'views/company/create_jaf',
        })
        .when('/recruiter/existingjafs',{
            templateUrl : 'views/company/existing_jafs',
        })
        .when('/recruiter/selections',{
            templateUrl : 'views/company/selections',
        })
        .when('/recruiter/publishresults',{
            templateUrl : 'views/company/publish_results',
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
            $location.path("/recruiter/login");
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
         else{
            if(next.templateUrl !="views/company/company_login" && next.templateUrl !="views/company/company_home" && next.templateUrl !="views/company/company_register"){
                $http.get("/company/stage").success(function(response) {
                               if(response.authenticated) {
                                    if(response.stage != 1){
                                        $location.path("/recruiter/home");
                                        alert("Registration hasnt been approved yet");
                                    }

                               }
                               else{
                                   $location.path("/recruiter/login");
                               }
                            });
            }
            if(next.templateUrl =="views/company/company_home"){
            $http.get("/company/stage").success(function(response) {
                                           if(response.authenticated) {
                                                $rootScope.stage = response.stage;

                                           }
                                           else{
                                               $location.path("/recruiter/login");
                                           }
                                        });
            }
         }
     });
});



