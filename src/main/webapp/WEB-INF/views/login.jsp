<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/images/legatoicon.png" rel="icon" type="image/x-icon">

<script src="${pageContext.request.contextPath}/js/vendor/jquery-3.3.1.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/custom/login.js"></script>

<link href='https://fonts.googleapis.com/css?family=ABeeZee' rel='stylesheet'>
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
<noscript>JavaScript is disabled on your browser. Please enable JavaScript or upgrade to a JavaScript-capable browser.</noscript>
</head>
<body>
	<div class='wrapper'>
		<div class='login-wrapper'>
			<div class='login-container'>
				<div class='login-header'>
					<div class='client-logo'><img src='${pageContext.request.contextPath}/images/legatoicon.png'/></div>
					<div class='client-name'>Legato Health Technologies LLP</div>
					<!--<div class='login-title'>Web Utility Login</div>-->
				</div>
				<div class='login-form'>
					<form action='<spring:url value="/signin"/>' method="post" id='login-form'>
						<table class='form-table'>
							<tr><th>Username:</th></tr>
							<tr><td><input name="username" id="username" type="text" placeholder='Enter username' autocomplete="off" maxlength="200"></td></tr>
							<tr><th>Password:</th></tr>
							<tr><td><input name="password" id="password" type="password" placeholder='Enter password' autocomplete="off" maxlength="200"></td></tr>
							<tr><td class='td-submit'><input type='submit'value='Login' class='login-btn'></td></tr>
						</table>
					</form>
				</div>
				<div class='message-td'>
					<c:if test="${not empty sessionScope.message}">
						<div class='alert alert-danger' id='message'><c:out value="${sessionScope.message}"/></div><c:remove var="message" scope="session" />
					</c:if>
				</div>
				<hr class='devider'>
				<div class='footer'>Copyright 2017-2018 © <a href='https://legatohealthtech.com' target='_blank'>Legato Health Technologies.</a> All Rights Reserved.</div>
			</div>
		</div>
	</div>
</body>
</html>