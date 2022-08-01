<%@page import="get.Forum"%>
<%@page import="java.util.ArrayList"%>
<%@page import="get.GetForum"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="connection.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%

session = request.getSession(false);

//validate user
int cid = 0;
if(session == null || session.getAttribute("cid") == null){
	response.sendRedirect("../login.jsp");
}else{
	 cid = (int)session.getAttribute("cid");
}


//changing latest and oldest
String order = new String();

try{
	String getOrder = new String(request.getParameter("order"));
	
	if(getOrder.equals("old")){
		order = "ASC";
	}else{
		order = "DESC";
	}
}catch(Exception e){
	order = "DESC";
}

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forums</title>

<style>
.forum div{
	border:1px solid #000;
	padding:5px;
}
/* Style the footer */
.footer {
  background-color: black;
  padding: 20px;
}
</style>

</head>

<body style="background:#E4E9F7">

<%@ include file="navbar.html"%>
<a href="../logout.jsp">
	<button type="button" name="logout">Logout</button>
</a>

<!-- Add new link -->
<br>
<a href="addForum.jsp">
	<input type="submit" value="Add New Forum" style="width:100%">
</a>
<br>


<!-- Order change form -->
<form method="POST" action="index.jsp">

<button type="submit" name="order" value="lt">Latest</button>
<button type="submit" name="order" value="old">Oldest</button>

</form><br><br>

<div class="forum">

<%
	ArrayList<Forum> data = GetForum.get(order);

	Connection con = DBConnection.connect();

	//getting forum data
	for(int i = 0;i < data.size();i++){
		
%>


	<div>
		<p><b><%=data.get(i).getName() %></b>	   
		<%=data.get(i).getDatetime() %></p>	

		<b><%=data.get(i).getTopic() %>	</b><br>

		<%=data.get(i).getDescription() %>	
	</div>
	
	
	<%
		if(data.get(i).getCid() == cid){
	%>
	<div>
	
		<a href="edit.jsp?fid=<%=data.get(i).getFid() %>"><button type="submit" name="cBtn">Edit</button></a>
		<form method="POST" action="../DeleteForum" style="float:left;"><button type="submit" name="fid" value="<%=data.get(i).getFid() %>">Delete</button></form>
	
	</div>
	<% 
		}
	%>
	
	
	<div style="margin-left: 50px;">
		<%
		
			String sqlReply = "SELECT name,reply,datetime,rid,r.cid FROM reply r,customer c WHERE r.cid=c.cid AND fid=" + data.get(i).getFid();
			Statement stmReply = con.createStatement();
			ResultSet resultReply = stmReply.executeQuery(sqlReply);
			
			while(resultReply.next()){
		%>
		
			<p><b><%=resultReply.getString("name") %></b> <%=resultReply.getString("datetime") %></p>
			<p><%=resultReply.getString("reply") %></p>
			
		<%
			if(resultReply.getInt("cid") == cid){
		%>
		
			<a href="editReply.jsp?rid=<%=resultReply.getInt("rid")%>">Edit</a>
			<form method="post" action="../DeleteReply" style="float:left;"><button value="<%=resultReply.getInt("rid")%>" name="rid">Delete</button></form>
		<%
			}
		%>
			<hr>
		<%
			}
		
		%>
	</div>
	
	<div>
		<form method="POST" action="../AddReply">
			<textarea rows="3" placeholder="Write the reply..." style="width:90%;margin:10px;" name="reply"></textarea>
			<button type="submit" name="addreply" value="<%=data.get(i).getFid() %>">Reply</button>	
		</form>
	</div>
	
	<br><br><br>


<%
	}
	con.close();
%>

</div>
<div class="footer">
  <p>Footer</p>
</div>
</body>
</html>