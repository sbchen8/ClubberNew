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
				
				<div class="search-filters">
				  	<label id="agesRangeLabel">טווח גילאים</label>
					<input type="checkbox" id="agesRange" onclick="toggle(this)" checked> הכל
				  	<input type="checkbox" name="agesRange" id="16-18" value="16-18" checked>16-18
				  	<input type="checkbox" name="agesRange" value="19-20" checked>19-20
				  	<input type="checkbox" name="agesRange" value="21-24" checked>21-24
				  	<input type="checkbox" name="agesRange" value="over25" checked>25 ומעלה
				  	<br>
				  	
	<!-- 			  	ask if it is critical to upload all this data from musicstyle table -->
				  	<label id="musicStyleLabel">סגנון מוזיקה</label>
				  	<input type="checkbox" id="musicStyle" onClick="toggle(this)" checked> הכל
				  	<input type="checkbox" name="musicStyle" value="pop" checked>פופ
				  	<input type="checkbox" name="musicStyle" value="africanAmerican" checked>שחורה
				  	<input type="checkbox" name="musicStyle" value="reggae" checked>רגאיי
				  	<input type="checkbox" name="musicStyle" value="electronic" checked>אלקטרוני 
				  	<input type="checkbox" name="musicStyle" value="HipHop" checked>היפ הופ
				  	<input type="checkbox" name="musicStyle" value="house" checked>האוס
				  	<input type="checkbox" name="musicStyle" value="rock" checked>רוק
				  	<input type="checkbox" name="musicStyle" value="reggaeton" checked>רגאטון
				  	<br>
					
				  	<label id="dayInWeekLabel">יום בשבוע</label>
				  	<input type="checkbox" id="dayInWeek" onClick="toggle(this)" checked> הכל
				  	<input type="checkbox" name="dayInWeek" value="sunday" checked>ראשון
				  	<input type="checkbox" name="dayInWeek" value="monday" checked>שני
				  	<input type="checkbox" name="dayInWeek" value="tuesday" checked>שלישי
				  	<input type="checkbox" name="dayInWeek" value="wednesday" checked>רביעי
				  	<input type="checkbox" name="dayInWeek" value="thursday" checked>חמישי
				  	<input type="checkbox" name="dayInWeek" value="friday" checked>שישי
				  	<input type="checkbox" name="dayInWeek" value="saturday" checked>שבת
				  	<br>
			  	</div>
				
				<input type="checkbox" name="searchByMyLines" id="searchByMyLines">
				חפש לפי התאמה לליינים שלי
				<br>
				<button type="submit" >חפש</button>
			</form>
		</div>
		
	<script>
		
	$("#searchByMyLines").click(function() {
		var isChecked = $("#searchByMyLines").is(':checked');
		var checkboxes = $('.search-filters input[type=checkbox]');
		
		for(var i = 0; i < checkboxes.length; i++) {
			checkboxes[i].disabled = isChecked;
		}
	});	
	
	function toggle(source) {
		var name = source.id;
		checkboxes = document.getElementsByName(name);
		
		for(var i = 0; i <= checkboxes.length; i++){
			checkboxes[i].checked = source.checked;
		}
	}
	</script>
	</body>
</html>