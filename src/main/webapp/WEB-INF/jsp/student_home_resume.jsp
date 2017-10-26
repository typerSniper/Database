<div ng-controller="StudentResumeController">
    <div align="center">
        <table style="width:50%" class="table table-striped">
            <tr>
                <th>
                    Resume 0(1 page)

                </th>
                <td>
                    <input type="file" accept="application/pdf" id="file" name="file"/>
                </td>
                <td>
                    <button ng-click="submitResume()" class="btn btn-success">Upload</button>
                </td>
            </tr>
            <tr>
                <th>
                    Resume 1(tech)
                </th>
               <td>
                    <input type="file" accept="application/pdf" id="file" name="file"/>
                </td>
                <td>
                    <button ng-click="submitResume()" class="btn btn-success">Upload</button>
                </td>
            </tr>
            <tr>
                <th>
                    Resume 2(tech)
                </th>
                <td>
                    <input type="file" accept="application/pdf" id="file" name="file"/>
                </td>
                <td>
                    <button ng-click="submitResume()" class="btn btn-success">Upload</button>
                </td>
                
            </tr>
            <tr>
                <th>
                    Resume 3(non-tech)
                </th>
               <td>
                    <input type="file" accept="application/pdf" id="file" name="file"/>
                </td>
                <td>
                    <button ng-click="submitResume()" class="btn btn-success">Upload</button>
                </td>
                
            </tr>
        </table>
    </div>
    <div align="center" class="jumbotron">
        <h3>Time to deadline</h3>
        <div class='time-to'>
            <span countdown='' date='January 1, 2018 12:00:00'>&nbsp;</span>
        </div>
    </div>
</div>

<progress-bar0 active='3'></progress-bar0>