package dbms.rdbms;

import java.util.Properties;
import java.sql.*;

public class PipelineDBStreaming {

  static final String HOST = "localhost";		//pipeline being installed on local VM
  static final String DATABASE = "pipeline";	//default database
  static final String USER = "influx";			//check in /l in pipeline client in linux vm. and pg_hba.conf (ps aux showed no pipeline user!!)
  static final String PASSWORD="pipeline";		//default password

  public static void main(String[] args) throws SQLException {

    // Connect to "test" database on port 5432
    String url = "jdbc:postgresql://" + HOST + ":5432/" + DATABASE;
    ResultSet  rs;
    Properties props = new Properties();

    props.setProperty("user", USER);
    props.setProperty("password", PASSWORD);
    Connection conn = DriverManager.getConnection(url, props);

    Statement stmt = conn.createStatement();
    stmt.executeUpdate(
      "CREATE CONTINUOUS VIEW v6 AS SELECT x::integer, COUNT(*) FROM astream GROUP BY x");

    for (int i=0; i<100000; i++)
    {
      // 10 unique groupings
      @SuppressWarnings("unused") int x = i % 10;

      // INSERT INTO stream (x) VALUES (x)
      stmt.addBatch("INSERT INTO astream VALUES (0,1,2)");
    }

    stmt.executeBatch();

    rs = stmt.executeQuery("SELECT * FROM v6");
    while (rs.next())
    {
      int id = rs.getInt("x");
      int count = rs.getInt("count");

      System.out.println(id + " = " + count);
    }

    // Clean up
    stmt.executeUpdate("DROP CONTINUOUS VIEW v6");
    conn.close();
  }
}
/* 
 * Pipeline DB fully supports postgresql syntax being a fork- uses postgresql client (mvn dependency same)
 * but reverse is not true. We can use Postgresql jar, pipelinedb's credentials and write pipelinedb's streaming query to contact pipeline and it will respond.
 * ensure- credentials; astream stream exists within database and v6 continuous view does not exist
 * */