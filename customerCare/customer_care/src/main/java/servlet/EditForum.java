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
 * Servlet implementation class EditForum
 */
@WebServlet("/EditForum")
public class EditForum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditForum() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//getting data 
		int fid = Integer.parseInt(request.getParameter("fid"));
		String topic = new String(request.getParameter("topic"));
		String description = new String(request.getParameter("des"));
		
		try {
			Connection con = DBConnection.connect();
			
			//sql query
			String sql = "UPDATE forum SET topic='"+ topic + "',description='"+ description +"' WHERE fid= '"+ fid+"'";
			Statement stm = con.createStatement();
			
			if(stm.executeUpdate(sql) == 1) {
				response.sendRedirect("forum/index.jsp");
			}else {
				response.sendRedirect("forum/edit.jsp?r=false");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
