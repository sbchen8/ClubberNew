	
function formattedDate(date) {
    var d = new Date(date || Date.now()),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [day, month, year].join('/');
}


function loadDataFromDB(data, areaName) 
	{
		console.log("adding auctions to "+ areaName);
		var counterDescription= " פניות מיחצנים  ";
		var description;
		
		for (var item in data) {
			if (data[item].description== null)
			{
				description="";
			}
			else
			{
				description= data[item].description;
			}
			
			$(' <div class="bg" style="padding-top: 0;"><div id=' +data[item].id+' class="my-auction-container" title="הצעות מיחצנים" onclick="auctionClicked('+data[item].id + ')" > <div class="my-auction-title"><h3>'+data[item].eventType.Name+ '</h3>  <span  style="float: left; verticle-align: top">'+ formattedDate(data[item].eventDate) +'</span></div>'
					+ '<div class="my-auction-description">'+description+'</div>'
					+ '<div class="my-auction-offer-number">'+data[item].offerNumber+counterDescription +'</div>'
					+'</div></div>').appendTo($(areaName)) ;
		}
	}

	
	
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
	
	function ajaxDataFormDBData() {
	    $.ajax({
	        url: "GetDBData",
	        type: "post",
	        dataType: 'json',
	        data:{RequestType: "GetDBData-MyAuctions"},
	        success: function(data) {
	            if (data != null) {
	                console.log("GetDBData-MyAuctions");  
	                loadDataFromDB(data, '.all-auctions-container' );
	            }},
	        error: function(data){
	            	console.log("error");}
	            
	        
	    });
	}

	$(function() {
		ajaxDataFormDBData();
	});