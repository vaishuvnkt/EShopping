<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class = "container">

	<!-- adding breadcrumb -->
	<div class = "row">
	
		<div class = "col-xs-12">
		
			<ol class = "breadcrumb">
			
				<li><a href = "${contextRoot}/home">Home</a></li>
				<li><a href = "${contextRoot}/show/all/products">Products</a></li>
				<li class = "active">${product.name}</li>
			
			</ol>
		
		</div>
	
	</div>


	<div class = "row">
	
		<!-- to display product image -->
		<div class = "col-xs-12 col-sm-4">
		
			<img src="${images}/${product.code}.jpg" class = "img img-responsive" />
		
		</div>
		
		<!-- to display product description -->
		<div class = "col-xs-12 col-sm-8">
		
			<h3>${product.name}</h3>
			<hr/>
			
			<p> ${product.description}</p>
			<hr />
			
			<h4>PRICE : <strong> &#8377; ${product.unitPrice} /-</strong></h4>
			<hr />
				
			<security:authorize access = "hasAuthority('USER')">

				<c:choose>

					<c:when test="${product.quantity < 1}">

						<h1 style="color: red">
							<span> OUT OF STOCK </span>
						</h1>
						<a href="javascript:void(0)" class="btn btn-success disabled">
							<span class="glyphicon glyphicon-shopping-cart"> </span> Add To
							Cart
						</a>

					</c:when>

					<c:otherwise>

						<h6>Stock availability : ${product.quantity}</h6>

						<a href="${contextRoot}/cart/add/${product.id}/product"
							class="btn btn-success"> <span
							class="glyphicon glyphicon-shopping-cart"> </span> Add To Cart
						</a>

					</c:otherwise>

				</c:choose>

			</security:authorize>

			<security:authorize access ="hasAuthority('ADMIN')">
			
				<a href="${contextRoot}/manage/${product.id}/product"
					class="btn btn-info"> <span
					class="glyphicon glyphicon-pencil"> </span> Edit Item details 
				</a>
			
			</security:authorize>
			
			<a href = "${contextRoot}/show/all/products" class = "btn btn-primary"> Back </a>
		
		</div>
		
	</div>

</div>