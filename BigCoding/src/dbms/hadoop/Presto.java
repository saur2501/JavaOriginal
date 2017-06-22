package dbms.hadoop;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Presto {
	private static Connection con;
	private static Statement stmt;
	public static void main(String[] args) throws Exception {
		Class.forName("com.facebook.presto.jdbc.PrestoDriver");
		con = DriverManager.getConnection("jdbc:presto://192.168.50.134:8081/hive","root","");
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM sample_07 LIMIT 3");
		System.out.println(rs.toString());
		while(rs.next()) {
			System.out.println(rs.getString("description"));
		}
		con.close();
		System.exit(0);
	}
}
//Ref- Presto.io documentation