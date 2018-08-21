$(document).ready(function() {
	$("#createnewfolder").click(function() {
		var path = $("#btnBack").attr("path");
		var folderName = prompt("Folder Name : ", "New Folder");
		if (folderName == null || folderName == "") {
			swal("Error", "Folder name can not be empty !", "error");
		}else{
			//swal("Information", "Folder name " + folderName, "info");
			$.ajax({
				url : contextPath + '/cii/createfolder',
				type : 'post',
				data : {path: $("#currentPath").val(), folderName: encodeURI(folderName)},
				async: false,
				success: function(response){
					if(response.responseCode == 200)
						loadFiles($("#currentPath").val(), -1);
					else
						swal("Error in folder creation", response.message, "error");
				}, error: function(jqXHR) {
					swal("Error in folder creation", jqXHR.responseText+'('+jqXHR.status + ' - ' + jqXHR.statusText+')', "error");
				}
			});
		}
	});
});