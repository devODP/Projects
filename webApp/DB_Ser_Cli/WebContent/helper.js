// This javascript file contains functions that are performed by user and a 
// websocket

var wsUri = "ws://" + document.location.host + "/DB_Ser_Cli/index";
var ws = new WebSocket(wsUri);
var para = document.getElementById("messagesTextArea");

ws.onmessage = function(event) {
	var mySpan = document.getElementById("messageGoesHere");
	serverMessage.value = event.data;
	if (serverMessage.value == "Invalid Login") {
		window.location.href = "login.html";
	}
};
		
ws.onopen = function(){
	ws.send(document.location.host.substr(0, document.location.host.indexOf(":")));
};

function sendMessage() {
	ws.send(textMessage.value);
}

function connectLogout(){
	document.logout.action = "http://" + document.location.host + "/DB_Ser_Cli/logout";
}
		
function connectUpload(){
	document.upload.action = "http://" + document.location.host + "/DB_Ser_Cli/upload";
}
		
function connectInsert(){
	document.insert.action = "http://" + document.location.host + "/DB_Ser_Cli/insert";
}