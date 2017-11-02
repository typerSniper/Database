<div ng-controller="ICFeeController" ng-init="getFeeRequests()">
    <jumbotron>Pending Fee Requests</jumbotron>
    <ol>
        <li ng-repeat="studentList as student">{{student}}</li>
    </ol>
</div>