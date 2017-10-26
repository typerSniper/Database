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