<div ng-controller="CompanyExistingJafsController">
	 <div class="jumbotron" style="text-align: center;"><h2>Pending Jaf </h2></div>

    <div ng-show="jafList.length > 0" style="margin: 50px" class="list-group">
        <table id="example" style="width:70%" class="table table-striped" align="center">
            <tr ng-repeat="jaf in studentList track by $index">
                <td>
                    <button type="button" class="list-group-item" ng-click="selectJaf(jaf)" ng-class="{'active': isSelected(jaf)}">{{jaf.jname}}</button>
                </td>
                <td>
                	<button type="button" class="list-group-item" ng-click="selectJaf(jaf)" ng-class="{'active': isSelected(jaf)}">{{jaf.jid}}</button>
                </td>
                <td>
                    <button ng-if="stage==" class="btn btn-success" ng-click="student_interview([$index])">Publish</button>
                </td>
            </tr>
        </table>               
    </div>

    <div ng-show="jafList.length == 0" style="text-align: center;">
        No pending jaf
    </div>

</div>