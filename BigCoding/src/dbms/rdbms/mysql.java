package dbms.rdbms;
import java.sql.*;

public class mysql{
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/customer";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   
   public static void main(String[] args) throws SQLException {
	   write();
	   writeProcedure();
	   read();
   }
   
   private static void writeProcedure() throws SQLException {
	   CallableStatement cstmt = null;
	   Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	   try {
	      String SQL = "{call getEmpName (?, ?)}";
	      cstmt = conn.prepareCall (SQL);
	      int supplierId = 8;
	      cstmt.setInt(1, supplierId);			//1st parameter is given by suppId
	      cstmt.registerOutParameter(2, Types.INTEGER);
	      cstmt.executeQuery();
	      int productCount = cstmt.getInt(2);
	      System.out.println(productCount);
	   } catch (Exception e) {}
	   finally {
		   if(cstmt != null)
			   cstmt.close();
	   }
   }

public static void write() throws SQLException {	//not tested
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");
	      
	      //STEP 4: Execute a query
	      System.out.println("Inserting records into the table...");
	      stmt = conn.createStatement();
	      
	      String sql = "INSERT INTO Registration " +
	                   "VALUES (100, 'Zara', 'Ali', 18)";
	      stmt.executeUpdate(sql);
	      sql = "INSERT INTO Registration " +
	                   "VALUES (101, 'Mahnaz', 'Fatma', 25)";
	      stmt.executeUpdate(sql);
	      sql = "INSERT INTO Registration " +
	                   "VALUES (102, 'Zaid', 'Khan', 30)";
	      stmt.executeUpdate(sql);
	      sql = "INSERT INTO Registration " +
	                   "VALUES(103, 'Sumit', 'Mittal', 28)";
	      stmt.executeUpdate(sql);
	      System.out.println("Inserted records into the table...");

	      conn.commit();
       } catch (SQLException e) {
		   if (conn != null) {
		        conn.rollback();
		   }
           e.printStackTrace();
       }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
   }
   
   public static void read() throws SQLException {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      Class.forName("com.mysql.jdbc.Driver");					//STEP 2: Register JDBC driver
	      System.out.println("Connecting to database...");			
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);		//STEP 3: Open a connection

	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();							//STEP 4: Execute a query
	      String sql;
	      sql = "SELECT id, first, last, age FROM Employees";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("id");
	         int age = rs.getInt("age");
	         String first = rs.getString("first");
	         String last = rs.getString("last");

	         //Display values
	         System.out.print("ID: " + id);
	         System.out.print(", Age: " + age);
	         System.out.print(", First: " + first);
	         System.out.println(", Last: " + last);
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	      conn.commit();
       } catch (SQLException e) {
		   if (conn != null) {
		        conn.rollback();
		   }
           e.printStackTrace();
       }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	    	  if(conn!=null)
	    		  conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
   }//end execute
}//end FirstExample
