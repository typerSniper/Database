<div ng-controller="StudentSignedJafController" ng-init="pendingJafs()">
	<table>
		<tr ng-repeat="jaf in pendingjafs track by $index">
			<td>{{jaf.company}}</td>
			<td>{{jaf.jafName}}</td>
			<td>{{jaf.deadline}}</td>
			<td><button ng-click="getJafDetails(jaf)">Details</button></td>
		</tr>
	</table>
</div>
