<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<jsp:include page="header.jsp" />
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">

<script src="${pageContext.request.contextPath}/js/custom/upload-file.js"></script>
</head>
<body>
	<div class="content">
		<div>
			<div class='content-left'>
				<jsp:include page="vertical-menu.jsp"/>
			</div>
			<div class='content-right'>
				<div class='main-title'>CII Documents</div>
				<div id='upload-box'>
					<form action="${pageContext.request.contextPath}/cii/upload" method="post" enctype="multipart/form-data" id='upload-form'>
						<div class="form-group">
							<label>File Type
								<select name='fileType'>
									<option value='0'>Document</option>
									<option value='1'>WebEx Recording</option>
								</select>
							</label>
						</div>
						<div class="form-group">
							<label>Select File</label><input class="form-control" type="file" name="file">
						</div>
						<div class="form-group">
							<button class="btn btn-primary" type="submit">Upload</button>
						</div>
					</form>
					<!-- Bootstrap Progress bar -->
					<div class="progress">
						<div id="progressBar" class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">0%</div>
					</div>

					<!-- Alert -->
					<div id="alertMsg" style="color: red; font-size: 18px;"></div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>