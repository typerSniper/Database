<ol class="progress" data-steps="5">
    <li ng-class="{'active': currentActive==1, 'done': currentActive>1}">
        <span class="name">Fill Details</span>
        <span class="step"><span>1</span></span>
    </li>
    <li ng-class="{'active': currentActive==2, 'done': currentActive>2}">
        <span class="name">Fee Payment</span>
        <span class="step"><span>2</span></span>
    </li>
    <li ng-class="{'active': currentActive==3, 'done': currentActive>3}">
        <span class="name">Resume Upload</span>
        <span class="step"><span>3</span></span>
    </li>
    <li ng-class="{'active': currentActive==4, 'done': currentActive>4}">
        <span class="name">Resume Verification</span>
        <span class="step"><span>4</span></span>
    </li>
    <li ng-class="{'active': currentActive==5, 'done': currentActive>5}">
        <span class="name">Done</span>
        <span class="step"><span>4</span></span>
    </li>
</ol>