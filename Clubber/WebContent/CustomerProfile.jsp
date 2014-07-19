<%@page import="Utlis.SessionUtils"%>
<%@page import="Utlis.Constants"%>
<%@page import="ClubberLogic.PR"%>
<%@ page language="java" contentType="text/html; charset=windows-1255" pageEncoding="windows-1255"%>
<% String messageText = (String)request.getAttribute(Constants.MESSAGE_TEXT); %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
		<title>פרופיל</title>
	    <link href="CSS/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
		<style>
		div.star {
		   width:26px; 
		   height:30px; 
		   background-image:url('images/star.jpg');
		   background-repeat:no-repeat;
		   float:right;
		}

		div.yellowStar {
		   width:26px;
		   height:31px;
		   background-image:url('images/yellowStar.jpg');
		   background-repeat:no-repeat;
		   float:right;
		}
	
		</style>
	</head>
<body dir="rtl">
	<div class="user-details">
		<form class="user-details-form" id="prDetails" name="userDetails" method="post" action="UpdateUserDetails">
		  	
		  	<label id="firstnameLabel">שם פרטי</label>
		  	<input type="text" name="firstName" id="firstName" required disabled>
		  	<br>
		  	
		  	<label id="lastnameLabel">שם משפחה</label>
		  	<input type="text" name="lastName" id="lastName" required disabled>			
		  	<br>
		  	
		  	<label id="genderLabel">מין</label>
		  	<input type="radio" name="gender" value="Male" checked disabled >
			זכר
			<input type="radio" name="gender" value="Female" disabled>
			נקבה
			<br>
			
			<label id="birthdateLabel">תאריך לידה</label>
			<input name="birthdate" id="birthdate" disabled>
  			<br>
			
			<label id="phonenumberLabel">טלפון</label>
			<input type="text" name="phoneNumber" id="phoneNumber" required disabled>
			<br>

			
			<label id="emailLabel">דוא"ל</label>
			<input type="text" name="email" id="email" required disabled>
  			<br>

			<label id="passwordLabel">סיסמה</label>
			<input type="password" name="password" id="password" required disabled>
  			<br>

			<label id="verifyPasswordLabel">אימות סיסמה</label>
			<input type="password" name="verifyPassword" id="verifyPassword" required disabled>
  			<br>
  			  			
  			<button id="editUserDel" type="button" >ערוך</button>
  			<button id="updateUserDel" type="submit" onclick="updateUserDetails()" disabled>שמור</button>
		</form>		
		<br>
		<label id="messageText">
			<% if(messageText != null){ %>
				<%=messageText %>
			<%} %>		
		</label>
	</div>
	<div class="recomendedLines">
		
	</div>
	<div class="user-rating">
		<label id="generalLabel">כללי</label>
		<br>
		<div class="generalStars">
			<div class="star"></div>
			<div class="star"></div>
			<div class="star"></div>
			<div class="star"></div>
			<div class="star"></div>
		</div>
		<br><br>
		<label id="punctualityLabel">דיקנות</label>
		<br>
		<div class="punctualityStars">
			<div class="star"></div>
			<div class="star"></div>
			<div class="star"></div>
			<div class="star"></div>
			<div class="star"></div>
		</div>
		<br><br>
		<label id="reliabilityLabel">אמינות</label>
		<br>
		<div class="reliabilityStars">
			<div class="star"></div>
			<div class="star"></div>
			<div class="star"></div>
			<div class="star"></div>
			<div class="star"></div>
		</div>
		<br>
	</div>
	<script src="http://jquery.bassistance.de/validate/jquery.validate.js"></script>
	<script src="http://jquery.bassistance.de/validate/additional-methods.js"></script>		
	<script>
	
		// Set error messages  
		jQuery.extend(jQuery.validator.messages, {
		    required: "שדה חובה",
		    email: 'כתובת דוא"ל אינה חוקית',
		    equalTo: "סיסמאות אינן תואמות",
		    date: "תאריך לידה אינו חוקי"		    
		});
		

		$( "#userDetails" ).validate({
			  rules: {
			    email: {
			      	email: true
			    },
		
				verifyPassword: {
		      		equalTo: "#password"
			  	},
			    
			    birthdate: {
			    	date: true
			    }
			 }
		});		
		
		$("#editUserDel").click(function() {
			$('#firstName').attr("disabled", false);
			$('#lastName').attr("disabled", false);
			$('input[name="gender"]').attr("disabled", false);
			$('#birthdate').attr("disabled", false);		
			$('#phoneNumber').attr("disabled", false);
			$('#email').attr("disabled", false);
			$('#password').attr("disabled", false);		
			$('#verifyPassword').attr("disabled", false);
			$("#updatePrDel").attr("disabled",false);
		});
		
		$(function(){
		    $.ajax({
		        url: "GetDBData",
		        type: "post",
		        dataType: 'json',
		        data:{RequestType: "DBDataUserProfile"},
		        success: function(data) {
		        	$("#firstName").val(data.firstName);
		        	$("#lastName").val(data.lastName);
		        	$('input[name="gender"][value="'+data.gender+'"]').prop("checked", true);
		        	$("#birthdate").val(data.birthDate);
		        	$("#phoneNumber").val(data.phoneNumber);
		        	$("#email").val(data.email);
		        	$("#password").val(data.password);
		        },
		        error: function(data){
		            	console.log("error");}
		    });
		});
		
		$(function(){
		    $.ajax({
		        url: "GetDBData",
		        type: "post",
		        dataType: 'json',
		        data:{RequestType: "DBDataCustomerProfileReview"},
		        success: function(data) {
					
		        	var i;
		        	if(data != null){
			        	for(i=1; i < data.Availability; i++){
			        		$(".punctualityStars")[i].attr("class", "colored-star");
			        	}
			        	
			        	for(i=1; i < data.Realiability; i++){
			        		$(".reliabilityStars")[i].attr("class", "colored-star");
			        	}
			        				        	
			        	var generalReview = (data.Availability + data.Realiability + data.Treats) / 3;
			        	
			        	for(i=1; i < generalReview; i++){
			        		$(".generalStars")[i].attr("class", "colored-star");
			        	}
		        	}
		        	
		        },
		        error: function(data){
		            	console.log("error");}
		    });
		});
		
		$(function(){
		    $.ajax({
		        url: "GetDBData",
		        type: "post",
		        dataType: 'json',
		        data:{RequestType: "DBDataRecomendedLines"},
		        success: function(data) {
					data.foreach(function(line){
						$(".recomendedLines").append("<p>'"+line.name+"'</p>");
					});
		        },
		        error: function(data){
		            	console.log("error");}
		    });
		});

		
	</script>			
</body>
</html>