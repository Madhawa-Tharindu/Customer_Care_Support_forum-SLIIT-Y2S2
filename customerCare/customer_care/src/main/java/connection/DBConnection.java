package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection connect()
		throws SQLException, ClassNotFoundException
	{
		// Database Connection details
		String dbdriver = "com.mysql.jdbc.Driver";
		String dburl = "jdbc:mysql:// localhost:3306/";
		
		// Database name to username and password
		String db = "customer_care";
		String username = "root";
		String password = "12345";

		Class.forName(dbdriver);
		Connection con = DriverManager.getConnection(dburl + db,username,password);
		
		return con;
	}
}

