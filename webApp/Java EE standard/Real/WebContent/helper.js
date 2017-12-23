// This javascript file contains functions that are performed by user and a 
// websocket

var wsUri = "ws://" + document.location.host + "/Real/attendance";
var ws = new WebSocket(wsUri);

ws.onmessage = function(event) {
	serverMessage.value = event.data;
};

ws.onopen = function(){
	onOpen();
};

function sendMessage() {
	ws.send(textMessage.value);
}

function connectLogout(){
	document.logout.action = "http://" + document.location.host + "/Real/logout";
	ws.close();
}
		
function connectUpload(){
	document.upload.action = "http://" + document.location.host + "/Real/upload";
}
		
function connectInsert(){
	document.insert.action = "http://" + document.location.host + "/Real/insert";
}
