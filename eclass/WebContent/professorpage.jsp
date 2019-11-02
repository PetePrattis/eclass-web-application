<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page</title>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 50%;
}

td, th {
    border: 1px solid #5F9EA0;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #5F9EA0;
}
</style>
</head>
<center>
<body style="background-image:url(http://www.sprintonweb.com/wp-content/uploads/2017/12/056-Shady-Water.png)">
<br>
	<h1>
		Page of 
		<%=session.getAttribute("uname")%></h1>
	</br>
<br>
	<table>
		<tr>
			<th>First Name</th>
			<th>Surname</th>
			<th>Course Code</th>
			<th>Grade</th>
		</tr>
		<%= request.getAttribute("html") %>.
	</table>
</body>
</center>
</html>