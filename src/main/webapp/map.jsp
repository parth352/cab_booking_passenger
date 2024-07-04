<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Google Maps Example</title>
    <style>
        /* Ensure the map container fills the entire screen */
        #map {
            height: 70vh; /* Use 100% of viewport height */
            width: 90%; /* Use 100% of viewport width */
        }
    </style>
</head>
<body>
<h1>Google maps view</h1>
    <div id="map"></div>
    
    <!-- Load the Google Maps JavaScript API with your API key -->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCogKPw1QkVXs4OQjsoEMyGgUlQjqXscrY&callback=initMap" async defer></script>
    
    <!-- Your custom JavaScript -->
    <script>
        // Initialize and display the map
        function initMap() {
            // Get latitude and longitude from URL parameters
            var urlParams = new URLSearchParams(window.location.search);
            var lat = parseFloat(urlParams.get('latitude'));
            var lng = parseFloat(urlParams.get('longitude'));

            // Check if lat and lng are valid numbers
           // if (isNaN(lat) || isNaN(lng)) {
           //     console.error('Invalid lat or lng parameters');
          //      return;
            //}

            // Create a new map centered at the specified location
            var map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: lat, lng: lng},
                zoom: 15 // Zoom level (0 = world view, higher numbers = closer)
            });

            // Add a marker at the specified location
         
        }
    </script>
</body>
</html>