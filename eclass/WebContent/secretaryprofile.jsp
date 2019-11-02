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
<form method="post" action="Secretary">
  <input type="hidden" name="method" value="viewCourse">
  <h2>View Courses of the Uni</h2>
  <input type="submit" value="Submit">
</form>
<form method="post" action="Secretary">
  <input type="hidden" name="method" value="getCourse">
  <h2>Assign Course to a Professor</h2>  
  <input type="submit" value="Submit">
</form>
<form method="post" action="Secretary">
  <input type="hidden" name="method" value="createStudent">
  <h2>Create a Student</h2>  
  <input type="submit" value="Submit">
</form>
<form method="post" action="Secretary">
  <input type="hidden" name="method" value="createProfessor">
  <h2>Create a Professor</h2>  
  <input type="submit" value="Submit">
</form>
<form method="post" action="Secretary">
  <input type="hidden" name="method" value="createCourse">
  <h2>Create a Course</h2>  
  <input type="submit" value="Submit">
</form>
<br><br>
<form method="post" action="Secretary">
  <input type="hidden" name="method" value="logout">
   <input type="submit" value="Logout">
</form>
</body>
</center>
</html>