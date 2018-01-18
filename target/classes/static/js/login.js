$(document).ready(function() {

	var signUpUrl = $("#ctx").val() + "/user/signup";
	$('#singup').on('click', function() {
		window.location.href = signUpUrl;
	});
});
