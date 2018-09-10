$(document).ready(function(){
	$("#upload-form").validate({
		rules: {
			file: {
				required: true,
				//extension: "xls|csv|"
			}
		}
	});
});

$(function() {
	$('button[type=submit]').click(function(e) {
		e.preventDefault();
		if(!$("#upload-form").valid()) return false;
		$(this).prop('disabled', true);
		var form = document.forms[0];
		var formData = new FormData(form);
		formData.append("filePath", $("#currentPath").val());
		
		// Ajax call for file uploaling
		var ajaxReq = $.ajax({
			url : $("#upload-form").attr("action"),
			type : 'POST',
			data : formData,
			async: false,
			cache : false,
			contentType : false,
			processData : false,
			xhr: function(){
				//Get XmlHttpRequest object
				var xhr = $.ajaxSettings.xhr();
				//Set onprogress event handler
				xhr.upload.onprogress = function(event){
					var perc = Math.round((event.loaded / event.total) * 100);
					$('#progressBar').text(perc + '%');
					$('#progressBar').css('width', perc + '%');
				};
				return xhr;
			},
			beforeSend: function( xhr ) {
				//Reset alert message and progress bar
				$('#alertMsg').text('');
				$('#progressBar').text('');
				$('#progressBar').css('width','0%');
			}
		});
		// Called on success of file upload
		ajaxReq.done(function(msg) {
			swal("Success", msg, "success");
			$('input[type=file]').val('');
			$('button[type=submit]').prop('disabled',false);
			$("#upload-box").dialog("close");
			loadFiles($("#currentPath").val(), -1);
		});
		// Called on failure of file upload
		ajaxReq.fail(function(jqXHR) {
			//$('#alertMsg').text(jqXHR.responseText+'('+jqXHR.status + ' - ' + jqXHR.statusText+')');
			swal("Success", jqXHR.responseText+"("+jqXHR.status + " - " + jqXHR.statusText+")", "success");
			$('button[type=submit]').prop('disabled',false);
		});
	});
});