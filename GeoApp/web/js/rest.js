var myMap;

function init() {
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
    var iconFeature = new ol.Feature({
        geometry: new ol.geom.Point([-5193252.61684, -2698365.38923]),
        name: 'mackTest@mack.br'
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
    var vectorSource = new ol.source.Vector({
        features: [iconFeature]
    });
    var vectorLayer = new ol.layer.Vector({
        source: vectorSource
    });
    myMap.addLayer(vectorLayer);
    var popupElem = document.getElementById('popup');
    var popup = new ol.Overlay({
        element: popupElem,
        positioning: 'bottom-center',
        stopEvent: false
    });
    myMap.addOverlay(popup);
    myMap.on('click', function (evt) {
        var feature = myMap.forEachFeatureAtPixel(evt.pixel,
            function (feature, layer) {
                return feature;
            });
        if (feature) {
            popup.setPosition(evt.coordinate);
            var xmlString;
            var login = feature.get('name');
            var urlString = 'http://localhost:8080/WebApp/services/application/positions/';
            console.log(urlString);
            urlString = urlString.concat(login);
            console.log(urlString);
            $.ajax({
                url: urlString,
                data: {
                    format: 'xml'
                },
                success: function (data) {
                    xmlString = (new XMLSerializer()).serializeToString(data);
                    console.log(xmlString);
                    $(popupElem).popover({
                        'placement': 'top',
                        'html': true,
                        'content': '<p>' + xmlString + '</p>'
                    });
                    $(popupElem).popover('show');
                },
                error: function (e) {
                    console.log(e.message);
                },
                type: 'GET'
            });
        } else {
            $(popupElem).popover('destroy');
        }
    });
    myMap.on('pointermove', function (e) {
        if (e.dragging) {
            $(popupElem).popover('destroy');
            return;
        }
        var pixel = myMap.getEventPixel(e.originalEvent);
        var hit = myMap.hasFeatureAtPixel(pixel);
    });
}
$(document).ready(init);