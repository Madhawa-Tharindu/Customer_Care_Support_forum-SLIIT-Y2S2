<%@page import="java.sql.ResultSet"%>
<%@page import="connection.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	//get forum id from link
	int fid = Integer.parseInt(request.getParameter("fid"));

	Connection con = DBConnection.connect();

	String sql = "SELECT topic,description FROM forum WHERE fid=" + fid;
	
	Statement stm = con.createStatement();
	ResultSet result = stm.executeQuery(sql);
	
	if(result.next()){
		
	}else{
		response.sendRedirect("index.jsp");
	}

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Forum</title>
</head>
<body>

<form method="POST" action="../EditForum">

	Topic <input type="text" name="topic" placeholder="Enter the topic..." style="width:100%" required value="<%=result.getString("topic")%>"><br><br>
	Description <textarea placeholder="Describe your problem" name="des" style="width:100%" required> <%=result.getString("description")%> </textarea><br><br>
	
	<button type="submit" value="<%=fid %>" name="fid" style="width:100%">Update</button>

</form>

</body>
</html>