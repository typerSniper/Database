<div ng-controller="companyJafController">
	    <div class="jumbotron" style="text-align: center;"><h2>Create New Jaf</h2></div>

	<form ng-submit="submit()">
		<div align="center">
		<table style="width:70%" class="table table-striped">
		  <tr>
		    <td>Job Name</td>
		    <td><input ng-model="input.jname" type="text" required/></td>
		  </tr>
		  <tr>
		  	<td>Salary</td>
		    <td>
		    	<input ng-model="salnumber" type="number" required/>
		    	<select class="btn" ng-model="curr"
		    	ng-init = "curr=currlist[0]"
		    	ng-options="x for x in currlist">
		    </select>
		    </td>
		  </tr>
		  <tr>
		  	<td>Company Location</td>
		    <td><input ng-model="input.location" type="text" required/></td>
		  </tr>
		 <tr>
		 	<td> Deadline</td>
		 	<td><input ng-model="date" type="date" required/></td>
		</table>
		<table style="width:70%" class="table table-striped">
		<tr>
		 	<td>Description</td>
		 	<td><textarea ng-model="input.description" rows="10" cols="100">
			</textarea></td>
		 </tr>
		</table>
  		<div>

    <fieldset>
      <legend>Add Eligibility Rule</legend>
    </fieldset>
    
    <table style="width:70%" class="table table-striped">
    	<tr>
<td>
			CPI</td>   		
		    <td>Department</td>
    		 <td>PROGRAM</td><td>
    	</td>
    	</tr>
    	<tr ng-repeat="elig in input.eligibilities track by $index">
    		<td>
			<input ng-model="elig.cpicutoff" type="number" min="0" max="10"> </td>   		
		    <td><select class="btn" ng-model="elig.deptid"
		    	ng-init = "elig.deptid=deptid[0]"
		    	ng-options="x for x in deptid">
		    </select></td>
    		 <td><select class="btn" ng-model="elig.programid"
		    	ng-init = "elig.programid=programid[0]"
		    	ng-options="x for x in programid">
		    </select></td><td>
    		<button type="button" class="btn btn-danger" ng-click="delElig($index)">Delete</button>
    	</td>
    	</tr>
    </table>
    <button type="button" class="btn btn-warning" ng-click="addElig()" required>Add Eligibility</button>

</div>
		
		<button type="submit" class="btn btn-success">Submit</button>

		
	</div>
	</form>
</div>
