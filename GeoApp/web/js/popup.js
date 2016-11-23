var myMap;
var container;
var content;
var closer;

function init() {
    container = document.getElementById('popup');
    content = document.getElementById('popup-content');
    closer = document.getElementById('popup-closer');
    var overlay = new ol.Overlay(({
        element: container,
        autoPan: true,
        autoPanAnimation: {
            duration: 250
        }
    }));
    myMap = new ol.Map({
        target: 'myMap',
        renderer: 'canvas',
        overlays: [overlay],
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
    closer.onclick = function () {
        overlay.setPosition(undefined);
        closer.blur();
        return false;
    };
    myMap.on('singleclick', function (evt) {
        var coordinate = evt.coordinate;
        var hdms = ol.coordinate.toStringHDMS(ol.proj.transform(coordinate, 'EPSG:900913',
            'EPSG:4326'));
        content.innerHTML = '<p>Posi&ccedil;&atilde;o Atual:</p><code>' + hdms + '</code>';
        overlay.setPosition(coordinate);
    });
}
$(document).ready(init);