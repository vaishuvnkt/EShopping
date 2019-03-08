<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Gorgeous You - ${title}</title>

<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>

<!-- should be in the same order -->

<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">


<!-- Bootstrap Theme CSS -->
<link href="${css}/template-theme-journal.css" rel="stylesheet">

<!-- Bootstrap DataTable jQuery -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${css}/myapp.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div class="wrapper">
		<!-- to avoid footer prblm wrap the whole body in class called wrapper -->


		<!-- Navigation -->

		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">

			<div class="container">

				<!-- brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">

					<a class="navbar-brand" href="${contextRoot}/home">Home</a>

				</div>

			</div>

		</nav>

		<!-- Page Content -->


		<div class="content">
			<!-- to avoid footer prblm wrap the page content in class called content -->

			<div class="container">

				<%--this message will be displayed only if user tries to login with unregistered email id or credentials are wrong like wrong password   --%>
				<c:if test="${not empty message}">

					<div class="row">

						<div class="col-md-offset-3 col-md-6">
						
							<div class = "alert alert-danger">$message</div>
						
						</div>
						
					</div>

				</c:if>

				<div class="row">

					<div class="col-md-offset-3 col-md-6">

						<div class="panel panel-primary">

							<div class="panel-heading">
								<h4>Sign in</h4>
							</div>

							<div class="panel-body">
								<form action="${contextRoot}/login" method="POST"
									class="form-horizontal" id="loginForm">
									<div class="form-group">
										<%--the id name and pwd for emaill attribute and pwd should be username and password only coz spring security's default name is username and password only to change it use username-parameter key inside form-login tag to change username--%>
										<label for="username" class="col-md-4 control-label">Email
										</label>
										<div class="col-md-8">
											<input type="text" name="username" id="username"
												class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label for="password" class="col-md-4 control-label">Password
										</label>
										<div class="col-md-8">
											<input type="password" name="password" id="password"
												class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-offset-4 col-md-8">
											<%-- provide a hidden field that will keep the csrf token that only server recogonize when the form is submitted--%>
											<%--spring form automatically creates this csrf token input line --%>
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" /> <input type="submit"
												value="Login" class="btn btn-primary" />
										</div>
									</div>
								</form>

							</div>
							<div class="panel-footer">
								<div class="text-right">
									<a href="${contextRoot}/register">Didn't sign up?</a>
								</div>
							</div>

						</div>

					</div>
				</div>
			</div>
		</div>

		<!-- /.container -->

		<!-- Footer comes here -->
		<%@include file="./shared/footer.jsp"%>
		<!-- /.container -->

		<!-- should be in the same order -->

		<!-- jQuery -->
		<script src="${js}/jquery.js"></script>

		<!-- jQuery validation -->
		<script src="${js}/jquery.validate.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>

		<!-- Self coded JavaScript File -->
		<script src="${js}/myapp.js"></script>
		<!-- used for active menu -->

	</div>
</body>

</html>
