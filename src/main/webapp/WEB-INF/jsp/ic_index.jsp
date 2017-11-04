<html ng-app="ic">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PtCell</title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/css/loader.css">
        <link rel="stylesheet" type="text/css" href="/css/ic_fee.css">

        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular-route.min.js"></script>
         <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.13.0/ui-bootstrap-tpls.min.js"></script>
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

        <script type="text/javascript" src="/js/ic_config.js"></script>
        <script type="text/javascript" src="/js/ic_login.js"></script>
        <script type="text/javascript" src="/js/ic_home.js"></script>
        <script type="text/javascript" src="/js/ic_home_fee.js"></script>
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
                  <li class="active"><a href="/coordinator/home">Home</a></li>
                  <!-- <li class="active"><a href="/coordinator/home"></a></li> -->
                  <!-- <li class="active"><a href="/coordinator/home">Home</a></li> -->
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#" ng-click="logout()""><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>

        <loading></loading>

        <div id="container">
              <div ng-view></div>
        </div>

    </body>
</html>
