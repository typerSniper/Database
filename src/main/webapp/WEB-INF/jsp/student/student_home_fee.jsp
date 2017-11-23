<div ng-controller="StudentFeeController" style="text-align: center;" ng-init="stage == 3 ? getIC() : console.log('no');">
    <div style="margin: 0 auto; width: 50%; height: 50%; align-content: center;">
        <button ng-click="submit()" class="btn btn-success" ng-disabled="stage === 3">Pay Fees</button>
        <div ng-show="stage === 3" class="jumbotron">
            Waiting for fee verification <br>
            Please contact your IC, <b>{{ic.name}}: {{ic.number}}</b> for further information.
        </div>
    </div>
</div>
<progress-bar0 active='2'></progress-bar0>
