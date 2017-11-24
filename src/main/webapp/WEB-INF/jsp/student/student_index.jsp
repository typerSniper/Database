<html ng-app="student">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PtCell</title>

        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/css/progress.css">
        <link rel="stylesheet" type="text/css" href="/css/form_input.css">
        <link rel="stylesheet" type="text/css" href="/css/home.css">
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <link rel="stylesheet" type="text/css" href="/css/loader.css">


        <script type="text/javascript" src="/js/jQuery.min.js"></script>
        <script type="text/javascript" src="/js/angular.min.js"></script>
        <script type="text/javascript" src="/js/angular-route.min.js"></script>
        <script type="text/javascript" src="/js/ui-bootstrap-tpls.min.js"></script>
        <script type="text/javascript" src="/js/bootstrap.js"></script>

        <script type="text/javascript" src="/js/student/student_config.js"></script>
        <script type="text/javascript" src="/js/student/student_login.js"></script>
        <script type="text/javascript" src="/js/student/student_home.js"></script>
        <script type="text/javascript" src="/js/student/student_home_fee.js"></script>
        <script type="text/javascript" src="/js/student/student_home_resume.js"></script>
        <script type="text/javascript" src="/js/student/student_home_verification.js"></script>
        <script type="text/javascript" src="/js/student/editdetails.js"></script>
        <script type="text/javascript" src="/js/student/student_home_final.js"></script>
        <script type="text/javascript" src="/js/student/viewjaf.js"></script>
        <script type="text/javascript" src="/js/student/signjaf.js"></script>
        <script type="text/javascript" src="/js/student/pendingresults.js"></script>





        <script type="text/javascript" src="/js/directives.js"></script>

        <base href = "/">
    </head>
    <body>

        <nav class="navbar navbar-default" ng-show="loggedIn">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">PT CELL</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/student/home">Home</a></li>
                    <li ng-if="stage == 6" class="active"><a href="/student/editdetails">Edit Details</a></li>
                    <li ng-if="stage == 6" class="active"><a href="/student/signedjafs">Signed Jafs </a></li>
 
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#" ng-click="logout()"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
                </ul>
            </div>
        </nav>

        <nav class="navbar navbar-default" ng-show="!loggedIn">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                  <li class="active"><a href="/">PT Cell Home</a></li>
                </ul>
            </div>
        </nav>

        <loading></loading>

        <div id="container">
              <div ng-view></div>
        </div>

    </body>
</html>
