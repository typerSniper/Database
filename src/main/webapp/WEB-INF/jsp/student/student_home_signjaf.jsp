<div ng-controller="StudentSignJafController" >
	<div  align="center" >
		<table style="width:70%" class="table table-striped">
			<tr>
				<td style="text-align:left;">
					Select Resume
				</td>
				<td style="text-align:left;">
					<input ng-model="resume_type" type="radio" name="resume" value="0" checked> Tech(1 page)
 			 		<input ng-model="resume_type" type="radio" name="resume" value="1"> Tech(2 page)
  					<input ng-model="resume_type" type="radio" name="resume" value="2"> Non-tech
  				</td>

			</tr>
			<tr >
				<td style="text-align:left;">
					Sop(If asked in jaf)
				</td>
				<td style="text-align:left;">
					<input type="file" accept="application/pdf" id="res" name="file"/>

  				</td>

			</tr>
		</table>
		<button ng-click="signjaf()" class="btn btn-success">Sign</button>
	</div>
</div>
