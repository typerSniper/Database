<div ng-controller="StudentResumeController">
    <div align="center" ng-hide="resumeDeadlinePassed">
        <table style="width:50%" class="table table-striped">
            <tr>
                <th>
                    Resume 0(1 page)

                </th>
                <td>
                    <input type="file" accept="application/pdf" id="res0" name="file"/>
                </td>
                <td>
                    <button ng-click="submitResume(0)" class="btn btn-success">Upload</button>
                </td>
            </tr>
            <tr>
                <th>
                    Resume 1(tech)
                </th>
               <td>
                    <input type="file" accept="application/pdf" id="res1" name="file"/>
                </td>
                <td>
                    <button ng-click="submitResume(1)" class="btn btn-success">Upload</button>
                </td>
            </tr>
            <tr>
                <th>
                    Resume 2(tech)
                </th>
                <td>
                    <input type="file" accept="application/pdf" id="res2" name="file"/>
                </td>
                <td>
                    <button ng-click="submitResume(2)" class="btn btn-success">Upload</button>
                </td>
                
            </tr>
            <tr>
                <th>
                    Resume 3(non-tech)
                </th>
               <td>
                    <input type="file" accept="application/pdf" id="res3" name="file"/>
                </td>
                <td>
                    <button ng-click="submitResume(3)" class="btn btn-success">Upload</button>
                </td>
                
            </tr>
        

            <tr>
                <th>
                    Resume Proofs Pdf
                </th>

                <td>
                    <input type="file" accept="application/pdf" id="ver" name="file"/>
                </td>
                <td>
                    <button ng-click="submitResumeVer()" class="btn btn-success">Upload</button>
                </td>
            </tr>
        </table>
    </div>
    <div align="center" class="jumbotron">
        <h3>Time to deadline</h3>
        <div class='time-to'>
            <span countdown='' date='December 10, 2017 12:00:00'>&nbsp;</span>
        </div>
    </div>
</div>

<progress-bar0 active='3'></progress-bar0>