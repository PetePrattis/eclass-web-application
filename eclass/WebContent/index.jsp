<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Class</title>
</head>
<center>
<body style="background-image:url(http://www.sprintonweb.com/wp-content/uploads/2017/12/056-Shady-Water.png)">
<br>
<form method="post" action="Student">
  <input type="hidden" name="method" value="login">
  <h1 style="color:#990000">Welcome to the E-Class</h1><br>
  <h2>Login as a Student</h2>
  User Name:<br>
  <input type="text" name="uname" >
  <br>
  Password:<br>
  <input type="password" name="AM">
  <br><br>
  <input type="submit" value="Submit">
</form> 
  <br><br>
<form method="post" action="Professor">
  <input type="hidden" name="method" value="login">
  <h2>Login as a Professor</h2>
  User Name:<br>
  <input type="text" name="uname" >
  <br>
  Password:<br>
  <input type="password" name="AM">
  <br><br>
  <input type="submit" value="Submit">
</form>
<form method="post" action="Secretary">
  <input type="hidden" name="method" value="login">
  <h2>Login as a Secretary</h2>
  User Name:<br>
  <input type="text" name="uname" >
  <br>
  Password:<br>
  <input type="password" name="AM">
  <br><br>
  <input type="submit" value="Submit">
</form> 
<br><br>
<form method="post" action="Student">
  <input type="hidden" name="method" value="exit">
  <input type="submit" value="Exit" onclick="javascript:window.close();">
</form> 
<br><br>

</body>
</center>
</html>