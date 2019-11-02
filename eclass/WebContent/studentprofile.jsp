<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Page</title>
</head>
<center>
<body style="background-image:url(http://www.sprintonweb.com/wp-content/uploads/2017/12/056-Shady-Water.png)">
<br>
	<h1>
		Welcome
		<%=session.getAttribute("uname")%></h1>
	</br>
<form method="post" action="Student">
  <input type="hidden" name="method" value="printGradepc">
  <h2>Print Grades of a Course</h2>
  Input Course Code:<br>
  <input type="text" name="course_code" >  
  <br><br>
  <input type="submit" value="Submit">
</form>
<form method="post" action="Student">
  <input type="hidden" name="method" value="printGradeps">
  <h2>Print the Grades of a Semester</h2>
  Input Semester number:<br>
  <input type="text" name="semester" >  
  <br><br>
  <input type="submit" value="Submit">
</form>
<form method="post" action="Student">
  <input type="hidden" name="method" value="printGrade">
  <h2>Print all Grades</h2>
  <input type="submit" value="Submit">
</form> 
<br><br>
<form method="post" action="Student">
  <input type="hidden" name="method" value="logout">
   <input type="submit" value="Logout">
</form>
	
</body>
</center>
</html>