<div ng-controller="StudentFinalController" ng-init="getJafs()">
	<table>
		<tr ng-repeat="jaf in jafList track by $index">
			<td>{{jaf.company}}</td>
			<td>{{jaf.jafName}}</td>
			<td>{{jaf.deadline}}</td>
			<td><button ng-click="getJafDetails(jaf)">Details</button></td>
			<td><button ng-click="getsignJaf(jaf)">Sign JAF</button></td>

		</tr>
	</table>
</div>
