app.controller('StudentResumeController', function($http, $scope, $interval){
    $scope.submitResume = function() {
        var f = document.getElementById('file').files[0];
        $scope.selectedFile = document.getElementById('file').value;
        var r = new FileReader();
        r.onloadend = function(e) {
            var data = e.target.result;
            console.log(data);
        }
        r.readAsDataURL(f);
    }
});