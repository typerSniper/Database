<div ng-controller="StudentHomeController">
	<form ng-submit="submit()">
		<div align="center">
		<table style="width:70%" class="table table-striped">
		  <tr>
		    <td>Name</td>
		    <td><input ng-model="input.name" type="text" /></td>
		  </tr>
		  <tr>
		    <td>Date Of Birth</td>
		    <td><input ng-model="input.dob" type="date" /></td>
		  </tr>
		  <tr>
		    <td>Sex</td>
		    <td>
		    	<input ng-model="input.sex" type="radio" name="gender" value="male" checked> Male<br>
 			 	<input ng-model="input.sex" type="radio" name="gender" value="female"> Female<br>
  				<input ng-model="input.sex" type="radio" name="gender" value="other"> Other<br><br>
  			</td>
  		  </tr>
  			<tr>
		    <td>Category</td>
		    <td><select ng-model="input.category"
		    	ng-init = "input.category=categories[0]"
		    	ng-options="x for x in categories">
		    </select>
		    </td>
		  </tr>
		  <tr>
		    <td>Nationality</td>
		    <td><input ng-model="input.nationality" type="text" /></td>
		  </tr>
		  <tr>
		    <td>Institute Email</td>
		    <td><input ng-model="input.univemail" type="email" /></td>
		  </tr>
		  <tr>
		    <td>Alernate Email</td>
		    <td><input ng-model="input.peremail" type="email" /></td>
		  </tr>
		  <tr>
		    <td>Hostel Address</td>
		    <td><input ng-model="input.hosteladdress" type="text" /></td>
		  </tr>
		  <tr>
		    <td>Contact</td>
		    <td><input ng-model="input.contact1" type="number" /></td>
		  </tr>
		  <tr>
		    <td>Alternate Contact</td>
		    <td><input ng-model="input.contact2" type="number" /></td>
		  </tr>
		  <tr>
		    <td>Home Contact</td>
		    <td><input ng-model="input.homecontact" type="number" /></td>
		  </tr>
		  <tr>
		    <td><b>Permanent Address Details</b></td>
		  </tr>
		  <tr>
		    <td>Locality</td>
		    <td><input ng-model="input.locality" type="text" /></td>
		  </tr>
		  <tr>
		    <td>City</td>
		    <td><input ng-model="input.city" type="text" /></td>
		  </tr>
		  <tr>
		    <td>Pin</td>
		    <td><input ng-model="input.pin" type="number" /></td>
		  </tr>
		  <tr>
		    <td>State</td>
		    <td><input ng-model="input.state" type="text" /></td>
		  </tr>
		  <tr>
		    <td>Country</td>
		    <td><input ng-model="input.country" type="text" /></td>
		  </tr>
		</table>

		
		<button type="submit" class="btn btn-success">Submit</button>
	</div>
	</form>
</div>
<br><br><br><br>
<progress-bar0 active='1'></progress-bar0>
