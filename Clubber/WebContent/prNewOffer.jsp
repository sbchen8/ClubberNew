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
    		<div id = "offer_summery">Offer Summery</div>
    		<div id = 'explenation'>Explenation</div>
   		 	<form action="demo_form.asp">
				Make an offer: <input type="text" name="usrname"><br>
				<input type="submit" value="Offer">
			</form>
		</div>                

    </body>
</html>
