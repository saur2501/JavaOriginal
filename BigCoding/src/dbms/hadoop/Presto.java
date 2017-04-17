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
		con = DriverManager.getConnection("jdbc:presto://192.168.237.137:8081/hive/default","root","");
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT orderkey FROM orders1 LIMIT 10");
		System.out.println(rs.toString());
		while(rs.next()) {
			System.out.println(rs.getInt("orderkey"));
		}
		con.close();
		System.exit(0);
	}
}