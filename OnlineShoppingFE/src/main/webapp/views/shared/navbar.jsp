<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${contextRoot}/home">Gorgeous You</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li id="about"><a href="${contextRoot}/about">About</a></li>
				<li id="listProducts"><a
					href="${contextRoot}/show/all/products">ThePlaza</a></li>
				<li id="contact"><a href="${contextRoot}/contact">Contact</a></li>
				
				<c:if test = "${userClickHome == true}" >

				<li class="dropdown" id="a_category"><a
					href="javascript:void(0)"
					class="dropdown-toggle"
					id="dropdownMenu1" data-toggle="dropdown">Categories <span
						class="caret"></span>
				</a>

					<ul class="dropdown-menu">
						<li>
							<c:forEach items="${categories}" var="category">
									<a href="${contextRoot}/show/${category.id}/products"
										 id="a_${category.name}">${category.name}</a>
									
							</c:forEach>
						</li>
					</ul>

					</li>

				</c:if>	

				<security:authorize access="hasAuthority('ADMIN')">
					<li id="manageProducts"><a
						href="${contextRoot}/manage/products">Manage Items</a></li>
				</security:authorize>
			</ul>

			<ul class="nav navbar-nav navbar-right ">
				<security:authorize access="isAnonymous()">
					<li id="register"><a href="${contextRoot}/register"><span class="glyphicon glyphicon-user"></span> Sign
							Up</a></li>
					<li id="login"><a href="${contextRoot}/login"><span class="glyphicon glyphicon-log-in"></span> Sign In</a></li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">

					<li class="dropdown" id="userCart"><a
						href="javascript:void(0)"
						class="dropdown-toggle"
						id="dropdownMenu1" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>
							 ${userModel.fullName}<span class="caret"></span>
					</a>

						<ul class="dropdown-menu">
							<security:authorize access="hasAuthority('USER')">
								<li><a href="${contextRoot}/cart/show"> <span
										class="glyphicon glyphicon-shopping-cart"></span> <span
										class="badge">${userModel.cart.cartLines}</span> - &#8377;
										${userModel.cart.grandTotal}
								</a></li>

								<li class="divider" role="seperator"></li>

							</security:authorize>

							<li><a href="${contextRoot}/perform-logout">Sign Out</a></li>
						</ul></li>

				</security:authorize>

			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>


<script>
	window.userRole = '${userModel.role}';
</script>
