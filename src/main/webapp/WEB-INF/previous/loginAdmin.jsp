<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<link href="<c:url value='/resources/dir/css/bootstrap.min.css' />" rel="stylesheet" id="bootstrap-css">
		<link href="<c:url value='/resources/dir/css/login.css' />" rel="stylesheet" id="login-css">
		<!------ Include the above in your HEAD tag ---------->

		<style>
			.error {
				padding: 15px;
				margin-bottom: 20px;
				border: 1px solid transparent;
				border-radius: 4px;
				color: #a94442;
				background-color: #f2dede;
				border-color: #ebccd1;
			}

			.msg {
				padding: 15px;
				margin-bottom: 20px;
				border: 1px solid transparent;
				border-radius: 4px;
				color: #31708f;
				background-color: #d9edf7;
				border-color: #bce8f1;
			}

			#login-box {
				width: 300px;
				padding: 20px;
				margin: 100px auto;
				background: #fff;
				-webkit-border-radius: 2px;
				-moz-border-radius: 2px;
				border: 1px solid #000;
			}
		</style>
	</head>






		<body onload='document.loginForm.username.focus();'>


		<div class="modal-dialog">
			<div class="loginmodal-container">
							<h1>Login to Your Account</h1><br>



				<div class="panel-body">
					<c:url var="loginUrl" value="/login" />

					<form action="${loginUrl}"  name="loginForm" method="post" class="form-horizontal">



						<c:if test="${param.error != null}">
						<div class="alert alert-danger">
							<p>Invalid username and password.</p>
						</div>
						</c:if>

						<c:if test="${param.logout != null}">
						<div class="alert alert-success">
							<p>You have been logged out successfully.</p>
						</div>
						</c:if>

						<c:if test="${param.accessDenied != null}">
						<div class="alert alert-danger">
							<p>Access Denied: You are not authorised!</p>
						</div>
						</c:if>



						<input type="text" id="userId" name="userId" placeholder="Username">
						<input type="password" id="password"  name="password" placeholder="Password">

						<div class="form-actions">
							<input type="submit"
								   class="btn btn-block btn-primary btn-default" value="Log in">
						</div>
					</form>


				</div>
			</div>
		</div>

				<script src="<c:url value='/resources/dir/js/bootstrap.js' />"></script>
				<script src="<c:url value='/resources/dir/js/jquery-2.1.4.min.js' />"></script>
		</body>
</html>

