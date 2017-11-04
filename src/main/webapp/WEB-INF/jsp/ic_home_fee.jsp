<div ng-controller="ICFeeController" ng-init="getFeeRequests()">
    <jumbotron>Pending Fee Requests</jumbotron>
    <ol>
        <li ng-repeat="student in studentList">{{student}}</li>
    </ol>
</div>