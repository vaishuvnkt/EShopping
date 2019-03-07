<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- header -->
<%@include file="../shared/flows-header.jsp"%>
			
<div class = "container">


<div class = "row">

<div class = col-sm-6>

	<div class = "panel panel-primary">
	
		<div class = "panel-heading">
		
			<h4>Personal Details</h4>
		
		</div>
		
		<div class = "panel-body">
		
			<!-- to display personal details -->
					<div class="text-center">
						<h3>Name : <strong>${registerModel.user.firstname} ${registerModel.user.lastname}</strong></h3>
						<h4>Email : <strong>${registerModel.user.email}</strong></h4>
						<h4>Contact : <strong>${registerModel.user.contactNumber}</strong></h4>
						<h4>Role : <strong>${registerModel.user.role}</strong></h4>	
					</div>
					
					<!-- anchor tag to move to personal details page to edit -->
					<p>
						<a href = "${flowExecutionUrl}&_eventId_personal" class = "btn btn-primary">Edit</a>		
					</p>
		</div>
		
	</div>

</div>


<div class = col-sm-6>

	<div class = "panel panel-primary">
	
		<div class = "panel-heading">
		
			<h4>Billing Address</h4>
		
		</div>
		
		<div class = "panel-body">
		
			<!-- to display billing address -->
					<div class="text-center">
						<p>${registerModel.billing.addressLineOne}, </p>
						<p>${registerModel.billing.addressLineTwo}, </p>
						<p>${registerModel.billing.city} -  ${registerModel.billing.postalCode}, </p>
						<p>${registerModel.billing.state}</p>
						<p>${registerModel.billing.country}</p>
					</div>		

			<!-- anchor tag to move to billing address page to edit -->
			<p>
				<a href = "${flowExecutionUrl}&_eventId_billing" class = "btn btn-primary">Edit</a>
			</p>
					
		</div>
	
	</div>

</div>

<div class = "row">

	<div class = "col-sm-4 col-sm-offset-4">
	
		<div class = "text-center">
		
			<!-- anchor tag to move to success page -->
			<a href = "${flowExecutionUrl}&_eventId_submit" class = "btn btn-primary">Confirm Submission</a>
		
		</div>
	
	</div>

</div>

</div>

</div>

<!-- footer -->
<%@include file="../shared/flows-footer.jsp"%>
