<div class="container">

	<div class="row">

		<!-- to display sidebars -->

		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>

		<!-- to display products -->
		<div class="col-md-9">
			<!-- 3+9=12 count to display grids in bootstrap -->

			<!-- adding breadcrumb component -->
			<!-- eg: home/category/mobile in url -->

			<div class="row">

				<div class="col-lg-12">

					<!-- when user clicks single category -->
					<c:if test="${userClickCategoryProducts == true}">

						<script>
							window.categoryID = '${cat.id}';
						</script>

						<ol class="breadcrumb">

							<!-- to show home in filter path -->
							<li><a href="${contextRoot}/home">Home</a></li>

							<li class="active">Category</li>
							<!--  category tab should be active -->
							<!-- cat comes from PageController where we created an object for Category.java BE file-->

							<li class="active">${cat.name}</li>
							<!-- selected category name tab should be active -->

						</ol>
					</c:if>

					<!-- when user clicks all products -->
					<c:if test="${userClickAllProducts == true}">

						<script>
							window.categoryID = '';
						</script>

						<ol class="breadcrumb">

							<!-- to show home in filter path -->
							<li><a href="${contextRoot}/home">Home</a></li>

							<li class="active">All products</li>
							<!-- all products tab should be active -->

						</ol>
					</c:if>

				</div>

				<!-- ADDING ACTUAL COMPONENTS -->
				<div class="row">
					<div class="col-xs-12">

						<div class = "container-fluid">
						
							<div class="table-responsive">

								<table id="productListTable"
									class="table table-striped table-borderd">

									<!-- <thead>
								<tr>
									<th>ID</th>
									<th>NAME</th>
								</tr>
							</thead> -->

									<thead>

										<tr>

											<th></th>
											<th>Name</th>
											<th>Brand</th>
											<th>Price</th>
											<th>Stock Available</th>
											<th></th>

										</tr>

									</thead>

									<!-- body will be filled by json -->

									<tfoot>

										<tr>

											<th></th>
											<th>Name</th>
											<th>Brand</th>
											<th>Price</th>
											<th>Stock Available</th>
											<th></th>

										</tr>

									</tfoot>

								</table>


							</div>

						</div>

					</div>

				</div>

			</div>

		</div>

	</div>

</div>