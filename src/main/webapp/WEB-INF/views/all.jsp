<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Everything</title>
</head>
<body>
<%response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0); %>
${msg }
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cr"%>
<table>
<tr>
<th>RegNo</th><th>Name</th><th>Department</th><th>CGPA</th><th>HSC</th>
<th>Diploma</th><th>SSLC</th><th>Skills</th><th>Certifications</th>
<th>Career</th><th>Mobile Number</th>
<th>Email</th><th>Status</th><th>Actions</th>
</tr>
<cr:forEach var="hai" items="${all }">
<tr>
<td>${hai.regno }</td><td>${hai.name }</td><td>${hai.department }</td>
<td>${hai.cgpa }</td><td>${hai.hsc }</td><td>${hai.diploma }</td>
<td>${hai.sslc }</td><td>${hai.skills }</td><td>${hai.certifications }</td>
<td>${hai.career }</td><td>${hai.mobile }</td>
<td>${hai.email }</td><td>${hai.status }</td>
<td><ul>
<li><a href="edit?id=${hai.regno }">Update</a></li>
<li><a href="delete?id=${hai.regno }">Delete</a></li>
</ul></td>
</tr>
</cr:forEach>
</table>
<center><a href="report">Get Report</a></center>
<a href="home"><h1>Home</h1></a>
<a href="logout"><h1>Logout</h1></a>
</body>
</html>