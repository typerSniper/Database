<div ng-controller="ICApproveJafController" ng-init="getjafRequests()">
    <div class="jumbotron" style="text-align: center;"><h2>Pending Jaf Requests</h2></div>

    <div ng-show="jafList.length > 0" style="margin: 50px" class="list-group">
        <ul style="list-style-type:none">
            <li ng-repeat="jaf in jafList track by $index">
                <button type="button" class="list-group-item" ng-click="selectJaf(jaf)">{{jaf.cid}} {{jaf.jid}}</button>
            </li>
        </ul>
    </div>

    <div ng-show="jafList.length == 0" style="text-align: center;">
        No pending requests
    </div>
</div>