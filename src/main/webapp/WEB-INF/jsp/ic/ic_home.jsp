<div ng-controller="ICHomeController" ng-init="fun()">
    <!-- <a href="/coordinator/fee">Pending Fee Requests</a>
    <button ng-click="getResume('student4')">Get Resume</button> -->
    <h1>IC HOME</h1>
<div ng-show="companies.length > 0" style="margin: 50px" class="list-group">
        <button type="button" class="list-group-item" ng-repeat="company in companies track by $index">{{company.name}} {{company.contact}}</button>
    </div>
</div>