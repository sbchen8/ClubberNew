<%@page import="ClubberLogic.UserType"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link href="CSS/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">		
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	</head>
	<body dir="rtl">
		<div class="message">
		התוצאות ממויינות אוטומטית לפי האזורים שבהם יש לך ליין לא ניתן לראות מכרזים באזורים אחרים. 
		</div>
		<div class="search-area">
			<form class="search-auction-form" id="searchAuction" name="searchAuction" method="post" action="SearchAuction">

			  	<label id="agesRangeLabel">טווח גילאים</label>
			  	<input type="text" name="agesRange" id="agesRange">
			  	<br>
			  	
			  	<label id="musicStyleLabel">סגנון מוזיקה</label>
			  	<input type="text" name="musicStyle" id="musicStyle">
			  	<br>
				
			  	<label id="dayInWeekLabel">יום בשבוע</label>
			  	<input type="text" name="dayInWeek" id="dayInWeek">
			  	<br>
				
				<input type="checkbox" name="matchesAll" id="matchesAll">
				חפש לפי התאמה לליינים שלי
				<br>
				
				<button type="submit" >חפש</button>
			</form>
		</div>
		
	<script>
		
	$("#matchesAll").click(function(){
		var isCheck = $("#matchesAll").is(':checked');

		if(isCheck == 1){
			$("#agesRange").attr("disabled",true);
			$("#agesRange").val("");
			$("#musicStyle").attr("disabled",true);
			$("#musicStyle").val("");
			$("#dayInWeek").attr("disabled",true);
			$("#dayInWeek").val("");
		}
	});	
	</script>
	</body>
</html>