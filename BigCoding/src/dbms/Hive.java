package dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Hive {
	private static Connection con;
	private static Statement stmt;
	public static void main(String[] args) throws Exception {
		String engineType = "tez";
		Class.forName("org.apache.hive.jdbc.HiveDriver");																										//org.apache.hive.jdbc.HiveDriver
		con = DriverManager.getConnection("jdbc:hive2://192.168.237.131:10000","hdfs","");
		stmt = con.createStatement();
		stmt.execute("set hive.execution.engine=tez");
		if(engineType.equals("mr")) {
			String setJobName = "set mapred.job.name=q1";
			System.out.println(setJobName);
			stmt.execute(setJobName);
		}
		stmt.execute("use tpcds");
		stmt.executeQuery("select count(*) from sales");
		con.close();		
	}
}
