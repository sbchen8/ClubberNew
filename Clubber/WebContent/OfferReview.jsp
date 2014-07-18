<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Offer Review</title>
        <link href="CSS/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="CSS/Main.css" rel="stylesheet" type="text/css">
  		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  		<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script src="Script/OfferReview.js"  type="text/javascript" charset="windows-1255" ></script>
        
    </head>
    <body >
    
    <div class= 'site-header'>
    <div class= 'upper-header'>
    	<div class='logo'></div>
    	<div class='advertisements'></div>
    </div>
    <div class='nav-bar'> פרופיל | המכרזים שלי | התרעות </div>
    <br>
    </div>
    <div class='main-container'>  
    <div class= "page-description">בדף זה ניתן לצפות בפרטי ההצעה שהתקבלה </div>
       	<div class="offer-item-reviewed-container">
       		<div class='offer-item-reviewed-title'>
       			
       		</div>
       		<div class= "offer-item-reviewed-content">
       			<div title="לחץ כאן כדי לראות את פרטי הליין" class="offer-reviewed-item-line-image-container" onclick="alert('')">
       				<img src="/Clubber/images/line_Img.png" class="offer-item-line-image">
       			</div>
       			<div class="offer-reviewed-item-description">
		       		<div id="offer-description">
						<label class="offer-title-label">תיאור</label>
					</div>
					<div id="offered-line">
						<label class="offer-title-label">ליין מוצע</label>
					</div>
					<div id="offer-expiration-date">
						<label class="offer-title-label">ת.תפוגת ההצעה</label>
					</div>
					<div id="max-arrival-hour">
						<label class="offer-title-label">שעת הגעה מקסימלית</label>
					</div>
					<div id="offer-status">
						<label class="offer-title-label">סטטוס ההצעה</label>
					</div>
					<div id="offer-treats">
						<label class="offer-title-label"> סל פינוקים</label>
					</div>
				</div>
				<input class='btn-primary' type="button" id="accept-offer-button" name="accept-offer-button"  value="קבל הצעה זו" onClick= "acceptOfferClicked()"/>
			</div>
		</div>
       <div class='all-messages-container'>
	       	<div class="old-messages"></div>
	       	<div class="new-message"></div> 
	       	 <input type="text" id='outgoing-message-text' class='outgoing-message outgoing-message-empty' onkeypress='sendMessage()' onblur='setNewMessageTextOnFocusOut();' onfocus='setNewMessageTextOnFocusIn();' id="new-message" name="new-message-input" value="השב...">        
	    </div>
	    <div class='recomended-lines-container'></div>        
    </div>

    </body>
</html>
