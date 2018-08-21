$(function() {

});

var removeFile = function(that, trCount, fileType, fileName){
	swal(fileType + "\n" + fileName);
	$(that).prop('disabled', true);
	$("#message-container").show();
	// Ajax call for file uploaling
	var ajaxReq = $.ajax({
		url : contextPath + "/cii/removefile",
		type : 'POST',
		data : {fileType: fileType, fileName: fileName},
		xhr: function(){
			var xhr = $.ajaxSettings.xhr();
			xhr.upload.onprogress = function(event){
				var perc = Math.round((event.loaded / event.total) * 100);
				$('#progressBar').text(perc + '%');
				$('#progressBar').css('width', perc + '%');
			};
			return xhr;
		},
		beforeSend: function( xhr ) {
			$('#progressBar').text('');
			$('#progressBar').css('width','0%');
		}
	});
	ajaxReq.done(function(msg) {
		/*swal({
			title : "Information",
			text : msg,
			icon : "success",
		},function(){
			location.reload();
		});*/
		swal({
			title : "Deleted!",
			text : "Your has been deleted.",
			type : "success"
		}).then(function(){
			$("#tr_item_"+trCount).remove();
		});
	});
	ajaxReq.fail(function(jqXHR) {
		swal({
			title : "Error",
			text : (jqXHR.responseText+'('+jqXHR.status + ' - ' + jqXHR.statusText+')'),
			icon : "success",
		});
		//swal(jqXHR.responseText+'('+jqXHR.status + ' - ' + jqXHR.statusText+')', 'error');
	});
}