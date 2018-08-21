$(document).ready(function(){
	setTimeout(fadeOut, 5000);
	
	$("#login-form").validate({
		rules: {
			username: "required",
			password: "required"
		},
		messages: {
			username: "This field is required !",
			password: "This field is required !"
		}
	});
});

function fadeOut() {
	$("#message").fadeOut().empty();
}