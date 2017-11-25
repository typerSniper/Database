<div ng-controller="ICHomeController" ng-init="fun()">
    <!-- <a href="/coordinator/fee">Pending Fee Requests</a>
    <button ng-click="getResume('student4')">Get Resume</button> -->
    <h1>IC HOME</h1>

    <div ng-show="companies.length > 0"  align="center">
        <h3>Alloted Companies</h3>
        <table class="table table-stripped">
            <tr><td>Name</td><td>Email</td><td>Number</td></tr>
            <tr ng-repeat="company in companies track by $index" style="margin: 50px" class="list-group"><td>{{company.name}}</td><td>{{company.email}}</td><td>{{company.contact}}</td></tr>
        </table>
    </div>
</div>