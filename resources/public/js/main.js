
var obj ="";
$.get( "api/get_rooms", function( data ) {
/*	alert( data );
*/	obj = JSON.parse(JSON.parse(data));
	console.log(obj);

	var content = "";
	var count = 0;

	console.log(obj.length + obj[0]);

	for (var i = obj.length - 1; i >= 0; i--) {
		if(count%3==0) content += "<div class=\"row\">";
		content += "<div class=\"col-sm-6 col-md-4\"><div class=\"thumbnail\"><img src=\"https://liquidspace.com/Content/Images/SEONew/final-conference-rooms.png\" alt=\"...\"><div class=\"caption\">"; 
		content += "<h3>" + obj[i]["room_name"]+ "</h3>";
		content += "<p>"+ obj[i]["room_desc"] +"</p>";
		content += "<p><a href=\"book_room?room="+ obj[i]["room_id"]+"\" class=\"btn btn-primary\" role=\"button\">Book Room</a></p></div></div>";
		if (count%3==0) { content += "</div>"; }
		count++;
	};

	$("#room_content").html(content);
});