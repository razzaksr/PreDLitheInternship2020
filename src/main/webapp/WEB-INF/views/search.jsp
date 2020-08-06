<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search</title>
</head>
<body>
<%response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0); %>
<form action="read" method="post">
<table>
<tr><td><input type="text" name="regno" placeholder="Select By register number"></td></tr>
<tr><td><select name="department">
<option>Select Any Department</option><option>Computers</option>
<option>Electronics</option><option>Electricals</option>
<option>Information</option><option>Mechanical</option>
</select></td></tr>
<tr><td><select name="career">
<option>Select Any Career</option><option>IT</option>
<option>Core</option><option>Higher Education</option>
<option>Entrepreneur</option>
</select></td></tr>
<tr><td><select name="status">
<option>Select Any Status</option><option>Placed</option>
<option>Not Placed</option>
</select></td></tr>
<tr>
<td><input type="submit" value="Read"></td>
<td><input type="reset" value="Clear"></td>
</tr>
</table>
</form>
<a href="home"><h1>Home</h1></a>
<a href="logout"><h1>Logout</h1></a>
</body>
</html>