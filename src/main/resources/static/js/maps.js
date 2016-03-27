function createMap(tag, latitude, longitude) {
    return new GMaps({
        div: tag,
        lat: latitude,
        lng: longitude
    });
}

function addMarker(map, latitude, longitude, detectionId) {
    var marker = map.addMarker({
        lat: latitude,
        lng: longitude,
        title: '',
        infoWindow: {
            content: "<p>Face encontrada!!</p><img width='100' src='/detection/" + detectionId + "/image' />"
        }
    });
    marker.infoWindow.open(map.map, marker);
}

function getAddress(latitude, longitude, success) {
    $.get("http://maps.googleapis.com/maps/api/geocode/json?sensor=true&latlng=" + latitude + "," + longitude)
        .done(success);
}