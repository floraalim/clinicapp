package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlserver {
	private static Statement stmt;
	static Connection connection=null;
    //create a method to connect to SQL Server
    public static Statement getstatement(String dbUrl, String dbPort, String defaultDatabase,
                                                     String dbUsername, String dbPassword)
    {
         //define a sql connection object
        String JTDS_Driver = "net.sourceforge.jtds.jdbc.Driver";  //use the driver
        try {
            Class.forName(JTDS_Driver);  //we use the jtds class for connection
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String connectionUrl="jdbc:jtds:sqlserver://"+dbUrl+":"+dbPort+";DatabaseName="+defaultDatabase;
        try {
            connection=DriverManager.getConnection(connectionUrl,dbUsername,dbPassword);
            stmt =  connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(!connection.isClosed())  //!connection.isClosed() is the same connection.isClosed==False
                System.out.println("Database connection is live");
        } catch (SQLException e) {
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
	  
	
	
	
	
	public void Updatedatabase(String sql) {
		try {
			
			// Update data value
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		if (connection!= null) {
			 connection.close();
		}
	}
 
	 public static void disconnectSqlServer(Connection con){
	        try {
	            if(!con.isClosed()){
	                con.close();
	                System.out.println("disconnected from SQL Server");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
    
    
}
