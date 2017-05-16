package dbms.hadoop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Spark {
	private static Connection con;
	private static Statement stmt;
	public static void main(String[] args) throws Exception {
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		con = DriverManager.getConnection("jdbc:hive2://192.168.50.143:10015","","");
		stmt = con.createStatement();
		System.out.println("Choosing db");
		stmt.execute("use default");
		System.out.println("Using default");
		ResultSet rs = stmt.executeQuery("select count(*) from sample_07 limit 10");
		System.out.println("Query executed");
		while(rs.next()) {
			System.out.println("ResultSet is " + rs.getInt(1));		//1 for first column
		}
		con.close();
	}
}
// run thrift server- /usr/hdp/current/spark-client/sbin/start-thriftserver.sh --hiveconf hive.server2.thrift.port=10015 --master yarn-client
// run spark client from ambari b4 exec