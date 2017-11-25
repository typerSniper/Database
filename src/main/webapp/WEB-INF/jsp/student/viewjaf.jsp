<div ng-controller="StudentViewJafController" ng-init="viewjaf(selectedJaf)">

        <table class="table table-striped">
          <tr>
            <th>Job Name</th>
            <td>{{jafDetails.jname}}</td>
          </tr>
          <tr>
            <th>Salary</th>
            <td>
                {{jafDetails.salary}}
            </td>
          </tr>
          <tr>
            <th>Job Location</th>
            <td>{{jafDetails.location}}</td>
          </tr>
         <tr>
            <th>JAF Deadline</th>
            <td>{{jafDetails.jafDeadline}}</td>
        </tr></table>

        <table class="table table-striped">
        <tr>
            <th>Description</th>
         </tr>
         <tr><th>{{jafDetails.description}}</th></tr>
        </table>
        <hr>

        <h3>Eligibility Rules</h3>
        <table class="table table-striped">
            <tr>
                <th>CPI</th>
                <th>Department</th>
                <th>PROGRAM</th>
            </tr>
            <tr ng-repeat="elig in eligibilities track by $index">
                <td>{{elig.cpicutoff}}</td>
                <td>{{elig.programid}}</td>
                <td>{{elig.deptid}}</td>
            </tr>
        </table>
        <div align="center">
            <table class="table table-striped">
                <tr>
                    <button  class="btn btn-info" ng-show="!setEligible" ng-click="back()">Go Back</button>
                    <td ng-hide="jaf.isSigned"><button ng-disabled="!jaf.eligible" ng-click="signJaf(jaf)" class="btn btn-success">Sign JAF</button></td>
                    <td ng-show="jaf.isSigned"><button ng-disabled="!jaf.eligible" ng-click="signJaf(jaf)"  class="btn btn-warning" >UnSign JAF</button></td>
                </tr>
            </table>

 
        </div>
    
</div>