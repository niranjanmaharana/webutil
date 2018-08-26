<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<title>Legato Health Technology - Web Utility</title>
<link href="${pageContext.request.contextPath}/images/legatoicon.png" rel="icon" type="image/x-icon">

<link href='${pageContext.request.contextPath}/fonts/ABeeZee.css' rel='stylesheet'>
<link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/formDefault.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/vertical-menu.css" rel="stylesheet">

<script src="${pageContext.request.contextPath}/js/vendor/jquery-3.3.1.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/sweet-alert.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/modernizr-2.6.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/custom/vertical-menu.js"></script>
<meta charset='utf-8'>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script>
var contextPath = "${pageContext.request.contextPath}";
var userRoles = [];
<security:authentication property="principal.authorities" var="authorities" />
<c:forEach items="${authorities}" var="authority" varStatus="vs">
	userRoles.push('${authority.authority}');
</c:forEach>
</script>
<div class="headSec"></div>
<div class="wrapper01 boxShadow">
	<div class="topSection">
		<img src="${pageContext.request.contextPath}/images/logo.png" class='logo'>
		<div class="nav">
			<%-- <ul class="leftnav" style="padding-top: 5px;">
				<li><a href="${pageContext.request.contextPath}/admin/home"><img alt="home" src="${pageContext.request.contextPath}/images/icons/home.png"></a></li>
			</ul> --%>
			<ul class="rightnav">
				<li>Welcome <strong><security:authentication property="principal.username" /></strong></li>
				<li><a href="${pageContext.request.contextPath}/signout" class='logout'>Logout</a></li>
			</ul>
		</div>
	</div>