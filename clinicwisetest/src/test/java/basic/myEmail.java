package basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.testng.annotations.Test;
public class myEmail {
	
	 String  host="smtp.gmail.com" ;
	 String username="alimjan74@gmail.com";
	 String  password="paz8187527";
	 String from="alimjan74@gmail.com";
	 Email email=null;
	 
    public myEmail() {
		super();
	}


	public void setup( )
       {
       
         email=new SimpleEmail();
       
        email.setHostName(this.host);
       
        email.setAuthenticator(new DefaultAuthenticator(this.username,this.password));
        //Set email host SSL to true
        email.setSSL(true);
        }
    
    
    public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public myEmail(String host, String username, String password) {
		super();
		this.host = host;
		this.username = username;
		this.password = password;
	}


	public void sendmail(String from,String to,String subject,String message  ) {
        try {
            //Set From email address
            email.setFrom(from);
          
        } catch (EmailException e) {
            e.printStackTrace();
        }
        //Set email Subject line
        email.setSubject(subject);
        try {
            //Set Email Message
            email.setMsg(message);
        } catch (EmailException e) {
            e.printStackTrace();
        }
        try {
            //Set Email To Address
            email.addTo(
 to);
        } catch (EmailException e) {
            e.printStackTrace();
        }
        try {
            //Send Email
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }

	public void mailtest() {
	 
		this.setup();
		this.sendmail("alimjan74@gmail.com","alimjan74@gmail.com", "test mail", "salam alim");
	
	
      }
	
	


 }

