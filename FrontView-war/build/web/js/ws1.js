var webSocket = new WebSocket("ws://localhost:8080/FrontView-war/example");
webSocket.onopen = function() {
    alert("Web socket is connected!");
};
webSocket.onmessage = function(event) {
    alert("Message received : " + event.data);
};
webSocket.onclose = function() {
    alert("Connection is closed...");
}
