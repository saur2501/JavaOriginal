package swings;
/*SQL Video Tutorials

    SQL Video Tutorial Pt 1
    SQL Video Tutorial Pt 2
    SQL Video Tutorial Pt 3
    SQL Video Tutorial Pt 4
    SQL Video Tutorial Pt 5

Setup JDBC in Eclipse

There are a couple of ways to setup JDBC in Eclipse. First You need to get JDBC Connector.

The easiest way on Macs and PCs is to configure the build path in Eclipse:

    In Eclipse Right click on your Project folder
    Click Build Path and Configure Build Path
    Click the Library tab
    Click Add External jars and locate the file named mysql-connector-java-5.1.19-bin.jar

Setup JDBC the Easy Way on Macs

Just copy the jar file above to the directory /System/Library/Java/Extensions. That is it!

Setup JDBC on PCs by Adding to the ClassPath

    Open Control Panel and double click on System
    Click the Advanced Tab
    Click Environment Variables
    At the end of the classpath add ;c:\the location of mysql-connector-java-5.1.19-bin.jar

Code from the Video


// The API for accessing and processing data stored in a database

 

import java.sql.*;

*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Lesson34 {

    public static void main(String[] args) {

         

        // A connection object is used to provide access to a database

         

        Connection conn = null;

         

        try {

            // The driver allows you to query the database with Java

            // forName dynamically loads the class for you

            

            Class.forName("com.mysql.jdbc.Driver");

             

            // DriverManager is used to handle a set of JDBC drivers

            // getConnection establishes a connection to the database

            // You must also pass the userid and password for the database

             

            conn = DriverManager.getConnection("jdbc:mysql://localhost/customer","mysqladm","turtledove");

             

            // Statement objects executes a SQL query

            // createStatement returns a Statement object

             

            Statement sqlState = conn.createStatement();

             

            // This is the query I'm sending to the database

             

            String selectStuff = "Select first_name from customer";

             

            // A ResultSet contains a table of data representing the

            // results of the query. It can not be changed and can

            // only be read in one direction

             

            ResultSet rows = sqlState.executeQuery(selectStuff);

             

            // next is used to iterate through the results of a query

             

            while(rows.next()){

                System.out.println(rows.getString("first_name"));

            }

        }

         

        catch (SQLException ex) {

             

            // String describing the error

             

            System.out.println("SQLException: " + ex.getMessage());

             

            // Vendor specific error code

             

            System.out.println("VendorError: " + ex.getErrorCode());

        }

         

        catch (ClassNotFoundException e) {

            // Executes if the driver can't be found

            e.printStackTrace();

        }

         

    }

}
