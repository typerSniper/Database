<div ng-controller="ICJafController" ng-init="getjafRequests()">

    <div class="jumbotron" style="text-align: center;"><h2>Pending Jaf Requests</h2></div>

    <div ng-show="jafList.length > 0" style="margin: 50px" class="list-group">
        <ul>
            <li ng-repeat="jaf in studentList track by $index">
                <button type="button" class="list-group-item" ng-click="selectJaf(jaf)" ng-class="{'active': isSelected(jaf)}">{{jaf.jid}}</button>
                <button class="btn btn-info" ng-click="viewjaf(jaf)">View</button>
                <button class="btn btn-success" ng-click="verifyjaf(jaf)">Verify</button>
                <div ng-show="show[$index]" ng-model="content[$index]"></div>
            </li>
        </ul>
    </div>

    <div ng-show="jafList.length == 0" style="text-align: center;">
        No pending requests
    </div>

    <div ng-show="jafList.length > 0" style="text-align: center;">
        <button class="btn btn-success" ng-click="sendFeeVerifications()">Verify</button>
    </div>

</div>