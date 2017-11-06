<div ng-controller="ICResumeController" ng-init="getResumeRequests()">

    <div class="jumbotron" style="text-align: center;"><h2>Pending Resume Verifications</h2></div>

    <div class="col-sm-6">
        <div ng-show="studentList.length > 0" style="margin: 50px" class="list-group">
            <button type="button" class="list-group-item" ng-repeat="student in studentList track by student.username" ng-click="selectStudent(student)" ng-class="{'active': isSelected(student)}">{{student.username}}, Resume No- {{student.rtype}}</button>
        </div>

        <div ng-show="studentList.length == 0" style="text-align: center;">
            No pending requests
        </div>

        <div ng-show="studentList.length > 0" style="text-align: center; margin: 10px">
            <button class="btn btn-info" ng-click="seeResume()">See Resume</button>
        </div>
        <div ng-hide="isSelected(null)" style="text-align: center; margin: 10px">
            <button class="btn btn-warning" ng-click="verifyResume()">Verify Resume</button>
        </div>
    </div>
    <div class="col-sm-6">
        <div style="margin: 50px">
            <iframe src="" id="resume" frameborder="0" scrolling="yes" seamless="seamless" style="width:100%; height: 40%;"></iframe>
        </div>
    </div>

</div>