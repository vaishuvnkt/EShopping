<!-- DataTable Bootstrap Script -->
<script src="${js}/angular.js"></script>

<!-- DataTable Bootstrap Script -->
<script src="${js}/productController.js"></script>
<div class="container" data-ng-app="ShoppingApp"
	data-ng-controller="ProductController as pCtrl">

	<div class="row" data-ng-init="pCtrl.fetchProducts()">


		<div class="col-md-12">

			<div class="row carousel-holder">

				<div class="col-md-12">
					<div id="carousel-example-generic" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carousel-example-generic" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"></li>
							<li data-target="#carousel-example-generic" data-slide-to="3"></li>
							<li data-target="#carousel-example-generic" data-slide-to="4"></li>
							<li data-target="#carousel-example-generic" data-slide-to="5"></li>
							<li data-target="#carousel-example-generic" data-slide-to="6"></li>
						</ol>
						<div class="carousel-inner">
							<div class="item active">
								<a  href = "${contextRoot}/show/16/product"><img class="slide-image" src="${images}/maybelline.jpg"
									alt="MayBelline NewYork - Fit Me Foundation" ></a>
							</div>
							<div class="item">
							<a  href = "${contextRoot}/show/20/product">
								<img class="slide-image" src="${images}/huda_beauty.jpg"
									alt="Huda Beauty Obsessions - Mini Eye Shadow"></a>
							</div>
							<div class="item">
							<a href = "${contextRoot}/show/19/product"> 
								<img class="slide-image" src="${images}/mac.jpg"
									alt="MAC - Strobe Cream"></a>
							</div>
							<div class="item">
							<a href = "${contextRoot}/show/15/product">
								<img class="slide-image" src="${images}/lakme.jpg"
									alt="Lakme Primer + Matte Lipstick"></a>
							</div>
							<div class="item">
							<a href = "${contextRoot}/show/18/product">
								<img class="slide-image" src="${images}/loreal-clay-mask.jpg"
									alt="L'Oreal Pure clay mask"></a>
							</div>
							<div class="item">
							<a href = "${contextRoot}/show/17/product">
								<img class="slide-image" src="${images}/maybelline-matte.jpg"
									alt="Maybelline - Shine Compulsion"></a>
							</div>
							<div class="item">
								<img class="slide-image" src="${images}/MAC-matte.jpg"
									alt="MAC - Boost your lashes">
							</div>
						</div>
						<a class="left carousel-control" href="#carousel-example-generic"
							data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left"></span>
						</a> <a class="right carousel-control"
							href="#carousel-example-generic" data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right"></span>
						</a>
					</div>
				</div>

			</div>

			<div class="row">
				<div class="col-xs-12">
					<h3>Our Most Viewed Products</h3>
					<hr />
				</div>
			</div>

			<div class="row is-table-row">

				<div class="col-sm-4" data-ng-repeat="product in pCtrl.mvProducts">
					<div class="thumbnail">
						<img class="landImg" data-ng-src="${images}/{{product.code}}.jpg"
							alt="{{product.name}}" >
						<h5 class = "text-center">{{product.name}}</h5>
						<hr />
						<div class="caption">
							<h4 class="pull-right">&#8377; {{product.unitPrice}}</h4>
							
							<a data-ng-href="${contextRoot}/show/{{product.id}}/product"
								class="btn btn-primary pull-left">View</a>
						</div>
					</div>

				</div>

			</div>

			<div class="row">
				<div class="col-xs-12">
					<h3>Our Most Purchased Products</h3>
					<hr />
				</div>
			</div>
			<div class="row is-table-row">

				<div class="col-sm-4" data-ng-repeat="product in pCtrl.mpProducts">
					<div class="thumbnail">
						<img class="landImg" data-ng-src="${images}/{{product.code}}.jpg"
							alt="{{product.name}}" >
						<h5 class = "text-center">{{product.name}}</h5>
						<hr />
						<div class="caption">
							<h4 class="pull-right">&#8377; {{product.unitPrice}}</h4>
							
							<a data-ng-href="${contextRoot}/show/{{product.id}}/product"
								class="btn btn-primary pull-left">View</a>
						</div>
					</div>
				</div>

			</div>


			<div class="col-sm-4 col-lg-4 col-md-4">
				<h4>Checkout more products!</h4>
				<hr />
				<a class="btn btn-primary" href="${contextRoot}/show/all/products">More
					Products</a>
			</div>

		</div>

	</div>

</div>
<!-- /.container -->
