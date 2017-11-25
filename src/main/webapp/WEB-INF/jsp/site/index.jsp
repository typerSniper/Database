<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PtCell</title>

        <script type="text/javascript" src="/js/jQuery.min.js"></script>
        <script type="text/javascript" src="/js/bootstrap.js"></script>

        <link rel="stylesheet" href="/css/bootstrap.min.css">

        <link rel="stylesheet" type="text/css" href="/css/home.css">
    </head>
    <body>
        <%@include file="index_header.jsp" %>

        <div class="row" style=" margin: 250px; font-size:20px; padding: 10px;">
            <div class="col-sm-5" >
                    <a href = "/student/">Student Login</a>
            </div>
            <br>
            <div class="col-sm-5" >
                <a href = "/coordinator/">Coordinator Login</a>
            </div>
            <br>
            <div class="col-sm-5" >
                <a href = "/recruiter/login">Recruiter Login</a>
            </div>
            <br>
            <div class="col-sm-5" >
                <a href = "/recruiter/register">Recruiter Register</a>
            </div>
        </div>
        <a href = "/site/temp">Recruiter Register</a>
        <%@include file="index_footer.jsp" %>
    </body>
</html>
