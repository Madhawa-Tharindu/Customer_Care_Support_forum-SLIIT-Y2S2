<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="connection.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	int rid = Integer.parseInt(request.getParameter("rid"));

	Connection con = DBConnection.connect();
	
	String sql = "SELECT reply FROM reply WHERE rid="+rid;
	
	Statement stm = con.createStatement();
	ResultSet result = stm.executeQuery(sql);
	
	String reply = new String(" ");
	if(result.next()){
		reply = new String(result.getString("reply"));
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Reply</title>
</head>
<body>

<form method="post" action="../EditReply">

	<textarea name="reply" placeholder="Enter Reply" style="width:100%">
		<%=reply %>
	</textarea><br>
	
	<button type="submit" name="rid" value="<%=rid %>">Update</button>

</form>

</body>
</html>