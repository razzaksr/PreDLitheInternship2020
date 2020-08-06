<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home for Campus Connect</title>
</head>
<body>
<%response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0); %>
<a href="new"><h1>New Candidate</h1></a>
<a href="list"><h1>Listing Everyone</h1></a>
<a href="find"><h1>Fetch Candidate Info</h1></a>
<a href="logout"><h1>Logout</h1></a>
</body>
</html>