<div ng-controller="CompanyExistingJafsController" ng-init="getExistingJaf()">
	 <div class="jumbotron" style="text-align: center;"><h2>All Jafs </h2></div>

    <div ng-show="jafList.length > 0" style="margin: 50px" class="list-group">
        <table id="example" style="width:70%" class="table table-striped" align="center">
            <tr ng-repeat="jaf in jafList track by $index">
                <td>
                    <button type="button" class="list-group-item" >{{jaf.cid}}</button>
                </td>
                <td>
                	<button type="button" class="list-group-item" >{{jaf.jid}}</button>
                </td>
                <td>
                    <button ng-if="stage==7" class="btn btn-success" ng-click="final_selections([$index])">Final Selections</button>
                    <button ng-if="stage==6" class="btn btn-success" ng-click="publish_results([$index])">Publish Results</button>
                </td>
            </tr>
        </table>               
    </div>

    <div ng-show="jafList.length == 0" style="text-align: center;">
        No pending jaf
    </div>

</div>