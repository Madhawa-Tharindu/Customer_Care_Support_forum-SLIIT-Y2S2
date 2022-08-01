package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.protocol.Resultset;

import connection.DBConnection;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//getting username and password
		String username = new String(request.getParameter("username"));
		String psw = new String(request.getParameter("psw"));
		
		//try catch for db connection
		try {
			Connection con = DBConnection.connect();
			
			String sql = "SELECT cid FROM customer WHERE name='" + username + "' AND psw='" + psw + "'";
			Statement stm = con.createStatement();
			
			ResultSet result = stm.executeQuery(sql);
			
			if(result.next()) {
				
				//making session 
				HttpSession session = request.getSession();
				session.setAttribute("cid", result.getInt("cid"));
				
				//send user to main forum page
				response.sendRedirect("forum/index.jsp?as="+result.getString("cid"));
				
			}else {
				response.sendRedirect("login.jsp?as=false");
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			response.sendRedirect("login.jsp?as="+e);
		}
	}

}
