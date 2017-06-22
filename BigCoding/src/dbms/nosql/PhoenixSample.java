package dbms.nosql;
import java.sql.*;

public class PhoenixSample {

    public static void main(String[] args) {
        // Create variables
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            // Connect to the database
        	System.out.println("S1");
            connection = DriverManager.getConnection("zookeeperQuorumIP:2181:/hbase-unsecure");
            System.out.println("S2");
            // Create a JDBC statement
            statement = connection.createStatement();
            System.out.println("S3");

            // Execute our statements
            statement.executeUpdate("create table javatest (mykey integer not null primary key, mycolumn varchar)");
            System.out.println("S4");
            statement.executeUpdate("upsert into javatest values (1,'Hello')");
            statement.executeUpdate("upsert into javatest values (2,'Java Application')");
            connection.commit();
            System.out.println("S5");

            // Query for table
            ps = connection.prepareStatement("select * from javatest");
            //ps = connection.prepareStatement("select * from \"TLG_Wide\"");
            System.out.println("S6");
            rs = ps.executeQuery();
            System.out.println("Table Values");
            /*while(rs.next()) {
                Integer myKey = rs.getInt(1);
                String myColumn = rs.getString(2);
                System.out.println("\tRow: " + myKey + " = " + myColumn);
            }*/
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(ps != null) {
                try {
                    ps.close();
                }
                catch(Exception e) {}
            }
            if(rs != null) {
                try {
                    rs.close();
                }
                catch(Exception e) {}
            }
            if(statement != null) {
                try {
                    statement.close();
                }
                catch(Exception e) {}
            }
            if(connection != null) {
                try {
                    connection.close();
                }
                catch(Exception e) {}
            }
        }


    }
}
//mvn- add repositories hortonworks if wanna download mvn presto-core 4.4.0.2.4.2.10-1 for HDP24