<div ng-controller="ICCompanyController" ng-init="getcompanyRequests()">

    <div class="jumbotron" style="text-align: center;"><h2>Pending Company Registration Requests</h2></div>

    <div ng-show="companyList.length > 0" style="margin: 50px" class="list-group">
        <table style="width:100%" class="table table-striped" align="center">
            <tr ng-repeat="jaf in studentList track by $index">
                <td>
                    {{company.name}}
                </td>
                <td>
                    {{company.location}}
                </td>
                <td>
                    <button ng-click="register_company($index)" class="btn btn-success">Register</button>
                </td>
            </tr>
        </table>       
    </div>

    <div ng-show="companyList.length == 0" style="text-align: center;">
        No pending requests
    </div>

   

</div>


