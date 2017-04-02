package dbms.hadoop;
//TODO- not tested
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Impala {

	private static Connection con;
	private static Statement stmt;
	public static void main(String[] args) throws Exception {
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		con = DriverManager.getConnection("jdbc:hive2://192.168.237.131:21050/gmo_pdmp;auth=noSasl");
		stmt = con.createStatement();
		stmt.execute("use tpcds");
		stmt.executeQuery("select count(*) from sales");
		con.close();
	}
}
