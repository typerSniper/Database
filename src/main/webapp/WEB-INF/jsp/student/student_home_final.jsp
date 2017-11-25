<div ng-controller="StudentFinalController" ng-init="getJafs()">
	<table id = "example" class = "table table-bordered" >
		<tr ng-repeat="jaf in jafList track by $index">
			<td>{{jaf.company}}</td>
			<td>{{jaf.jname}}</td>			
				<td>{{jaf.jafDeadline}}</td>
				<td>{{jaf.eligible}}</td>
			<td><button ng-click="getJafDetails(jaf)">Details</button></td>
			<td><button ng-click="getsignJaf(jaf)">Sign JAF</button></td>

		</tr>
	</table>
</div>
