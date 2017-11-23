<div ng-controller="ICJafController" ng-init="getFeeRequests()">

    <div class="jumbotron" style="text-align: center;"><h2>Pending Jaf Requests</h2></div>

    <div ng-show="jafList.length > 0" style="margin: 50px" class="list-group">
        <button type="button" class="list-group-item" ng-repeat="jaf in studentList track by $index" ng-click="selectJaf(jaf)" ng-class="{'active': isSelected(jaf)}">{{jaf.jid}}</button>
    </div>

    <div ng-show="jafList.length == 0" style="text-align: center;">
        No pending requests
    </div>

    <div ng-show="jafList.length > 0" style="text-align: center;">
        <button class="btn btn-success" ng-click="sendFeeVerifications()">Verify</button>
    </div>

</div>