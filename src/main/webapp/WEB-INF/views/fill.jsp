<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sheet Fill</title>
</head>
<body>
<%response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0); %>
${msg }
<%@taglib prefix="hai" uri="http://www.springframework.org/tags/form" %>
<hai:errors path="candidates.*"/>
<form action="add" method="post">
<table>
<tr><td><input type="text" name="regno" placeholder="RegNo of Canidate"></td></tr>
<tr><td><input type="text" name="name" placeholder="Name of Canidate"></td></tr>
<tr><td><input type="text" name="department" placeholder="DEpartment of Canidate"></td></tr>
<tr><td><input type="text" name="cgpa" placeholder="CGPA of Canidate"></td></tr>
<tr><td><input type="text" name="hsc" placeholder="HSC of Canidate"></td></tr>
<tr><td><input type="text" name="diploma" placeholder="Diploma of Canidate"></td></tr>
<tr><td><input type="text" name="sslc" placeholder="SSLC of Canidate"></td></tr>
<tr><td><input type="text" name="skills" placeholder="Skills of Canidate"></td></tr>
<tr><td><input type="text" name="certifications" placeholder="certifications of Canidate"></td></tr>
<tr><td><input type="text" name="mobile" placeholder="MobileNumber of Canidate"></td></tr>
<tr><td><input type="text" name="email" placeholder="Email of Canidate"></td></tr>
<tr><td><input type="text" name="career" placeholder="Intrest of Canidate"></td></tr>
<tr><td><input type="text" name="status" placeholder="Satus of Canidate"></td></tr>
<tr>
<td><input type="submit" value="Enroll"></td>
<td><input type="reset" value="Cancel"></td>
</tr>
</table>
</form>
<a href="home"><h1>Home</h1></a>
<a href="logout"><h1>Logout</h1></a>
</body>
</html>