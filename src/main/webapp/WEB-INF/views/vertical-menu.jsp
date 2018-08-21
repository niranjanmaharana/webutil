<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<div id='cssmenu'>
	<ul>
		<li id=''><a href="${pageContext.request.contextPath}/admin/home"><span>Home</span></a></li>
		<%-- <li><a href='${pageContext.request.contextPath}/qr/qrcode'><span>QR Code</span></a></li> --%>
		<li><a href='${pageContext.request.contextPath}/cii/documents'><span>CII Documents</span></a></li>
		<%-- <li class='has-sub'><a href='#'><span>CII Share</span></a>
			<ul>
				<li><a href='${pageContext.request.contextPath}/cii/docs'><span>Documents</span></a></li>
				<li><a href='${pageContext.request.contextPath}/cii/webex'><span>Webex Recordings</span></a></li>
			</ul>
		</li> --%>
	</ul>
</div>