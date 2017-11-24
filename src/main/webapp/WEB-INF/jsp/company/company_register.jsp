<div ng-controller="companyRegisterController">
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
                    <td><input ng-model="input.name" type="text" /></td>
                  </tr>
                  <tr>
                    <td>Email</td>
                    <td><input ng-model="input.email" type="email" /></td>
                    </tr>
                  <tr>
                    <td>Contact</td>
                    <td><input ng-model="input.contact" type="number" /></td>
                  </tr>
                  <tr>
                    <td>Person Of Contact</td>
                    <td><input ng-model="input.representative" type="text" /></td>
                  </tr>
                  <tr>
                    <td>Create Password</td>
                    <td><input ng-model="input.password" type="password" /></td>
                  </tr>
                  <tr>
                    <td>Re-enter Password</td>
                    <td><input ng-model="pass" type="password" /></td>
                  </tr>

                </table>
              <button type="submit" class="btn btn-success">Submit</button>

            </div>
        </form>
    </div>
    <div class="col-xs-4"></div>
</div>
