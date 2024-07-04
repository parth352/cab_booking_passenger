<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>hello welcome to our website</h1>
<form action="/map" method="get">
	<label>Latitude: </label><input type="number" name="latitude" id="lat" step="any"/>
	<label>Longitude: </label><input type="number" name="longitude" id="lon" step="any"/>
	<input type="submit" value="submit"/>
</form>
</body>
</html>