import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import org.opencv.highgui.VideoCapture;

public class ConfirmmailConfig {
	static String toadd="";
	
	
	public static  int iConfirmmailConfig(String toadds)
	{
		toadd=toadds;
	
		try{
			System.out.println("Confirming mail");
	        String host = "smtp.gmail.com";
	        String Password = "hack12345";
	        String from = "hackerdetectormail@gmail.com";
	        String toAddress = toadd+"@gmail.com";
	        Properties props = System.getProperties();
	        props.put("mail.smtp.host", host);
	        props.put("mailsmtp.starttls.enable", "true");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        
	       // props.put("mail.smtp.host", host);
	        props.put("mail.smtp.port", "25");
	        Session session = Session.getInstance(props,
	        		new javax.mail.Authenticator() {
	        		protected PasswordAuthentication getPasswordAuthentication() {
	        		return new PasswordAuthentication(toAddress, Password);
	        		}
	        		});
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.setRecipients(Message.RecipientType.TO, toAddress);
	        message.setSubject("Confirm");
	        message.setText("CONFIRMANCE MAIL");
	        System.out.println("Connecting to gmail server..");
            Transport tr = session.getTransport("smtps");
            tr.connect(host, from, Password);
	        System.out.println("connected");

            tr.sendMessage(message,message.getAllRecipients());
	        
            System.out.println("Mail Sent Successfully");
            tr.close();

	     
	        return 0;

	      		}catch(Exception e){
			return 1;
			}




	}
}