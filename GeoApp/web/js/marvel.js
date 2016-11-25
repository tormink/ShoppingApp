var webServiceUrl = "http://localhost:8080/WebApp/services/application/";
var user; //user object
var myMap; //map object
var searchUser = "";
var vectorLayer;
var popupElem = document.getElementById('popup');
var closer = document.getElementById('popup-closer'); // botão para fechar
var content = document.getElementById('popup-content');
var jsonPositions;
var marvelAvatarUrl = "";

function init(userQuery) {

    $.getJSON(webServiceUrl + "user/" + userQuery, function (userJSON) {
        user = userJSON[0];
        console.log(user);
        //array with positions
        var positionsUrl = webServiceUrl + "positions/" + user.username;
        $.getJSON(positionsUrl, function (data) {
            jsonPositions = data;
            console.log(jsonPositions);

            $.getJSON(webServiceUrl+"marvel/" + user.icon, function (marvelJSON) {
                var results = marvelJSON.data.results;
                console.log(results);
                marvelAvatarUrl = results[0].thumbnail.path + '.' + results[0].thumbnail.extension;
                console.log(marvelAvatarUrl);
                newPosition(jsonPositions);
            });
        })
    });

    myMap.on('pointermove', function (e) {
        if (e.dragging) {
            $(content).popover('destroy');
            return;
        }
        var pixel = myMap.getEventPixel(e.originalEvent);
        var hit = myMap.hasFeatureAtPixel(pixel);
    });

}

function newPosition(positions) {
    var features = [];
    for (var i = 0; i < positions.length; i++) {
        var iconFeature = new ol.Feature({
            geometry: new ol.geom.Point([(positions[i].latitude), (positions[i].longitude)]),
            name: positions[i].login,
            timestamp: positions[i].timestamp,
            avatar: user.icon,
            lon: positions[i].longitude,
            lat: positions[i].latitude
        });
        var iconStyle = new ol.style.Style({
            image: new ol.style.Icon(({
                anchor: [0.5, 46],
                anchorXUnits: 'fraction',
                anchorYUnits: 'pixels',
                opacity: 0.75,
                src: 'data/r2d2.png'
            }))
        });
        iconFeature.setStyle(iconStyle);
        features.push(iconFeature);
    }

    var vectorSource = new ol.source.Vector({
        features: features
    });

    vectorLayer = new ol.layer.Vector({
        source: vectorSource
    });
    myMap.addLayer(vectorLayer);

}

function insert(outToService, positionJson) {
    var positionData = outToService;
    var newPositionUrl = webServiceUrl + "newposition";
    $.ajax({
        url: newPositionUrl,
        data: outToService,
        type: 'PUT',
        contentType: "text/xml",
        dataType: "text",
        success: function () {
            newPosition(positionJson);
        },
        error: function (e) {
            console.log(e.message);
        }
    });
}
$(document).ready(function () {

    myMap = new ol.Map({
        target: 'myMap',
        renderer: 'canvas',
        view: new ol.View({
            projection: 'EPSG:900913',
            center: [-5193252.61684, -2698365.38923],
            zoom: 18
        })
    });
    var openStreetMapLayer = new ol.layer.Tile({
        source: new ol.source.OSM()
    });
    myMap.addLayer(openStreetMapLayer);

    var popup = new ol.Overlay({
        element: popupElem,
        positioning: 'bottom-center',
        stopEvent: false
    });
    myMap.addOverlay(popup);

    closer.onclick = function () {
        popup.setPosition(undefined);
        closer.blur();
        return false;
    };
    myMap.on('singleclick', function (event) {
        var feature = myMap.forEachFeatureAtPixel(event.pixel,
            function (feature, layer) {
                return feature;
            });
        if (feature) {
            popup.setPosition(event.coordinate);
            var login = feature.get('name');
            var avatar = feature.get('avatar');
            var timestamp = feature.get('timestamp');
            var lat = feature.get('lat');
            var lon = feature.get('lon');

            var coordinate = [lat, lon];
            console.log(coordinate);
            var hdms = ol.coordinate.toStringHDMS(ol.proj.transform(coordinate, 'EPSG:900913', 'EPSG:4326'));

            content.innerHTML =
                "<div class=\"container-fluid\">" +
                "<div class=\"row\">" +
                "<div class=\"col-md-4\" id=\"avatarcontainer\">" +
                "<div id=\"avataricon\"></div>" +
                "</div>" +
                "<div class=\"col-md-8\">" +
                "<p>Login: " + login + "</p>" +
                "<p>Created on: " + timestamp + "</p>" +
                "<p>Location: <br><code>" + hdms + "</code></p>" +
                "<p>Character in avatar: " + avatar.replace("%20"," ") + "</p>" +
                "</div>" +
                "</div>" +
                "</div>";
            $("#avataricon").css("background-image", "url(\"" + marvelAvatarUrl + "\")");
            popup.setPosition(event.coordinate);

        } else {
            var dateTime = moment(dateTime).format("YYYY-MM-DD HH:mm:ss");
            var data = dateTime = dateTime.toString();
            /// TIME STAMP AQUI EM CIMA, var data. 

            var coordinate = event.coordinate;
            var template = '<position><id>0</id><login>' + user.username + '</login><timestamp>' + data + '</timestamp><latitude>{x}</latitude><longitude>{y}</longitude></position>';

            var outToService = ol.coordinate.format(coordinate, template, 6);
            var jsonTemplate = "{x}|{y}";
            jsonTemplate = ol.coordinate.format(coordinate, jsonTemplate, 6);
            jsonTemplateArr = jsonTemplate.split("|");
            var positionJson = {
                "id": 0,
                "login": user.username,
                "latitude": parseInt(jsonTemplateArr[0]),
                "longitude": parseInt(jsonTemplateArr[1]),
                "timestamp": data
            }
            insert(outToService, [positionJson]);


        }
    });

    $("#searchform").submit(function (e) {
        e.preventDefault();

        searchUser = $(this).serializeArray().reduce(function (obj, item) {
            obj[item.name] = item.value;
        });
        console.log(searchUser.value);
        init(searchUser.value);
    })

});