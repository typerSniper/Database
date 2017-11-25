<div ng-controller="companyHomeController">
<div class = "jumbotron"align = "center"><h1>Welcome</h1></div>

    <div ng-show="stage == 1">
    <a href="recruiter/newjaf" > <h3>Create New Jaf</h3></a>
        <a href="recruiter/existingjafs" > <h3>See Existing Jafs</h3></a>
    </div>
    <div ng-show="stage == 0">
        Waiting for registration confirmation from PT Cell
    </div>

</div>