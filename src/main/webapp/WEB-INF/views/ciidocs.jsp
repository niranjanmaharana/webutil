<%@page import="com.legato.webutil.domain.documents.FileType"%>
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
<script src="${pageContext.request.contextPath}/js/custom/remove-file.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#loader').css('display', 'none');
		$('#loader-wrapper').css('display', 'none');
		$("#upload").click(function(){
			location.href = "${pageContext.request.contextPath}/cii/uploadform";
		});
	});
</script>
</head>
<body>
	<div class="content">
		<div id="loader-wrapper">
			<div id="loader"></div>
			<div class="loader-section section-left"></div>
			<div class="loader-section section-right"></div>
		</div>
		<div>
			<div class='content-left'>
				<jsp:include page="vertical-menu.jsp"/>
			</div>
			<div class='content-right'>
				<div class='main-title'>CII Documents</div>
				<security:authorize access="hasRole('SUPERADMIN')">
					<div class='button-container'><input type='button' value='Upload' id="upload"/></div>
				</security:authorize>
				<div class='list-container'>
					<table class='list-table'>
						<tr><th class='item-count'></th><th class='item-name'></th><th class='item-link'></th></tr>
						<c:forEach items="${fileNames }" var='fileName' varStatus="count">
							<script>var url = encodeURI('${fileName.value}');</script>
							<tr id='tr_item_${count.index + 1}'>
								<td class='item-count'>${count.index + 1})</td>
								<td class='item-name'>${fileName.key }</td>
								<td class='item-link'>
									<a href="${pageContext.request.contextPath}/cii/download?fileType=<%=FileType.DOCUMENTS.getId() %>&fileName=${fileName.key }" target="_blank">
										<img src='${pageContext.request.contextPath}/images/download.png'>
									</a>
									<security:authorize access="hasRole('SUPERADMIN')">
										<span class='link' onclick="removeFile(this, ${count.index + 1}, <%=FileType.DOCUMENTS.getId() %>, '${fileName.key }')">
											<img src='${pageContext.request.contextPath}/images/delete.png'>
										</span>
									</security:authorize>
								</td>
							</tr>
						</c:forEach>
						<tr><th class='item-count'></th><th class='item-name'></th><th class='item-link'></th></tr>
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