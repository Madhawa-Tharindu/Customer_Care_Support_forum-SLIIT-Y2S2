package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.DBConnection;

/**
 * Servlet implementation class DeleteReply
 */
@WebServlet("/DeleteReply")
public class DeleteReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReply() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int rid = Integer.parseInt(request.getParameter("rid"));
		
		try {
			
			Connection con = DBConnection.connect();
		
			Statement stm = con.createStatement();
			
			String sql = "DELETE FROM reply WHERE rid=" + rid;
			
			if(stm.executeUpdate(sql) == 1) {
				response.sendRedirect("forum/index.jsp");
			}else {
				response.sendRedirect("forum/index.jsp?r=drf");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
