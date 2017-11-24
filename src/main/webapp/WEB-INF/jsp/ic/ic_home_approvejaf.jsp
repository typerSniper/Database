<div ng-controller="ICApproveJafController" ng-init="getjafRequests()">

    <div class="jumbotron" style="text-align: center;"><h2>Pending Jaf Requests</h2></div>

    <div ng-show="jafList.length > 0" style="margin: 50px" class="list-group">
        <table class="table table-stripped">
            <tr ng-repeat="jaf in jafList track by $index">
                <td>
                    <button type="button" class="list-group-item" ng-click="selectJaf(jaf)" ng-class="{'active': isSelected(jaf)}">{{jaf.cid}}->{{jaf.jid}}</button>
                </td>
                <td><button class="btn btn-info" ng-click="viewjaf($index)">View</button></td>
                <td><button class="btn btn-success" ng-click="verifyjaf($index)">Verify</button></td>
                <td><button class="btn btn-danger" ng-click="declinejaf($index)">Decline</button></td>
                <td><div ng-show="show[$index]" ng-model="content[$index]"></div></td>
            </tr>
        </table>
    </div>

    <div ng-show="jafList.length == 0" style="text-align: center;">
        No pending requests
    </div>

    

</div>