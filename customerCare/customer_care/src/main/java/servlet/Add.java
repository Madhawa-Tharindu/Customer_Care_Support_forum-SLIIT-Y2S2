package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.DBConnection;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String topic = new String(request.getParameter("topic"));
		String description = new String(request.getParameter("des"));
		
		try {
			
			Connection con = DBConnection.connect();
			
			//get max id + 1 for new record id
			String sql = "SELECT (MAX(fid)+1) AS mid FROM forum;";
			Statement stm = con.createStatement();
			
			ResultSet result = stm.executeQuery(sql);
			
			if(result.next()) {
				
				int mid = result.getInt("mid");
				
				//get current time
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();  
				String currentTime = new String(dtf.format(now));  
				
				//get user ID
				HttpSession session = request.getSession(false);

				int cid = 0;
				if(session == null){
					response.sendRedirect("../login.jsp");
				}else{
					 cid = (int)session.getAttribute("cid");
				}
				
				//insert forum
				String insertSql = "INSERT INTO forum VALUES(" + mid + "," + cid + ",'"+ topic +"','" + description  + "','" + currentTime +"')";

				//return 1 if record inserted successfully
				if(stm.executeUpdate(insertSql) == 1) {
					response.sendRedirect("forum/index.jsp");
				}else {
					response.sendRedirect("forum/addForum.jsp?r=false");
				}
				
			}

			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
