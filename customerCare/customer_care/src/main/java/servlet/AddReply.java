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
 * Servlet implementation class AddReply
 */
@WebServlet("/AddReply")
public class AddReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReply() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int fid = Integer.parseInt(request.getParameter("addreply"));
		String reply = new String(request.getParameter("reply"));
		
		try {
			
			Connection con = DBConnection.connect();

			//get max id + 1 for new record id
			String sql = "SELECT MAX(rid) AS mid FROM reply;";
			Statement stm = con.createStatement();
			
			ResultSet result = stm.executeQuery(sql);
			
			if(result.next()) {
			
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
				
				String sqlReply = "INSERT INTO reply VALUES("+ (result.getInt("mid")+1) +","+ fid + ","+ cid + ",'"+ reply + "','" + currentTime + "')";
				
				//return 1 if reply add succefully
				if(stm.executeUpdate(sqlReply) == 1) {
					response.sendRedirect("forum/index.jsp");
				}else {
					response.sendRedirect("forum/index.jsp?rf");
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
