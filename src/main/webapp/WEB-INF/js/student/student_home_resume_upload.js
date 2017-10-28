app.controller('StudentResumeUploadController', function($http, $scope){

    $scope.submitResume = function(id) {
        var f;
        if(id == 0){
           var f = document.getElementById('res0').files[0];
        }
        if(id == 1){
           var f = document.getElementById('res1').files[0];
        }
        if(id == 2){
           var f = document.getElementById('res2').files[0];
        }
        if(id == 3){
           var f = document.getElementById('res3').files[0];
        }
        if(f != null){
            $scope.selectedFile = document.getElementById('file').value;
            var r = new FileReader();
                r.onloadend = function(e) {
                    var data = e.target.result;
                    console.log(data);
            }
            r.readAsDataURL(f);
        }
    }
});