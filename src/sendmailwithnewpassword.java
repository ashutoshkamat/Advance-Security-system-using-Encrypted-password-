import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;

import org.opencv.highgui.VideoCapture;

import java.net.Authenticator;

class ProxyAuthenticator extends Authenticator {

    private String user, password;

    public ProxyAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password.toCharArray());
    }
}
public class sendmailwithnewpassword {
	static int  flag=0;
	String newpass="";
	String toadd="";
	public sendmailwithnewpassword(String newpass,String toadd,String keyval)
	{
		VideoCapture camera = new VideoCapture(0);

		this.newpass=newpass;
		this.toadd=toadd;
		
		
		try{
			
	        String host = "smtp.gmail.com";
	        String Password = "hack12345";
	        String from = "hackerdetectormail@gmail.com";
	        String toAddress = toadd+"@gmail.com";
	        
	       
	       
	       
	        Properties props = System.getProperties();
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtps.auth", "true");
	        props.put("mailsmtp.starttls.enable", "true");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

	    
	        props.put("mail.smtp.port", "25");
	        Session session = Session.getDefaultInstance(props,null);

	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.setRecipients(Message.RecipientType.TO, toAddress);
	        message.setSubject("Hacker detected");
	        message.setText("New Password:-"+newpass);
	        
	        
	     
	        System.out.println("Connecting to server...");
	        
	        
	        
	            Transport tr = session.getTransport("smtps");
	            
	            
	            
	            tr.connect(host, from, Password);
		        System.out.println("Connected");

	            tr.sendMessage(message,message.getAllRecipients());
		        
	            System.out.println("Mail Sent Successfully");
	            tr.close();
	            
	           //restart 

	    		Runtime runtime = Runtime.getRuntime();
	        	Process proc = runtime.exec("shutdown -r -t 0");
	        	System.exit(0);
	               
	            
	    	}catch(Exception e){
	    		System.out.println(e.getMessage());
	    		++flag;
	    	}
		
	     
	}
	
	static int error()
	{
		return flag;
	}
	static void addAttachment(Multipart multipart, String filename) throws MessagingException
    {
        DataSource source = new FileDataSource(filename);
        BodyPart messageBodyPart = new MimeBodyPart();        
       
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
    }
	
}
