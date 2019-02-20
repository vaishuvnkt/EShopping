<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class = "container">

	<div class = "row">

		<c:if test = "${not empty message}">
		
			<div class = "col-xs-12">
			
				<div class = "alert alert-success alert-dismissible">
				
					<button type = "button" class = "close" data-dismiss = "alert">&times;</button> <!-- close symbol -->
					
					${message}
				
				</div>
			
			</div>
		
		</c:if>
		<!-- to make the form appear in center -->
		<div class = "col-md-offset-2 col-md-8">

			<div class = "panel panel-primary">

				<div class = "panel-heading">

					<h4>Product Manager</h4>

				</div>

				<div class = "panel-body">

					<!-- FORM ELEMENTS -->
					<sf:form class = "form-horizontal" modelAttribute = "product" action = "${contextRoot}/manage/products" method = "POST"> <!-- modelAttribute name should be same as given in ManagementController given before object -->

						<div class = "form-group">

							<label class =  "control-label col-md-4" for  = "name">Enter item name</label>

							<div class = "col-md-8">

								<sf:input type = "text" path = "name" id = "name" placeholder = "Item Name" class = "form-control" />
								<em class = "help-block"> Please enter VALID item name</em>
								
							</div>
							
						</div>
						
						<div class = "form-group">

							<label class = "control-label col-md-4" for = "brand">Enter brand name of the item </label>

							<div class = "col-md-8">

								<sf:input type = "text" path = "brand" id = "brand" placeholder = "Brand" class = "form-control" />
								<em class = "help-block"> Please enter VALID brand</em>
								
							</div>
							
						</div>
						
						<div class = "form-group">

							<label class =  "control-label col-md-4" for  = "desc">Enter Description</label>

							<div class = "col-md-8">

								<sf:textarea path = "description" id = "desc" rows = "4" placeholder = "Write description" ></sf:textarea>
								<em class = "help-block"> Please enter VALID description</em>
								
							</div>
						
						</div>
						
						<div class = "form-group">

							<label class =  "control-label col-md-4" for  = "cost" >Enter cost of the item </label>

							<div class = "col-md-8">

								<sf:input type = "number" path = "unitPrice" id = "cost" placeholder = "Item cost in &#8377;" class = "form-control" />
								<em class = "help-block"> Please enter VALID cost of the item</em>
								
							</div>
						
						</div>
						
						<div class = "form-group">

							<label class =  "control-label col-md-4" for  = "quantity">Enter total number of stocks available</label>

							<div class = "col-md-8">

								<sf:input type = "number" path = "quantity" id = "quantity" placeholder = "Stocks available" class = "form-control" />
								<em class = "help-block"> Please enter VALID item name</em>
								
							</div>
						
						</div>

						<div class = "form-group">

							<label class =  "control-label col-md-4" for  = "categoryID">Select category</label>

							<div class = "col-md-8">

								<sf:select class = "form-control" path= "categoryID" id = "categoryID"  
								
								items = "${Categories}"
								itemLabel = "name"
								itemValue = "id"
								
								/>
								<em class = "help-block"> Please select VALID category</em>
							
							</div>  
						
						</div> 
						<div class = "form-group">

							<div class = "col-md-offset-4 col-md-8">

								<input type = "submit" name = "submit" id = "submit" value = "Submit" class = "btn btn-success" />
								
								<!-- these fields cannot be updated by product manager so hide these fields -->
								<sf:hidden path = "id"/>
								<sf:hidden path = "code"/>
								<sf:hidden path = "active"/>
								<sf:hidden path = "supplierID"/>
								<sf:hidden path = "purchases"/>
								<sf:hidden path = "views"/>
													
							</div>
							
						</div>						

					</sf:form>

				</div>

			</div>
			

		</div>

	</div>

</div>

