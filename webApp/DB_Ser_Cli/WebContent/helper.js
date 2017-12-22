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
	getUserIP(function(ip){
		ws.send(ip);
	});
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

function getUserIP(privateIP) {
    //compatibility for firefox, chrome and IE Edge
    var PeerConnection = window.mozRTCPeerConnection || window.RTCPeerConnection || window.webkitRTCPeerConnection;
    var pc = new PeerConnection({
        iceServers: []
    }), noop = function() {}, localIPs = {},
    ipRegex = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/g,
    key;
    function iterateIP(ip) {
        if (!localIPs[ip]){
        	privateIP(ip);
        }
        localIPs[ip] = true;
    }
    //create a data channel for IP address transmition
    pc.createDataChannel("Private IP address");
    // create offer and set local description
    pc.createOffer().then(function(sdp) {
        sdp.sdp.split('\n').forEach(function(line) {
            if (line.indexOf('candidate') < 0){
            	return;
            }
            line.match(ipRegex).forEach(iterateIP);
        });
        
        pc.setLocalDescription(sdp);
    }).catch(function(e) {
        console.error(e);
    });
    //listen for candidate events
    pc.onicecandidate = function(ice) {
        if (!ice || !ice.candidate || !ice.candidate.candidate || !ice.candidate.candidate.match(ipRegex)){
        	return;
        }
        ice.candidate.candidate.match(ipRegex).forEach(iterateIP);
    };
}