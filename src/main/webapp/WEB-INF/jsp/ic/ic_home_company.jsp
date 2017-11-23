<div ng-controller="ICCompanyController" ng-init="getFeeRequests()">

    <div class="jumbotron" style="text-align: center;"><h2>Pending Company Registration Requests</h2></div>

    <div ng-show="studentList.length > 0" style="margin: 50px" class="list-group">
        <button type="button" class="list-group-item" ng-repeat="company in companyList track by company.username" ng-click="selectCompany(company)" ng-class="{'active': isSelected(company)}">{{company.username}}</button>
    </div>

    <div ng-show="companyList.length == 0" style="text-align: center;">
        No pending requests
    </div>

    <div ng-show="companyList.length > 0" style="text-align: center;">
        <button class="btn btn-success" ng-click="sendFeeVerifications()">Verify</button>
    </div>

</div>