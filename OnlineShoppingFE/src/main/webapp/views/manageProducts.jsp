<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">

	<div class="row">

		<c:if test="${not empty message}">

			<div class="col-xs-12">

				<div class="alert alert-success alert-dismissible">

					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<!-- close symbol -->

					${message}

				</div>

			</div>

		</c:if>
		<!-- to make the form appear in center -->
		<div class="col-md-offset-2 col-md-8">

			<div class="panel panel-primary">

				<div class="panel-heading">

					<h4>Product Manager</h4>

				</div>

				<div class="panel-body">

					<!-- FORM ELEMENTS -->
					<sf:form class="form-horizontal" modelAttribute="product"
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data">
						<!-- modelAttribute name should be same as given in ManagementController given before object -->

						<div class="form-group">

							<label class="control-label col-md-4" for="name">Enter
								item name</label>

							<div class="col-md-8">

								<sf:input type="text" path="name" id="name"
									placeholder="Item Name" class="form-control" />

							</div>

						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="brand">Enter
								brand name of the item </label>

							<div class="col-md-8">

								<sf:input type="text" path="brand" id="brand"
									placeholder="Brand" class="form-control" />

							</div>

						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="desc">Enter
								Description</label>

							<div class="col-md-8">

								<sf:textarea path="description" id="desc" rows="4" cols=""
									placeholder="Write description"></sf:textarea>

							</div>

						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="cost">Enter
								cost of the item </label>

							<div class="col-md-8">

								<sf:input type="number" path="unitPrice" id="cost"
									placeholder="Item cost in &#8377;" class="form-control" />
								<sf:errors path="unitPrice" cssClass="help-block" element="em" />

							</div>

						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="quantity">Enter
								total number of stocks available</label>

							<div class="col-md-8">

								<sf:input type="number" path="quantity" id="quantity"
									placeholder="Stocks available" class="form-control" />

							</div>

						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="file">Upload a
								file</label>

							<div class="col-md-8">

								<sf:input type="file" path="file" id="file" class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em" />

							</div>

						</div>


						<div class="form-group">

							<label class="control-label col-md-4" for="categoryID">Select
								category</label>

							<div class="col-md-8">

								<sf:select class="form-control" path="categoryID"
									id="categoryID" items="${Categories}" itemLabel="name"
									itemValue="id" />

								<c:if test="${product.id == 0}">

									<div class="text-right">
										<br />
										<button type="button" data-toggle="modal"
											data-target="#myCategoryModal" class="btn btn-primary btn-xs">Add
											new Category</button>
									</div>

								</c:if>

							</div>

						</div>
						<div class="form-group">

							<div class="col-md-offset-4 col-md-8">

								<input type="submit" name="submit" id="submit" value="Submit"
									class="btn btn-primary" />

								<!-- these fields cannot be updated by product manager so hide these fields -->
								<sf:hidden path="id" />
								<sf:hidden path="code" />
								<sf:hidden path="active" />
								<sf:hidden path="supplierID" />
								<sf:hidden path="purchases" />
								<sf:hidden path="views" />

							</div>

						</div>

					</sf:form>

				</div>

			</div>


		</div>

	</div>

	<div class="row">

		<div class="col-xs-12">

			<h3>Available Items</h3>
			<hr />

		</div>

		<div class="col-xs-12">

			<div class="container-fluid">

				<div class="table-responsive">

					<!-- Product table for admin -->
					<table id="adminProductsTable"
						class="table table-striped table-bordered">

						<thead>

							<tr>

								<th>Id</th>
								<th>&#160;</th>
								<th>Name</th>
								<th>Brand</th>
								<th>Quantity</th>
								<th>Cost</th>
								<th>Active</th>
								<th>Edit</th>

							</tr>

						</thead>

						<tfoot>

							<tr>

								<th>Id</th>
								<th>&#160;</th>
								<th>Name</th>
								<th>brand</th>
								<th>Quantity</th>
								<th>Cost</th>
								<th>Active</th>
								<th>Edit</th>

							</tr>

						</tfoot>

					</table>

				</div>

			</div>

		</div>

	</div>

	<div class="modal fade" id="myCategoryModal" role="dialog"
		tabindex="-1">

		<div class="modal-dialog" role="document">

			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title">New Category</h4>
				</div>

				<div class="modal-body">

					<sf:form id="categoryForm" modelAttribute="category"
						action="${contextRoot}/manage/category" method="POST"
						class="form-horizontal">
						<div class="form-group">

							<label for="cat_name" class="control-label col-md-4">Category
								Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="cat_name"
									class="form-control" />
							</div>

						</div>

						<div class="form-group">

							<label for="cat_Description" class="control-label col-md-4">Category
								Description</label>
							<div class="col-md-8">
								<sf:textarea cols="" rows="5" path="Description"
									id="cat_Description" class="form-control" />
							</div>

						</div>

						<div class="form-group">

							<div class="col-md-offset-4 col-md-8">
								<input type="submit" value="Add" class="btn btn-primary" />
							</div>

						</div>

					</sf:form>
				</div>

			</div>

		</div>

	</div>

</div>

