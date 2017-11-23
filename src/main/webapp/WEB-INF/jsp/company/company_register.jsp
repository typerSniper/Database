<div ng-controller="companyLoginController">
    <div class="col-xs-4"></div>
    <div class="col-xs-4 well well-lg">
        <div style="text-align:center;">
            <h3><strong>Placement Cell</strong></h3>
            <h3><strong>Recruiter Register</strong></h3>
        </div>
        <hr>
        <form ng-submit="submit()" class="form" >
            <div align="center">
                <table style="width:70%" class="table table-striped">
                  
                  <tr>
                    <td>Company Name </td>
                    <td><input ng-model="input.nationality" type="text" /></td>
                   
                  </tr>
                  <tr>
                    <td>Institute Email</td>
                    <td><input ng-model="input.univemail" type="email" /></td>
                     
                  </tr>
                  <tr>
                    <td>Alernate Email</td>
                    <td><input ng-model="input.peremail" type="email" /></td>
                    
                  <tr>
                    <td>Hostel Address</td>
                    <td><input ng-model="input.hosteladdress" type="text" /></td>
                   
                  </tr>
                  <tr>
                    <td>Contact</td>
                    <td><input ng-model="input.contact1" type="number" /></td>
                    

                </table>
            </div>
        </form>
    </div>
    <div class="col-xs-4"></div>
</div>
