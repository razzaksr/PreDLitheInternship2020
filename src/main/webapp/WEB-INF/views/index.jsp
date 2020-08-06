<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login to Campus Connect</title>
</head>
<body>
${msg }
<form action="log" method="post">
<table>
<tr><td><input type="text" name="user" placeholder="UserName"></td></tr>
<tr><td><input type="password" name="pass" placeholder="PassWord"></td></tr>
<tr>
<td><input type="submit" value="LogIn"></td>
<td><input type="reset" value="Clear"></td>
</tr>
</table>
</form>
</body>
</html>