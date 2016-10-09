'use strict';

function initMap(address) {
	// console.log("Batata")
  var directionsService = new google.maps.DirectionsService;
  var directionsDisplay = new google.maps.DirectionsRenderer;
  var map = new google.maps.Map(document.getElementById('mapbus'), {
    zoom: 15,
    center: {lat: -25.412916, lng: -49.267349}
  });
  directionsDisplay.setMap(map);
  // directionsDisplay.setPanel(document.getElementById('right-panel'));
  
  // console.log(calculateAndDisplayRoute(directionsService, directionsDisplay, address));
  return calculateAndDisplayRoute(directionsService, directionsDisplay, address);
  // document.getElementById('start').addEventListener('change', onChangeHandler);
  // document.getElementById('end').addEventListener('change', onChangeHandler);
}

function calculateAndDisplayRoute(directionsService, directionsDisplay, address) {
	// console.log(address);
  directionsService.route({
    origin: "Rua Deputado Mario de Barros, 1290, Juvevê",
    destination: address,
    travelMode: google.maps.TravelMode.DRIVING
  }, function(response, status) {
  	// console.log(status);
    if (status === google.maps.DirectionsStatus.OK) {
      directionsDisplay.setDirections(response);
      return true;
    } else {
      return false;
    }
  });
}
//# sourceMappingURL=main.js.map
