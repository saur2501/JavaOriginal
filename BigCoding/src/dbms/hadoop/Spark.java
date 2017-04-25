package dbms.hadoop;
//TODO- not tested
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Spark {
	private static Connection con;
	private static Statement stmt;
	public static void main(String[] args) throws Exception {
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		con = DriverManager.getConnection("jdbc:hive2://192.168.237.140:10015","spark","");
		stmt = con.createStatement();
		stmt.execute("use tpcds_text_2");
		stmt.executeQuery("select count(*) from call_center limit 10");
		con.close();
	}
}
