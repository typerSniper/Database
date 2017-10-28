<div ng-controller="StudentFeeController" style="width: 100%;
  text-align: center; height: 100%">
    <div style="margin: 0 auto; width: 50%; height: 50%; align-content: center;">
        <button ng-click="submit()" class="btn btn-success" ng-disabled="stage === 3">Pay Fees</button>
        <p ng-show="stage === 3">Waiting for Fee Verification</p>
    </div>
</div>
<progress-bar0 active='2'></progress-bar0>
