var stompClient = null;

//function setConnected(connected) {
//    $("#connect").prop("disabled", connected);
//    $("#disconnect").prop("disabled", !connected);
//    if (connected) {
//        $("#conversation").show();
//    }
//    else {
//        $("#conversation").hide();
//    }
//    $("#greetings").html("");
//}

function connect() {
	console.log("here");
    var socket = new SockJS('/NapkinStudio/napkin-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
//        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/napkin-notifications', function (greeting) {
        	console.log("SAP UPDATE!!!");
        	$("#websocket").show();
        	window.setTimeout(function() {
        		$("#websocket").hide();
        	}, 5000);
//            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/question-to-napkin", {}, JSON.stringify({'name': $("#name").val()}));
}

//function showGreeting(message) {
//	console.log("message > " + message);
//    $("#greetings").append("<tr><td>" + message + "</td></tr>");
//}
$(document).ready(function () {
	connect();
});
//$(function () {
//    $("form").on('submit', function (e) {
//        e.preventDefault();
//    });
//    $( "#connect" ).click(function() { connect(); });
//    $( "#disconnect" ).click(function() { disconnect(); });
//    $( "#send" ).click(function() { sendName(); });
//});