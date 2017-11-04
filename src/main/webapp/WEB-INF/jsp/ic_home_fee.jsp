<div ng-controller="ICFeeController" ng-init="getFeeRequests()">
    <jumbotron>Pending Fee Requests</jumbotron>
    <ol ng-show="studentList.length > 1">
        <li ng-repeat="student in studentList track by student.username" ng-click="isSelected(student)">{{student.username}}</li>
    </ol>
    <div ng-show="studentList.length == 0">
        No pending requests
    </div>

    <button class="btn btn-success" ng-click="sendFeeVerifications()">Verify</button>
    <a href="/coordinator/home">Home</a>

</div>