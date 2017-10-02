<div ng-controller="LoginController">
    <div class="col-xs-4"></div>
    <div class="col-xs-4 well well-lg">
        <form ng-submit="login()" name="loginForm" class="form" >
            <div class="form-group">
                <input id="usernameInput" type="text" class="form-control" ng-model="username" placeholder="username" required/>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" ng-model="password" placeholder="password" required/>
            </div>
            <div class="form-group" style="color: red">
                {{errorMessage}}
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-success">Login</button>
            </div>
        </form>
    </div>
    <div class="col-xs-4"></div>
</div>