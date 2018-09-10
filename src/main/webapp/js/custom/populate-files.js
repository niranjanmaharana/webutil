$(document).ready(function() {
	loadFiles(rootPath, 0);
	$("#btnBack").click(function(){
		var backSource = decodeURI($("#btnBack").attr("path"));
		var count = (backSource.match(/\\/g) || []).length;
		if(count < 3) return;
		backSource = backSource.substring(0, backSource.lastIndexOf("\\"));
		loadFiles(backSource, -1);
	});
	
	$("#upload-box").dialog({
		autoOpen: false,
		modal: true,
		width: 500,
		height: 300
	});
	
	$("#uploadnewfile").click(function(){
		$("#fileLocation").val($("#address-bar").text());
		$("#upload-box").dialog("open");
	});
});

var loadFiles = function(rootPath, count){
	$("#btnBack").attr("path", rootPath);
	if(count == 0) $("#btnBack").prop("disabled", true);
	else $("#btnBack").prop("disabled", false);
	$("#address-bar").text(decodeURI(rootPath).substring(decodeURI(rootPath).indexOf("\\")));
	$("#currentPath").val(rootPath);
	
	$.ajax({
		url : contextPath + '/cii/documents/list',
		type : 'GET',
		data : {rootPath: rootPath},
		async: false,
		dataType: 'text',
		success: function(response){
			//console.log("files : " + JSON.stringify(response));
			populateFiles(JSON.parse(response));
		}, error: function(jqXHR) {
			console.log("Error", jqXHR.responseText+'('+jqXHR.status + ' - ' + jqXHR.statusText+')', "error");
		}
	});
}

var loadDefaultIcon = function(image) {
	image.onerror = "";
	image.src = contextPath + "/images/fileicons/blank.png";
	return true;
}

var populateFiles = function(files){
	var fileListTable = $("#file-list-table");
	$(fileListTable).html("<thead><tr><th>Name</th><th></th><th>Date of Creation</th><th></th><tr></thead>");
	$("<tbody></tbody>").appendTo(fileListTable);
	for(var i = 0; i < files.length; i++){
		var file = files[i];
		var trElement = $("<tr></tr>");
		if(file.directory){
			$("<td class='folder'><span class='folder-link' ondblclick=\"loadFiles('"+encodeURI(file.filePath)+"', -1)\"><img src='"+contextPath+"/images/icons/folder.png'></span></td>").appendTo(trElement);
			$("<td><div class='file-name' title='"+file.fileName+"'>"+file.fileName+"<div></td>").appendTo(trElement);
			$("<td class='date-field'>"+file.creationTime+"</td>").appendTo(trElement);
			$("<td></td>").appendTo(trElement);
		}else{
			var fileExt = file.fileName.substring(file.fileName.lastIndexOf('.')+1, file.fileName.length) || file.fileName;
			fileExt = fileExt.toLowerCase();
			$("<td class='file'><img src='"+contextPath+"/images/fileicons/"+fileExt+".png' onerror='loadDefaultIcon(this);'></td>" +
					"<td><div class='file-name' title='"+file.fileName+"'>"+file.fileName+"<div></td>").appendTo(trElement);
			$("<td class='date-field'>"+file.creationTime+"</td>").appendTo(trElement);
			$("<td class='file-link'><a href='"+contextPath+"/cii/downloadFile?filePath="+encodeURI(file.filePath)+"' target='_blank'><img src='"+contextPath+"/images/icons/download.png'></a></td>").appendTo(trElement);
			if(userRoles.indexOf('ROLE_SUPERADMIN') != -1){
				$("<td class='file-link'><a href='' target='_blank'><img src='"+contextPath+"/images/icons/delete.png'></a></td>").appendTo(trElement);
			}
		}
		trElement.appendTo(fileListTable);
	}
}