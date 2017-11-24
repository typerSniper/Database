<div ng-controller="companyHomeController">
    <!-- <a href="/coordinator/fee">Pending Fee Requests</a>
    <button ng-click="getResume('student4')">Get Resume</button> -->
    <div ng-show="stage == 1">
    <a href="recruiter/newjaf">Create New Jaf</a>
        <a href="recruiter/existingjafs">See Existing Jafs</a>
    </div>
    <div ng-show="stage == 0">
        Waiting for registration confirmation from PT Cell
    </div>

</div>