<div class="container progress-container">
    <div class="row">
        <ul class="breadcrumb">
            <li ng-class="{'active': currentActive==1, 'completed': currentActive>1}">
                <a>Fill Details</a>
            </li>
            <li ng-class="{'active': currentActive==2, 'completed': currentActive>2}">
                <a>Fee Payment</a>
            </li>
            <li ng-class="{'active': currentActive==3, 'completed': currentActive>3}">
                <a>Resume Upload</a>
            </li>
            <li ng-class="{'active': currentActive==4, 'completed': currentActive>4}">
                <a>Resume Verification</a>
            </li>
            <li ng-class="{'active': currentActive==5, 'completed': currentActive>5}">
                <a>Done</a>
            </li>

        </ul>
    </div>
</div>