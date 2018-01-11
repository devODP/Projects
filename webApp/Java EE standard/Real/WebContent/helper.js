// websocket
var wsUri = "ws://" + document.location.host + "/Real/attendance";
var ws = new WebSocket(wsUri);

// list that will contain form data
var ls = [];

ws.onmessage = function(event) {
	serverMessage.value = event.data;
};

ws.onopen = function() {
	onOpen();
};

function sendMessage() {
	ws.send(textMessage.value);
}

function connectLogout() {
	document.logout.action = "http://" + document.location.host
			+ "/Real/logout";
	ws.close();
}

function connectUpload() {
	if(ls.length == 0){ // if the user chose to upload by browsing
		document.upload.action = "http://" + document.location.host
				+ "/Real/upload";
	}else{ // if the user chose to drag and drop
		readDropZone();
	}
}

function connectInsert() {
	document.insert.action = "http://" + document.location.host
			+ "/Real/insert";
}

function _(id) {
	return document.getElementById(id);
}

function drag_drop(event) {
	event.preventDefault();
	
	for (var i = 0; i < event.dataTransfer.files.length; i++) {
		var formData = new FormData();
		formData.append("file", event.dataTransfer.files[i]);
		formData.set("name", event.dataTransfer.files[i].name);
		ls.push(formData);
	}
}

function readDropZone() {
	for (var i = 0; i < ls.length; i++) {
		// create an http request for each formData
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "http://" + document.location.host + "/Real/upload");
		xhr.send(ls[i]);
	}
	// reset list after all formData are sent
	ls = [];
}