<html ng-app="company">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PtCell</title>

        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/css/loader.css">


        <script type="text/javascript" src="/js/jQuery.min.js"></script>
        <script type="text/javascript" src="/js/angular.min.js"></script>
        <script type="text/javascript" src="/js/angular-route.min.js"></script>
        <script type="text/javascript" src="/js/ui-bootstrap-tpls.min.js"></script>
        <script type="text/javascript" src="/js/bootstrap.js"></script>

        <script type="text/javascript" src="/js/company/company_config.js"></script>
        <script type="text/javascript" src="/js/company/company_login.js"></script>
        <script type="text/javascript" src="/js/company/company_register.js"></script>  
        <script type="text/javascript" src="/js/company/company_home.js"></script> 
        <script type="text/javascript" src="/js/company/company_newjaf.js"></script>  

        <script type="text/javascript" src="/js/directives.js"></script>

        <base href = "/">
    </head>
    <body>


        <loading></loading>

        <div id="container">
              <div ng-view></div>
        </div>

    </body>
</html>
