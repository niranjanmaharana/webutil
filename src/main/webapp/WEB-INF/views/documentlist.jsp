<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="header.jsp" />
<link href="${pageContext.request.contextPath}/css/list.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">

<script type="text/javascript">
var rootPath = '${rootPath}';
</script>
<script src="${pageContext.request.contextPath}/js/custom/populate-files.js"></script>
<script src="${pageContext.request.contextPath}/js/custom/create-folder.js"></script>
</head>
<body>
	<div class="content">
		<div>
			<div class='content-left'>
				<jsp:include page="vertical-menu.jsp"/>
			</div>
			<div class='content-right'>
				<div class='main-title'>CII Documents</div>
				<div>
					<input type='hidden' id='currentPath' name='currentPath'>
					<div class='directory-option'>
						<button id='btnBack' class="previous" title='Go back'>&larr;</button>
					</div>
					<div class='address-bar' id='address-bar'></div>
					<security:authorize access="hasRole('SUPERADMIN')">
						<div class='button-container'>
							<input type='button' value='New Folder' id="createnewfolder"/>
							<input type='button' value='Upload' id="uploadnewfile"/>
						</div>
					</security:authorize>
				</div>
				<div class='file-list-container'>
					<table class='file-list-table' id='file-list-table'>
					</table>
				</div>
				<div id='message-container' class='message-container'>
					<div class="progress">
						<div id="progressBar" class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">0%</div>
					</div>
					<div id="alertMsg" style="color: red; font-size: 18px;"></div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>