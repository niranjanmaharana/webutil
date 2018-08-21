<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.io.Serializable"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="header.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#loader').css('display', 'none');
		$('#loader-wrapper').css('display', 'none');
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
				<div class='main-title'>Welcome to the portal.</div>
				<span class='title'></span>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>