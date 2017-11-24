<div ng-controller="ICViewJafController" ng-init="viewjaf(selectedJafID)">

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
            <th>Company Deadline</th>
            <td>{{jafDetails.companyDeadline}}</td>
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
            <tr ng-repeat="elig in jafDetails.eligibilities track by $index">
                <td>{{elig.cpicutoff}}</td>
                <td>{{elig.programid}}</td>
                <td>{{elig.deptid}}</td>
            </tr>
        </table>
        <div align="center">
            <form ng-submit="verifyjaf()">
                <input type="date" ng-model="deadline_date" required/>Date
                <input type="time" ng-model="deadline_time" required/>Time

                <button class="btn btn-success" type="submit">Verify</button>
            </form>
            <button class="btn btn-danger" type="button" ng-click="declinejaf()">Decline</button>
        </div>
    
</div>