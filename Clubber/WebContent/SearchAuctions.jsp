<%@page import="ClubberLogic.UserType"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Search for auction</title>
		<link href="CSS/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">		
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
		<script src="Script/DateFormater.js"  type="text/javascript"></script>		
	</head>
	<body dir="rtl">
		<div class="message">
		התוצאות ממויינות אוטומטית לפי האזורים שבהם יש לך ליין לא ניתן לראות מכרזים באזורים אחרים. 
		</div>
		<div class="search-area">
			<form class="search-auction-form" id="searchAuction" name="searchAuction" method="post">
				
				<div class="search-filters">
				  	<label id="agesRangeLabel">טווח גילאים</label>
					<input type="text" name="agesRange" id="agesRange">
				  	<br>
				  	
				  	<label id="musicStyleLabel">סגנון מוזיקה</label>
				  	<div class="auction-music-style">
					</div>
					
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
				<button type="button" id="searchAuctions" >חפש</button>
			</form>
	       <div class='all-auctions-container'>   
	             
	   	   </div>    
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
	
	$(function() {
	
		musicStyleDiv = $(".auction-music-style");
	  	
	    $.ajax({
	        url: "GetDBData",
	        type: "post",
	        dataType: 'json',
	        data:{RequestType: "DBDataAuctionMusicStyle"},
	        success: function(data) {
				
	        	musicStyleDiv.append($('<label><input type="checkbox" id="MusicStyle" onClick="toggle(this)" checked>הכל </label>'));
	        	
	        	for(var i=0; i < data.length; i++){
	        		
	        		var element = '<label><input type="checkbox" name="MusicStyle" checked>' +data[i].Name+ '</label>' ;
	        		musicStyleDiv.append($(element));
	        	}
	        	
	        },
	        error: function(data){
	            	console.log("error");}
	    });
	});	
	
	$("#searchAuction").validate({
		  rules: {
			    agesRange: {
			    	number: true,
			      	range: [1,120]
			    }
			 }
	});
	
	// Set error messages  
	jQuery.extend(jQuery.validator.messages, {
		number: "גיל מינימלי אינו חוקי",
	  	range: "גיל מינימלי אינו חוקי"
	});
	
	$("#searchAuctions").click(function(){
	    $.ajax({
	        url: "SearchAuction",
	        type: "post",
	        dataType: 'json',
	        data: {agesRange: "agesRange",
	        	  musicStyle: "MusicStyle",
	        	  dayInWeek: "dayInWeek",
	        	  searchByMyLines: "on"},
	        success: function(data) {

				console.log("adding auctions");
				var counterDescription= " הצעות התקבלו  ";
				var description;
				
				$(".all-auctions-container").html("");
				
				for (var item in data) {
					if (data[item].description== null)
					{
						description="";
					}
					else
					{
						description= data[item].description;
					}
					
					$(' <div id=' +data[item].id+' class="my-auction-container" title="לחץ כאן כדי לראות את פרטי המכרז" onclick="auctionClicked('+data[item].id + ')"> <div class="my-auction-title">'+data[item].eventType.Name+ ' - '+ formattedDate(data[item].eventDate) +'</div>'
							+ '<div class="my-auction-description">'+description+'</div>'
							+ '<div class="my-auction-offer-number">'+data[item].offerNumber+counterDescription +'</div>'
							+'</div>').appendTo($(".all-auctions-container")) ;
				}

	        	
	        	
	        },
	        error: function(data){
	            	console.log("error");}
	    });
		
	});
			
		function auctionClicked(auctionID)
		{
			  $.ajax({
			        url: "AuctionOfferItemClicked",
			        type: "post",
			        dataType: 'json',
			        data:{ClickedItemType: "AuctionItemClicked", ItemID:auctionID},
			        success: function(data) {
			        	 console.log("redirect to auction management page");
			             window.location.href = 'AuctionManagement.jsp';
			            },
			        error: function(data){
			            	console.log("error");}
			            
			        
			    });
		}
		
		
		function formattedDate(date) {
		    var d = new Date(date || Date.now()),
		        month = '' + (d.getMonth() + 1),
		        day = '' + d.getDate(),
		        year = d.getFullYear();

		    if (month.length < 2) month = '0' + month;
		    if (day.length < 2) day = '0' + day;

		    return [day, month, year].join('/');
		}
	</script>
	</body>
</html>