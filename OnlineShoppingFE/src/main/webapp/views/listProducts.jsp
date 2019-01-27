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
						<ol class="breadcrumb">

							<!-- to show home in filter path -->
							<li><a href="${contextRoot}/home">Home</a></li>

							<li class="active">All products</li>
							<!-- all products tab should be active -->

						</ol>
					</c:if>

				</div>

			</div>

		</div>


	</div>


</div>