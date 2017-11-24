<div ng-controller="ICPendingJafController" ng-init="getjafRequests()">

    <div class="jumbotron" style="text-align: center;"><h2>Pending Jaf Requests</h2></div>

    <div ng-show="jafList.length > 0" style="margin: 50px" class="list-group">
        
                <table style="width:70%" class="table table-striped" align="center">
                    <tr ng-repeat="jaf in studentList track by $index">
                        <td>
                            <button type="button" class="list-group-item" ng-click="selectJaf(jaf)" ng-class="{'active': isSelected(jaf)}">{{jaf.cid}}->{{jaf.jid}}</button>
                        </td>
                        <td>
                            <button class="btn btn-success" ng-click="publishjaf([$index])">Publish</button>
                        </td>
                    </tr>
                </table>                              
    </div>

    <div ng-show="jafList.length == 0" style="text-align: center;">
        No pending jafs to publish
    </div>

    

</div>