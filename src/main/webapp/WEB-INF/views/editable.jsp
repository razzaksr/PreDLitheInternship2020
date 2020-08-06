<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editable One</title>
</head>
<body>
<%response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0); %>
${msg }
<form action="update" method="post">
<table>
<tr><td><input type="text" name="regno" value="${got.regno }"></td></tr>
<tr><td><input type="text" name="name" value="${got.name }"></td></tr>
<tr><td><input type="text" name="department" value="${got.department }"></td></tr>
<tr><td><input type="text" name="cgpa" value="${got.cgpa }"></td></tr>
<tr><td><input type="text" name="hsc" value="${got.hsc }"></td></tr>
<tr><td><input type="text" name="diploma" value="${got.diploma }"></td></tr>
<tr><td><input type="text" name="sslc" value="${got.sslc }"></td></tr>
<tr><td><input type="text" name="skills" value="${got.skills }"></td></tr>
<tr><td><input type="text" name="certifications" value="${got.certifications }"></td></tr>
<tr><td><input type="text" name="mobile" value="${got.mobile }"></td></tr>
<tr><td><input type="text" name="email" value="${got.email }"></td></tr>
<tr><td><input type="text" name="career" value="${got.career }"></td></tr>
<tr><td><input type="text" name="status" value="${got.status }"></td></tr>
<tr>
<td><input type="submit" value="Change"></td>
<td><input type="reset" value="Cancel"></td>
</tr>
</table>
</form>
<a href="home"><h1>Home</h1></a>
<a href="logout"><h1>Logout</h1></a>
</body>
</html>