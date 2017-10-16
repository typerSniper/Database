<html ng-app="ic">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PtCell</title>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular-route.min.js"></script>

        <script type="text/javascript" src="/js/ic_config.js"></script>
       <!--  <script type="text/javascript" src="/js/student_login.js"></script>
        <script type="text/javascript" src="/js/student_home.js"></script>

        <link rel="stylesheet" type="text/css" href="/css/progress.css"> -->

        <base href = "/">
    </head>
    <body>
        <loading></loading>

        <div id="container">
              <div ng-view></div>
        </div>

        <!-- <a href = "/student/login">Student Login {Login using Ldap}</a>
        <br>
        <a href = "/ic/®">IC Login {Login using Ldap}</a> -->

    </body>
</html>
