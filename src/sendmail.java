import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class sendmail {

    public sendmail(String toadd)  {
    	try{
        String host = "smtp.gmail.com";
        String Password = "hack12345";
        String from = "hackerdetectormail@gmail.com";
        String toAddress = toadd+"@gmail.com";
        
        String filename1 = "C:/Users/Public/AppData/HackerDetector/hacker_img5.jpg";
        String filename2 = "C:/Users/Public/AppData/HackerDetector/hacker_img4.jpg";
        String filename3 = "C:/Users/Public/AppData/HackerDetector/hacker_img3.jpg";
        String filename4 = "C:/Users/Public/AppData/HackerDetector/hacker_img2.jpg";
        String filename5 = "C:/Users/Public/AppData/HackerDetector/hacker_img1.jpg";
        // Get system properties
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props,null);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, toAddress);
        message.setSubject("Attacker Photo");
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Here's the photo");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        messageBodyPart = new MimeBodyPart();
        
       /* DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);*/
        addAttachment(multipart, filename1);
        addAttachment(multipart, filename2);
        addAttachment(multipart, filename3);
        addAttachment(multipart, filename4);
        addAttachment(multipart, filename5);
        
        
        message.setContent(multipart);

        System.out.println("Connecting...");
            Transport tr = session.getTransport("smtps");
            tr.connect(host, from, Password);
            tr.sendMessage(message, message.getAllRecipients());
            System.out.println("Mail Sent Successfully");
            tr.close();
     
            
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
        
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