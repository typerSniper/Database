<div ng-controller="StudentHomeController">
	<form ng-submit="submit()">
		<div align="center">
		<table style="width:70%" class="table table-striped">
		  <tr>
		    <td>Date Of Birth</td>
		    <td><input ng-model="date" type="date" required/></td>
		    <td><b>Permanent Address Details</b></td>
		    <td></td>
		  </tr>
		  <tr>
		    <td>Sex</td>
		    <td>
		    	<input ng-model="input.sex" type="radio" name="gender" value="male" checked> Male
 			 	<input ng-model="input.sex" type="radio" name="gender" value="female"> Female
  				<input ng-model="input.sex" type="radio" name="gender" value="other"> Other<br>
  			</td>
  			<td>Locality</td>
		    <td><input ng-model="input.homeaddress.locality" type="text" /></td>
  		  </tr>
  			<tr>
		    <td>Category</td>
		    <td><select ng-model="input.category"
		    	ng-init = "input.category=categories[0]"
		    	ng-options="x for x in categories">
		    </select>
		    </td>
		     <td>City</td>
		    <td><input ng-model="input.homeaddress.city" type="text" /></td>
		  </tr>
		  <tr>
		    <td>Nationality</td>
		    <td><input ng-model="input.nationality" type="text" /></td>
		    <td>Pin</td>
		    <td><input ng-model="input.homeaddress.pin" type="number" /></td>
		  </tr>
		  <tr>
		    <td>Institute Email</td>
		    <td><input ng-model="input.univemail" type="email" /></td>
		     <td>State</td>
		    <td><input ng-model="input.homeaddress.state" type="text" /></td>
		  </tr>
		  <tr>
		    <td>Alernate Email</td>
		    <td><input ng-model="input.peremail" type="email" /></td>
		    <td>Country</td>
		    <td><input ng-model="input.homeaddress.country" type="text" /></td>
		  </tr>
		  <tr>
		    <td>Hostel Address</td>
		    <td><input ng-model="input.hosteladdress" type="text" /></td>
		    <td>Home Contact</td>
		    <td><input ng-model="input.homeaddress.homecontact" type="number" /></td>
		  </tr>
		  <tr>
		    <td>Contact</td>
		    <td><input ng-model="input.contact1" type="number" /></td>
		    <td>Alternate Contact</td>
		    <td><input ng-model="input.contact2" type="number" /></td>

		</table>

		<table style="width:70%" class="table table-striped">

		  <tr>
		    <td><b>Examination</td>
		    <td><b>University</td>
		    <td><b>Institute</td>
		    <td><b>Year</td>
		    <td><b>CPI/%</td>
		  </tr>
		  <tr>
		    <td>Intermediate/+2</td>
		    <td><input ng-model="input.detail12th.university" type="text" /></td>
		   	<td><input ng-model="input.detail12th.institute" type="text" /></td>
		   	<td><input ng-model="input.detail12th.year" type="number" /></td>
		   	<td><input ng-model="input.detail12th.cpi" max="100" type="number" /></td>
		</tr>
		 <tr>
		    <td>Matriculation</td>
		    <td><input ng-model="input.detail10th.university" type="text" /></td>
		   	<td><input ng-model="input.detail10th.institute" type="text" /></td>
		   	<td><input ng-model="input.detail10th.year" type="number" /></td>
		   	<td><input ng-model="input.detail10th.cpi" max="100" type="number" /></td>
		</tr>
		 <tr>
		    <td>Others</td>
		    <td><input ng-model="input.others.university" type="text" /></td>
		   	<td><input ng-model="input.others.institute" type="text" /></td>
		   	<td><input ng-model="input.others.year" type="number" /></td>
		   	<td><input ng-model="input.others.cpi" max="100" type="number" /></td>
		</tr>
  			
		   
		  
		 
		</table>

		
		<button type="submit" class="btn btn-success">Submit</button>
	</div>
	</form>
</div>
<br><br><br><br>
<progress-bar0 active='1'></progress-bar0>
