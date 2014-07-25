var currAucId;
var refreshRate = 1000; //miliseconds

function setNewMessageTextOnFocusOut()
{
	elementId= 'outgoing-message-text'; 
    
    if ($('#outgoing-message-text')[0].value == "")
    {
    	document.getElementById(elementId).setAttribute("class","outgoing-message outgoing-message-empty");
        document.getElementById(elementId).setAttribute("value","השב...");
    }  
}

function setNewMessageTextOnFocusIn()
{
elementId= 'outgoing-message-text'; 
    
    if ($('#outgoing-message-text')[0].value == "השב...")
    {
    	document.getElementById(elementId).setAttribute("class","outgoing-message outgoing-message-value");
        document.getElementById(elementId).setAttribute("value","");
    } 
}

function convert12to24(timeStr)
	{
	    var meridian = timeStr.substr(timeStr.length-2).toLowerCase();;
	    var hours =  timeStr.substr(0, timeStr.indexOf(':'));
	    var minutes = timeStr.substring(timeStr.indexOf(':')+1, timeStr.indexOf(' '));
	    if (meridian=='pm')
	    {
	        if (hours!=12)
	        {
	            hours=hours*1+12;
	        }
	        else
	        {
	            hours = (minutes!='00') ? '0' : '24' ;
	        }
	    }

	    return hours+':'+minutes;
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


function convertTimeStampFormat(timestamp)
{
	var d = new Date(timestamp || Date.now()),
    year = d.getFullYear();
	var startIndex= timestamp.indexOf(year)+5;
	var time= timestamp.substr(startIndex);
	
	return (formattedDate(timestamp) + ' '+ convert12to24(time));
}


function approveBtnClicked()
{
	
}

function loadOfferFromDB(data)
{
		console.log("adding current offer");
		var description;
			
		if (data.description== null)
		{
			description="";
		}
		else
		{
			description= data.description;
		}
		$(".offer-item-reviewed-title").append("<div class= 'offer-item-right-title' >"+data.prId.Name+"</div> <div class='offer-item-left-title' >" +formattedDate(data.submitDate) +"</div>");
		$("#offer-description").append("<label class='offer-value-label'>"+description+"</label>");
		$("#offered-line").append("<label class='offer-value-label'>"+data.lineId.Name+"</label>");/*should be a link to line*/
		$("#offer-expiration-date").append("<label class='offer-value-label'>"+formattedDate(data.expirationDate)+"</label>");
		$("#max-arrival-hour").append("<label class='offer-value-label'>"+convert12to24(data.maxArrivalHour)+"</label>");
		$("#offer-status").append("<label class='offer-value-label'>"+data.offerStatusId.Name+"</label>");
		
		for (var item in data.offerTreats) 
		{
			$("#offer-treats").append("</br> <img src='/Clubber/images/Check_Image.png' class='offer-item-treat-image'><label class='offer-multi-value-label'>"+data.offerTreats[item].Name+"</label>");
		}
		
	}	
	
	function ajaxOfferFormDBData() {
	    $.ajax({
	        url: "GetDBData",
	        type: "post",
	        dataType: 'json',
	        data:{RequestType: "GetDBData-OfferReview"},
	        success: function(data) {
	            if (data != null) {
	                console.log("GetDBData-OfferReview");  
	                currAucId= data.auctionId;
	                loadOfferFromDB(data);
	            }},
	        error: function(data){
	            	console.log("error- offer review");}	        
	    });
	}
	
	/*function ajaxApproveCurrentOffer() {
	    $.ajax({
	        url: "GetDBData",
	        type: "post",
	        dataType: 'json',
	        data:{RequestType: "GetDBData-OfferReview"},
	        success: function(data) {
	           if (data != null) {
	            	/*if offer pending to accept and expiration date not over and auction status is active then accept offer*/
	            	/*if ((data.offerStatusId.id == 2) && (data.expirationDate< now)&& ())
            		{
	            		
            		}
	            		
	                console.log("");  

	            }},
	        error: function(data){
	            	console.log("error- getting offer details");}	        
	    });
	}*/
	
	
	function ajaxSendMessage(){
		var description= $('#outgoing-message-text')[0].value;
		 $.ajax({
		        url: "GetDBData",
		        type: "post",
		        dataType: 'json',
		        data: {RequestType: "GetDBData-AddMessage",OutGoingMessageDescription:description},
		        success: function(data){
		        	console.log("message creation succedded");
		        	$('#outgoing-message-text')[0].value="";
		        },
		        error: function(data){
	            	console.log("error- adding message");}
		        });
	}
	
	function loadMessagesFromDB(data)
	{
		 $(".old-messages").empty(); 
		for (var i = 0; i < data.length; i++) {
			$(".old-messages").append("<div class='incoming-message'> <label>"+data[i].description+"</label></br> <label class='message-date'>"+convertTimeStampFormat(data[i].createdOn)+"</label></div>");
		}
	}
	
	function ajaxMessagesFormDBData() {
	    $.ajax({
	        url: "GetDBData",
	        type: "post",
	        dataType: 'json',
	        data:{RequestType: "GetDBData-Messages"},
	        success: function(data) {
	            if (data != null) {
	                console.log("GetDBData-Messages");  
	                loadMessagesFromDB(data);
	            }},
	        error: function(data){
	            	console.log("error- Messages");}
	            
	        
	    });
	}

	$(function() {
		ajaxOfferFormDBData();
		ajaxMessagesFormDBData();
		$('#outgoing-message-text').keypress( function( e ) {
			  if( e.keyCode == 13 ) { ajaxSendMessage(); }
			} );
		//The games list is refreshed automatically every second
	    setInterval(ajaxMessagesFormDBData, refreshRate);
	});