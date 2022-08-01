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
 * Servlet implementation class DeleteForum
 */
@WebServlet("/DeleteForum")
public class DeleteForum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteForum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int fid = Integer.parseInt(request.getParameter("fid"));
		
		try {
			Connection con = DBConnection.connect();
			
			//when delete forum first need to delete reply's of forum after that forum
			String sqlReply =  "DELETE FROM reply WHERE fid=" + fid;
			String sqlForum =  "DELETE FROM forum WHERE fid=" + fid;
			
			Statement stm = con.createStatement();
			
			stm.executeUpdate(sqlReply);
			
			if(stm.executeUpdate(sqlForum) == 1) {
				response.sendRedirect("forum/index.jsp");
			}else {
				response.sendRedirect("forum/index.jsp?r=false");
			}
			
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
	}

}
