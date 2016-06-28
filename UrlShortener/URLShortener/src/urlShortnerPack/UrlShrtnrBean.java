package urlShortnerPack;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

class MyOwnException extends Exception {
	   
	private static final long serialVersionUID = 1L;

	public MyOwnException(String msg){
	      super(msg);
	   }
	}

public class UrlShrtnrBean {
	
	static final String dbUrl    = "jdbc:mysql://127.0.0.1/Sakila";            
	static final String userName = "root";
	static final String pwd      = "Password";
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	String query = null;
	boolean hasResult = false;
	
	public UrlShrtnrBean(){
		try{
			
			conn = DriverManager.getConnection(dbUrl,userName,pwd);
		}catch( SQLException  ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	/* name : createShrtLink
	 * createShrtLink. Method to insert the URL into the master table with next available ID.
	 * it returns urlshrtnr object with ID converted to BASE 36 number
	 */
	
	public urlshrtnr createShrtLink(urlshrtnr us) 
	{
	      try {
	    	  query = "INSERT INTO urlshrtnr(url) VALUES ('" +us.getOrgLink()+  "')";
    	      stmt = conn.createStatement();
    	      stmt.executeUpdate(query);
    	      
              //Record will be inserted with auto incremental url key. get it
    	      query = "SELECT CONV(LAST_INSERT_ID(),10,36) AS short_url";
    	      stmt = conn.createStatement();
    	      rs = stmt.executeQuery(query);
    	      while(rs.next())
  			  {
    	        us.setShrtLinkBase36(rs.getString("short_url"));
    	        hasResult = true;  
  			  }
    	        
    	      
	      } catch( Exception e){
	    	  JOptionPane.showMessageDialog(null, e);
				
	      }
				return us;
	 }
	
	/* name : retrieveOrgLink
	 * retrieveOrgLink. method to search given short link in master db and get original url if available
	 */
	
	public urlshrtnr retrieveOrgLink(urlshrtnr us) throws MyOwnException
	{
		try
		{
		    query = "SELECT url from urlshrtnr where url_id = " + us.getShrtLink();
 	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(query);
	        
	        while(rs.next())
			{
	        	us.setOrgLink(rs.getString("url"));
  	            hasResult = true;
			}
	        if(!hasResult)
  	        {
  	    	  //throw new MyOwnException("Invalid Short URL");
	        	JOptionPane.showMessageDialog(null,"Invalid Short URL");	
  	        }	
	        
		}catch( SQLException  ex){
	    	  JOptionPane.showMessageDialog(null, ex.getMessage());
				
	      }
				return us;    

	}
}
