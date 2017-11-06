<div ng-controller="ICFeeController" ng-init="getFeeRequests()">
    <jumbotron>Pending Fee Requests</jumbotron>
    <table ng-show="studentList.length > 0" class="table table-striped">
        <tr ng-repeat="student in studentList track by student.username" >
            <td ng-click="selectStudent(student)" ng-class="{'student_selected': isSelected(student), 'student_unselected': !isSelected(student)}">{{student.username}}</td>
        </tr>
    </table>
    <div ng-show="studentList.length == 0">
        No pending requests
    </div>

    <button class="btn btn-success" ng-click="sendFeeVerifications()">Verify</button>
    <a href="/coordinator/home">Home</a>

</div>