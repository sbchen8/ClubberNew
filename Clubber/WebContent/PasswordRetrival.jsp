<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
	<title>Password Retrival</title>
	<link href="CSS/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">	
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>	
</head>
<body dir="rtl">
	<div class="password-retrival-area">
		<form class="password-retrival-form" id="passwordRetrival" name="passwordRetrival" method="post" action="PasswordRetrival">
			<label id="emailLabel">דוא"ל</label>
			<input type="text" name="email" id="email" required>
  			<br>
  			<button type="submit" >שלח</button>
		</form>
	</div>
	<div class="message-area">
		<label id="statusMessageLabel"></label>
	</div>
	<script src="http://jquery.bassistance.de/validate/jquery.validate.js"></script>
	<script src="http://jquery.bassistance.de/validate/additional-methods.js"></script>
	<script>
	
	// Set default messages  
	jQuery.extend(jQuery.validator.messages, {
	    required: "שדה חובה",
	    email: 'כתובת דוא"ל אינה חוקית',
	});	
	
	// validate fields
	$( "#passwordRetrival" ).validate({
		  rules: {
		    email: {
		      	email: true
		    }
		 }
		});		
	
	</script>		    				
</body>
</html>