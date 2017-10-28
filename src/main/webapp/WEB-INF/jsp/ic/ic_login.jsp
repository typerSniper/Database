

<div ng-controller="ICLoginController">
    <div class="col-xs-4"></div>
    <div class="col-xs-4 well well-lg">
        <div style="text-align:center;">
            <h3><strong>Placement Cell</strong></h3>
            <h3><strong>IC Login</strong></h3>
        </div>
        <hr>
        <form ng-submit="login()" name="loginForm" class="form" >
            <div class="form-group">
                <input id="username" type="text" class="form-control" ng-model="username" placeholder="username" required/>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" ng-model="password" placeholder="password" required/>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-success">Login</button>
            </div>
            <div class="form-group" style="color: red">
                 {{errorMessage}}
            </div>
        </form>
    </div>
    <div class="col-xs-4"></div>
</div>
