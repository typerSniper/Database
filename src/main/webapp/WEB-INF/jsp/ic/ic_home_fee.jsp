<div ng-controller="ICFeeController" ng-init="getFeeRequests()">

    <div class="jumbotron" style="text-align: center;"><h2>Pending Fee Requests</h2></div>

    <div ng-show="studentList.length > 0" style="margin: 50px" class="list-group">
        <button type="button" class="list-group-item" ng-repeat="student in studentList track by student.username" ng-click="selectStudent(student)" ng-class="{'active': isSelected(student)}">{{student.username}}</button>
    </div>

    <div ng-show="studentList.length == 0" style="text-align: center;">
        No pending requests
    </div>

    <div ng-show="studentList.length > 0" style="text-align: center;">
        <button class="btn btn-success" ng-click="sendFeeVerifications()">Verify</button>
    </div>

</div>