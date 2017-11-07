<div ng-controller="StudentVerificationController">
	<div ng-if="comments !=2" align="center">
		<h2>
			0 Comments<br>
			Your Resume is being verified.<br>
			Contanct your IC for more details.
		</h2> 
	</div>
    <div ng-if="comments == 2"  align="center">

        <table style="width:50%" class="table table-striped">
        	<tr ng-if="type === 'res0'" style="background-color:white">
            			<td >
            			Comments will be shown here<br>
            			</td> 
            	
            			<td>
            				<input type="text" placeholder="Wite a Reply ...">
            			</td>
            			<td>
            				<button class="btn btn-success">Reply</button>
            			</td>
            			<td>
            			</td>
            </tr>
            <tr ng-if="type === 'res0'">
            		
            	
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
            <tr ng-if="type === 'res1'" style="background-color:white">
            			<td >
            			Comments will be shown here<br>
            			</td> 
            	
            			<td>
            				<input type="text" placeholder="Wite a Reply ...">
            			</td>
            			<td>
            				<button class="btn btn-success">Reply</button>
            			</td>
            			<td>
            			</td>
            </tr>
            <tr ng-if="type == res1">
            	
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
            <tr ng-if="type === 'res2'" style="background-color:white">
            			<td >
            			Comments will be shown here<br>
            			</td> 
            	
            			<td>
            				<input type="text" placeholder="Wite a Reply ...">
            			</td>
            			<td>
            				<button class="btn btn-success">Reply</button>
            			</td>
            			<td>
            			</td>
            </tr>
            <tr ng-if="type == res2">
            	
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
            <tr ng-if="type === 'res3'" style="background-color:white">
            			<td >
            			Comments will be shown here<br>
            			</td> 
            	
            			<td>
            				<input type="text" placeholder="Wite a Reply ...">
            			</td>
            			<td>
            				<button class="btn btn-success">Reply</button>
            			</td>
            			<td>
            			</td>
            </tr>
            <tr ng-if="type == res3">
            	
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
        

            <tr >
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
    </div>
   
    
</div>

<progress-bar0 active='4'></progress-bar0>

 