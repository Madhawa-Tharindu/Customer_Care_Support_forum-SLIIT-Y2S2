package get;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.DBConnection;

public class GetForum {

	public static ArrayList<Forum> get(String order){
		
		ArrayList<Forum> data = new ArrayList<Forum>();
		
		Connection con;
		try {
			
			con = DBConnection.connect();
			
			String sql = "SELECT topic,description,f.cid,fid,datetime,c.name FROM forum f,customer c WHERE f.cid=c.cid ORDER BY datetime " + order;

			Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery(sql);
			
			//getting forum data
			while(result.next()){
			
				Forum obj = new Forum(result.getString("topic") , result.getString("description") , result.getInt("cid") , result.getInt("fid") , result.getString("datetime") , result.getString("name"));
			
				data.add(obj);
			
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	


		return data;
		
	}
	
}
