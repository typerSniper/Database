<div ng-controller="ICApproveJafController" ng-init="getjafRequests()">
    <div class="jumbotron" style="text-align: center;"><h2>Pending Jaf Requests</h2></div>
    <div ng-show="jafList.length > 0" style="margin: 50px" class="list-group">
        <table class="table table-stripped">
            <tr ng-repeat="jaf in jafList track by $index">
                <td>
                    <table>
                        <tr>
                            <th>Company</th>
                            <th>JAF</th>
                        </tr>
                        <tr>
                            <td>
                                <button type="button" class="list-group-item" ng-click="selectJaf(jaf)" ng-class="{'active': isSelected(jaf)}">{{jaf.cid}}</button>
                            </td>
                            <td>
                                <button type="button" class="list-group-item" ng-click="selectJaf(jaf)" ng-class="{'active': isSelected(jaf)}">{{jaf.jid}}</button>
                            </td>
                        </tr>
                        <tr>
                            <td><button class="btn btn-info" ng-click="viewjaf($index)">View</button></td>
                            <td><button class="btn btn-success" ng-click="verifyjaf($index)">Verify</button></td>
                            <td><button class="btn btn-danger" ng-click="declinejaf($index)">Decline</button></td>
                        </tr>
                        <tr>
                            <td><div ng-show="show[$index]" ng-model="content[$index]"></div></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>

    <div ng-show="jafList.length == 0" style="text-align: center;">
        No pending requests
    </div>
</div>