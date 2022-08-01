<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Forum</title>
</head>
<style>
/* Style the footer */
.footer {
  background-color: black;
  padding: 20px;
  position:relative;
  margin-top:160px;
  
}

</style>
<body style="background:#E4E9F7">

<%@ include file="navbar.html"%>
<form method="POST" action="../Add">

	Topic <input type="text" name="topic" placeholder="Enter the topic..." style="width:100%" required><br><br>
	Description <textarea placeholder="Describe your problem" name="des" style="width:100%" rows="20" cols="50" required></textarea><br><br>
	
	<input type="submit" value="Add" style="width:100%">

</form>

</body>
<div class="footer">
  <p>Footer</p>
</div>
</html>