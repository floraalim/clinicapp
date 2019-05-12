package basic;

import java.sql.Connection;
import java.sql.Statement;
import org.testng.annotations.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class database {

	// Config information
	static Connection con = null;
	private static Statement stmt;
	public static String DB_URL = "jdbc:mysql://localhost:3306/testdb";
	public static String DB_USER = "root";
	public static String DB_PASSWORD = "";
	public String dbClass = "com.mysql.jdbc.Driver";
	// SQL queries
	//String queryShowCustomers = "SELECT * FROM CUSTOMERS";
	//String queryInsertCustomer = "INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY) VALUES (7, 'Sang', 01, 'AB', 2000.00)";
	//String queryUpdateCustomer = "UPDATE Customers SET ADDRESS = 'VN', SALARY= '10000' WHERE ID = 6";
	//String queryVerifySalaryWithID = "SELECT SALARY FROM CUSTOMERS WHERE ID = 6";
	
	
	public Statement getstatement( ) throws Exception {
		try {
			// Make the database connection
			
			Class.forName(dbClass).newInstance();

			// Get connection to DB
			Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// Statement object to send the SQL statement to the Database
			stmt = con.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
   	
	
	
	public   ResultSet showresult(String sql ) {
		ResultSet res=null;
		try {
			// Get the contents of table from DB
			 res = stmt.executeQuery(sql);

			// Print the all result
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	public   String  getfieldvalue(int  index,String sql) {
		      ResultSet res=null;
		      String cusID="" ;
		      
		try {
			// Get the contents of table from DB
			 res = this.showresult(sql);

			// Print the all result
			
				 cusID = res.getString(index);
				
          
			
			}

		    catch (Exception e) {
			e.printStackTrace();
		}
		return   cusID ;
	}
	  
	
	
	
	
	public int  Updatedatabase(String sql) {
		 int  affectedRows=0;
		try {
			
			// Update data value
			affectedRows=stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return affectedRows;
	}	
	

	public void  Insertdata(String sql) {
		try {			
			// Insert data value
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	 public int delRecord(String sql){
	        int delRecCnt=0;
	        

	       
	        try {
	            delRecCnt=this.stmt.executeUpdate(sql);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        if(delRecCnt!=1)
	            System.out.println("deleting record failed");
	        else System.out.println("one record is deleted");
	        return  delRecCnt;
	    }
	
	public void close() throws Exception {
		
		if (con != null) {
			con.close();
		}
	}

}