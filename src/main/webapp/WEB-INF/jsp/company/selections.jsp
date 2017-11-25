<div ng-controller="CompanySelectionsController" ng-init="getStudentList()">
     <div class="jumbotron" style="text-align: center;"><h2>All Jafs </h2></div>

    <div ng-show="studentList.length > 0" style="margin: 50px" class="list-group">
        <table id="example" style="width:70%" class="table table-striped" align="center">
            <tr ng-repeat="student in studentList track by $index">
                <td>
                    <button type="button" class="list-group-item">{{student.sid}}</button>
                </td>
                <td>
                    <button type="button" class="list-group-item">{{student.name}}</button>
                </td>
            </tr>
        </table>
        <button  class="btn btn-success" ng-click="back()">Go Back</button>               
    </div>

    <div ng-show="studentList.length == 0" style="text-align: center;">
        No Student Signed the Jaf
    </div>

</div>