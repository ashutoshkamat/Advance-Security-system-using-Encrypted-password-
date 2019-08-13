import javafx.scene.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.crypto.EncryptedPrivateKeyInfo;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;



public class Main implements ActionListener {

	
	VideoCapture camera;
	Mat frame = new Mat();
	Timer t=new Timer();
	String mailname;
	StringBuffer name;
	String nameorg,passorg;
	String keyval;
	String usrname;
	String pass,mailpass;
	String newpass="";
	String otp;
	int forgotstatus, registerstatus=0, confirmmail=0;
	String otpstat;
	Time time;
	int fakeattempts=0,realattempts=0;
	DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	Calendar calobj;
	
	JFrame mainform, fakeFrame, realFrame, registerFrame, loadingframe, forgotframe, otpframe,forgotregisterform;
	JButton cancelforgotlogin,forgotloginbutton,forgotfakebutton,forgotrealbutton, mainloginbutton,mainregisterbutton,fakeLoginButton,realLoginButton,register,submitotp,cancelotp,resendotp,submitforgotregbutton;
	JLabel forgotnamelabel,forgotpasslabel,fakenameLabel,fakePasswordLabel,realnameLabel,realPasswordLabel,namelabel,passlabel,confirmpasslabel,keystrokelabel,mailpasslabel,usernamelabel,gmaillabel,keylabel,keynote,loading,otplabel,forgotregisternewpass,forgotregisterconfirmpass, forgotgmaillabel, fakebackground, otpbackground,forgotscreenbackground,resetpasswordback, loadingbackground, otpimg, registerbackground, mailimg, fakeloginimg, realloginimg, resetpassimg;
	JTextField fakeNameField,realNameField,namefield,usernamefield,keyfield,forgotnamefield,otpfield;
	JPasswordField forgotpassfield,fakePasswordField,realPasswordField,passfield,confirmpassfield,mailpassfield,forgotregisternewpassfield,forgotregisterconfirmpassfield;
	JCheckBox cb;
	private JLabel realbackground;
	
	
	public Main() 
	{	
		//main frame
		
		mainform=new JFrame("HackerDetector");
		mainform.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainform.setBounds(100,100,700,600);	
		mainform.setLayout(null);
        
		mainloginbutton=new JButton("Login");
		mainloginbutton.setBounds(270,100,200,100);
		mainloginbutton.addActionListener(this);
		
		mainregisterbutton=new JButton("Register");
		mainregisterbutton.setBounds(270,300,200,100);
		mainregisterbutton.addActionListener(this);
		
		mainform.add(mainloginbutton);
		mainform.add(mainregisterbutton);
	//fake frame
		fakeFrame=new JFrame("Log In");
		fakeFrame.setBounds(0,0,1366,768);
		fakeFrame.setLayout(null);
		fakeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		fakeFrame.setUndecorated(true);
		fakeFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		fakeloginimg=new JLabel(new ImageIcon("D:/Project image/login.png"));
		fakeloginimg.setBounds(600, 100, 100, 100);
		
		fakebackground=new JLabel(new ImageIcon("D:/Project image/win10.jpg"));
		fakebackground.setBounds(0,0,1366,768);
		
		fakenameLabel=new JLabel("Enter UserName");
		fakenameLabel.setBounds(500,250,100,30);
		
		fakePasswordLabel=new JLabel("Enter Password");
		fakePasswordLabel.setBounds(500,320,100,30);
		
		fakeNameField=new JTextField();
		fakeNameField.setBounds(700,250,200,30);
		
		fakePasswordField=new JPasswordField();
		fakePasswordField.setBounds(700,320,200,30);
		fakePasswordField.setText(" ");
		
		fakeLoginButton=new JButton("Log In");
		fakeLoginButton.setBounds(570,400,200,50);
		fakeLoginButton.addActionListener(this);
		
		forgotfakebutton=new JButton("Forgot Password");
		forgotfakebutton.setBounds(570,500,200,50);
		forgotfakebutton.addActionListener(this);
		
		fakeFrame.add(fakeloginimg);
		fakeFrame.add(fakenameLabel);
		fakeFrame.add(fakePasswordLabel);
		fakeFrame.add(fakeNameField);
		fakeFrame.add(fakePasswordField);
		fakeFrame.add(fakeLoginButton);
		fakeFrame.add(forgotfakebutton);
		fakeFrame.add(fakebackground);

		//real frame
		
		realFrame=new JFrame("Log In");
		realFrame.setBounds(0,0,1366,768);
		realFrame.setLayout(null);
		realFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		realFrame.setUndecorated(true);
		realFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		

		realloginimg=new JLabel(new ImageIcon("D:/Project image/login.png"));
		realloginimg.setBounds(600, 100, 100, 100);
		
		realbackground=new JLabel(new ImageIcon("D:/Project image/win10.jpg"));
		realbackground.setBounds(0,0,1366,768);
		realnameLabel=new JLabel("Enter UserName");
		realnameLabel.setBounds(500,250,100,30);
		
		realPasswordLabel=new JLabel("Enter Password");
		realPasswordLabel.setBounds(500,320,100,30);
		
		realNameField=new JTextField();
		realNameField.setBounds(700,250,200,30);
		
		realPasswordField=new JPasswordField();
		realPasswordField.setBounds(700,320,200,30);
		
		realLoginButton=new JButton("Log In");
		realLoginButton.setBounds(570,400,200,50);
		realLoginButton.addActionListener(this);
		
		forgotrealbutton=new JButton("Forgot Password");
		forgotrealbutton.setBounds(570,500,200,50);
		forgotrealbutton.addActionListener(this);
		
		realFrame.add(realloginimg);
		realFrame.add(realnameLabel);
		realFrame.add(realPasswordLabel);
		realFrame.add(realNameField);
		realFrame.add(realPasswordField);
		realFrame.add(realLoginButton);
		realFrame.add(forgotrealbutton);
		realFrame.add(realbackground);
		//register frame
		
		registerFrame=new JFrame("Advance Security System | Registration");
		registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		registerFrame.setBounds(100,0,800,900);	
		registerFrame.setLayout(null);
		ImageIcon img = new ImageIcon("D:/Project image/registertab.png");
		registerFrame.setIconImage(img.getImage());
		
		JLabel welcometab=new JLabel("Welcome to Advance Security System !!!");
		welcometab.setFont(new Font("Times New Roman", Font.BOLD, 20));
		welcometab.setForeground(Color.RED);
		welcometab.setBounds(200,10,700,40);
		
		namelabel=new JLabel("Enter Name");
		namelabel.setBounds(100,50,100,40);
		namelabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		namelabel.setForeground(Color.BLACK);
		
		namefield=new JTextField();
		namefield.setBounds(300,50,200,40);
		passlabel=new JLabel("Enter Password");
		passlabel.setBounds(100,150,300,40);
		passlabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		passlabel.setForeground(Color.BLACK);
		
		passfield=new JPasswordField();
		passfield.setBounds(300,150,200,40);
		
		confirmpasslabel=new JLabel("Confirm Password");
		confirmpasslabel.setBounds(100,250,300,40);
		confirmpasslabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		confirmpasslabel.setForeground(Color.BLACK);
		
		confirmpassfield=new JPasswordField();
		confirmpassfield.setBounds(300,250,200,40);
		
		usernamelabel = new JLabel("mail address");
		usernamelabel.setBounds(100, 350, 100, 40);
		usernamelabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		usernamelabel.setForeground(Color.BLACK);
		
		
		usernamefield = new JTextField();
		usernamefield.setBounds(300, 350, 200, 40);
		
		gmaillabel=new JLabel("@gmail.com");
		gmaillabel.setBounds(500,350,100,40);
		gmaillabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		gmaillabel.setForeground(Color.BLACK);
		
		
		mailpasslabel=new JLabel("Mail Password");
		mailpasslabel.setBounds(100,450,100,40);
		mailpasslabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		mailpasslabel.setForeground(Color.BLACK);
		
		
		mailpassfield=new JPasswordField();
		mailpassfield.setBounds(300,450,200,40);
		
		keylabel=new JLabel("encryption key");
		keylabel.setBounds(100,550,200,40);
		keylabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		keylabel.setForeground(Color.BLACK);
		
		
		keyfield=new JTextField();
		keyfield.setBounds(300, 550, 200,40);
		
		keynote=new JLabel("16 characters");
		keynote.setBounds(500, 550, 190,40);
		keynote.setForeground(Color.MAGENTA);
		
		cb=new JCheckBox("2 step verification", false);
		cb.setBounds(600, 550, 200, 40);
		cb.setFont(new Font("Times New Roman", Font.BOLD, 15));
		cb.setForeground(Color.BLACK);
		cb.setBackground(Color.CYAN);
		register=new JButton("Send OTP");
		register.setBounds(250,650,100,40);
		register.addActionListener(this);
			
		
		registerbackground =new JLabel(new ImageIcon("D:/Project image/registerback.png"));
		registerbackground.setBounds(0,0,1366,768);
		
		registerFrame.add(welcometab);
		registerFrame.add(namelabel);
		registerFrame.add(passlabel);
		registerFrame.add(confirmpasslabel);
		registerFrame.add(usernamelabel);
		registerFrame.add(mailpasslabel);
		registerFrame.add(gmaillabel);
		registerFrame.add(keylabel);
		registerFrame.add(namefield);
		registerFrame.add(passfield);
		registerFrame.add(confirmpassfield);
		registerFrame.add(usernamefield);
		registerFrame.add(mailpassfield);
		registerFrame.add(keyfield);
		registerFrame.add(register);
		registerFrame.add(keynote);
		registerFrame.add(cb);
		registerFrame.add(registerbackground);
	//loading status frame
		loadingframe=new JFrame("Loading");
		loadingframe.setBounds(0,0,1366,768);
		loadingframe.setLayout(null);
		loadingframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		loadingframe.setUndecorated(true);
		loadingframe.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		loadingbackground=new JLabel(new ImageIcon("D:/Project image/win10.jpg"));
		loadingbackground.setBounds(0,0,1366,768);
		loading=new JLabel("Loading....");
		loading.setBounds(0,0,200,30);
		
		
		loadingframe.add(loading);
		loadingframe.add(loadingbackground);
		
	//forgot frame
		forgotframe=new JFrame("Forgot pass");
		forgotframe.setBounds(0,0,1366,768);
		forgotframe.setLayout(null);
		forgotframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		forgotframe.setUndecorated(true);
		forgotframe.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			
		mailimg=new JLabel(new ImageIcon("D:/Project image/mail.png"));
		mailimg.setBounds(600, 100, 100, 100);
		
		
		forgotscreenbackground=new JLabel(new ImageIcon("D:/Project image/win10.jpg"));
		forgotscreenbackground.setBounds(0,0,1366,768);
		forgotnamelabel=new JLabel("Account Name");
		forgotnamelabel.setBounds(500,250,100,30);
		
		
		
		forgotpasslabel=new JLabel("gmail Password");
		forgotpasslabel.setBounds(500,320,100,30);
			
		forgotnamefield=new JTextField();
		forgotnamefield.setBounds(700,250,200,30);
		
		forgotgmaillabel=new JLabel("@gmail.com");
		forgotgmaillabel.setBounds(900,250,100,30);
		
		forgotpassfield=new JPasswordField();
		forgotpassfield.setBounds(700,320,200,30);
			
		forgotloginbutton=new JButton("Submit");
		forgotloginbutton.setBounds(570,400,200,50);
		forgotloginbutton.addActionListener(this);
			
		cancelforgotlogin=new JButton("Cancel");
		
		
		cancelforgotlogin.setBounds(570,500,200,50);
		cancelforgotlogin.addActionListener(this);
			
		forgotframe.add(mailimg);
		forgotframe.add(forgotnamelabel);
		forgotframe.add(forgotpasslabel);
		forgotframe.add(forgotnamefield);
		forgotframe.add(forgotpassfield);
		forgotframe.add(forgotloginbutton);
		forgotframe.add(cancelforgotlogin);
		forgotframe.add(forgotgmaillabel);	
		forgotframe.add(forgotscreenbackground);
		//otp frame
		otpframe=new JFrame("OTP");
		otpframe.setBounds(0,0,1366,768);
		otpframe.setLayout(null);
		otpframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		otpframe.setUndecorated(true);
		otpframe.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		
		otpimg=new JLabel(new ImageIcon("D:/Project image/otpimg.png"));
		otpimg.setBounds(600, 100, 100, 100);
		
		otpbackground=new JLabel(new ImageIcon("D:/Project image/win10.jpg"));
		otpbackground.setBounds(0,0,1366,768);
		
		otplabel=new JLabel("Enter OTP");
		otplabel.setBounds(500,250,100,30);

		otpfield=new JTextField();
		otpfield.setBounds(700,250,200,30);
			
		submitotp=new JButton("Submit");
		submitotp.setBounds(570,400,200,50);
		submitotp.addActionListener(this);
			
		resendotp=new JButton("Resend OTP");
		resendotp.setBounds(570,500,200,50);
		resendotp.addActionListener(this);
			
		
		otpframe.add(otpimg);
		otpframe.add(otplabel);
		otpframe.add(otpfield);
		otpframe.add(submitotp);
		otpframe.add(resendotp);
		
		otpframe.add(otpbackground);

		//resetpassord field
		forgotregisterform=new JFrame("Reset Password");
		forgotregisterform.setBounds(0,0,1366,768);
		forgotregisterform.setLayout(null);
		forgotregisterform.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		forgotregisterform.setUndecorated(true);
		forgotregisterform.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		resetpasswordback=new JLabel(new ImageIcon("D:/Project image/win10.jpg"));
		resetpasswordback.setBounds(0,0,1366,768);
		
		resetpassimg=new JLabel(new ImageIcon("D:/Project image/resetpass.png"));
		resetpassimg.setBounds(600, 100, 100, 100);
		forgotregisternewpass=new JLabel("New Password");
		forgotregisternewpass.setBounds(500,250,100,30);

		forgotregisternewpassfield=new JPasswordField();
		forgotregisternewpassfield.setBounds(700,250,200,30);
			
		forgotregisterconfirmpass=new JLabel("Confirm password");
		forgotregisterconfirmpass.setBounds(500,320,100,30);			

		forgotregisterconfirmpassfield=new JPasswordField();
		forgotregisterconfirmpassfield.setBounds(700,320,200,30);
			

		submitforgotregbutton=new JButton("Reset");
		submitforgotregbutton.setBounds(570,400,200,50);
		submitforgotregbutton.addActionListener(this);
			
		forgotregisterform.add(resetpassimg);
		forgotregisterform.add(forgotregisternewpass);
		forgotregisterform.add(forgotregisternewpassfield);
		forgotregisterform.add(forgotregisterconfirmpass);
		forgotregisterform.add(forgotregisterconfirmpassfield);
		forgotregisterform.add(submitforgotregbutton);
		forgotregisterform.add(resetpasswordback);	
		//key binding
	 
		//mainform.setVisible(true);
		
		InputMap im = fakeNameField.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap am = fakeNameField.getActionMap();
	    im.put(KeyStroke.getKeyStroke("Control D"), "cancelfake");
	    im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK ), "cancelfake");
	 	am.put("cancelfake", new CancelAction());
        
	 	
	 	registerFrame.setVisible(true);
	 	
	 	
	 	int count=count();
		if(count==0)
		{
			try {
					
					registerFrame.setVisible(true);
	 			} 
			catch (Exception e) 
				{
	 		
	 				e.printStackTrace();
	 			}
		}
		
		else
		{
			camera=new VideoCapture(0);
			fakeFrame.setVisible(true);
		}
		}
	
	
	class CancelAction extends AbstractAction 
	{
        public void actionPerformed(ActionEvent ev) {
        	fakeFrame.setVisible(false);
        	otpframe.setVisible(false);
        	forgotframe.setVisible(false);
        	forgotregisterform.setVisible(false);
			
        	realFrame.setVisible(true);
        }
    }
	public static void main(String[] args) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
		new Main();
		
		
	}
	
	public void setTime()
	{
		Timer t=new Timer();
		t.schedule(new TimerTask(){
			public void run(){
				otp="";
				System.out.println("time elapsed");
			}
		},2*60*1000);
	}
	
	String generatenewpassword()
	{
		int randompasswordlength=4 + (int)(Math.random() * ((8 - 4) + 1));
		GeneratePassword g=new GeneratePassword();
		g.RandomString(randompasswordlength);
		return g.nextString();
		
	}
	
	int auth(String username,String password)
	{
		

		int flag=0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/hackerdetector","root","root");
			Statement stmt = (Statement) con.createStatement();
		
			String query = "SELECT username,pass,mailname,passname FROM userdb;";
			ResultSet rs =stmt.executeQuery(query);
	    
	    
		while(rs.next())
		{
			
			nameorg=rs.getString("username");
	    	name=new StringBuffer(rs.getString("username"));  //contents of username in database is stored in name(Stringbuffer)
	    	otpstat=name.substring(name.length()-1);//otpstatus
	    	name.setLength(name.length()-1);//length reducedby 1
	    	keyval=name.substring(name.length()-16);//last 16 bits of username i.e. AES key is stored in keyval 
	    	name.setLength(name.length()-16);//length of name is reduced by 16
	    	passorg=rs.getString("pass");
	    	pass=rs.getString("pass");
	    	mailname=AESEncr.decrypt(rs.getString("mailname"), keyval);
	    	String namestring=name.toString();
	    	username=AESEncr.encrypt(username, keyval);
	    	username=Hash.generateHash(username);
	    	password=AESEncr.encrypt(password, keyval);
	    	password=Hash.generateHash(password);
	    	if(namestring.equals(username)&&pass.equals(password))
	        {
	        	flag=1;
	        	System.out.println(flag);
	        	break;
	        }
	       
	       
	    }
		
		
		}catch(Exception e){}
		return flag;
	}
	
	int authmail(String mailnamecomp,String mailpasscomp)
	{
		int flg=0;
		
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/hackerdetector","root","root");
		Statement stmt = (Statement) con.createStatement();
		
		String query = "SELECT username,pass,mailname,passname FROM userdb;";
	    ResultSet rs =stmt.executeQuery(query);
	    
	    
	    while(rs.next())
	    {
	    	nameorg=rs.getString("username");
	    	name=new StringBuffer(rs.getString("username"));  //contents of username in database is stored in name(Stringbuffer)
	    	name.setLength(name.length()-1);//length of otp status is substracted
	    	keyval=name.substring(name.length()-16);//last 16 bits of username i.e. AES key is stored in keyval 
	    	passorg=rs.getString("pass");
	    	mailname=AESEncr.decrypt(rs.getString("mailname"),keyval);
	    	mailpass=rs.getString("passname");
	    	

	    	mailpasscomp=AESEncr.encrypt(mailpasscomp, keyval);
	    	mailpasscomp=Hash.generateHash(mailpasscomp);

	    	System.out.println(mailpass);
	    	System.out.println(mailpasscomp);
	    if(mailnamecomp.equals(mailname)&&mailpasscomp.equals(mailpass))
	        {
	        	
	        	flg=1;
	        }
	       
	    }
		}catch(Exception e){}			
		return flg;
		}

	
	void changepassword(String name,String pass)
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/hackerdetector","root","root");
		Statement stmt = (Statement) con.createStatement();
		newpass=generatenewpassword();
		newpass=AESEncr.encrypt(pass, keyval);
		newpass=Hash.generateHash(newpass);
		String query = "update userdb set pass='"+newpass+"' where username='"+nameorg+"' AND pass='"+passorg+"'";
		stmt.executeUpdate(query);
		 
		}catch(Exception e){}
	}
	@SuppressWarnings("deprecation")
	@Override
	
	
	public void actionPerformed(ActionEvent ae) {
		
		int st= CheckConnection.ChkInternet();
		
			if(ae.getSource()==realLoginButton)
			{
				
				if(st==0)
				{
					JOptionPane.showMessageDialog(realFrame, "Failed to connect to DB server.Please check your internet connection.");
					
				}
				else
				{
					realattempts++;
					String nametocompare=realNameField.getText();
					String passtocompare=realPasswordField.getText();
			
					int check=auth(nametocompare, passtocompare);
					if(check==0&&realattempts==1)
					{
						JOptionPane.showMessageDialog(realFrame, "Invalid username or password");
						realNameField.setText("");
						realPasswordField.setText("");
					}
					if(check==0&&realattempts==2)
					{
						JOptionPane.showMessageDialog(realFrame, "Invalid username or password");	
						realNameField.setText("");
						realPasswordField.setText("");
					}
					if(check==0&&realattempts==3)
					{
				
						changepassword(nametocompare,passtocompare);
						
						startcaptureandsendmailwithnewpassword();

						System.out.println("Hacker entered real login screen");
					}
					if(check==1)
					{
						try {
							fakeFrame.setVisible(false);
							
							otpframe.setVisible(false);
							forgotregisterform.setVisible(false);
							forgotframe.setVisible(false);
							camera.release();
							if(otpstat.equals("1"))
							{
								otp=generatenewpassword();
								new ForgotPasswordMail(mailname,otp);
								if(ForgotPasswordMail.error()==0)
								{
									JOptionPane.showMessageDialog(realFrame, "OTP is sent to your mail");
									t.schedule(new TimerTask(){
										public void run(){
											otp=generatenewpassword();
											System.out.println("time elapsed");
										}
									},12*60*1000);
									otpframe.setVisible(true);
								}
								else
									JOptionPane.showMessageDialog(realFrame, "Failed to send otp");	
								realFrame.setVisible(false);
								otpframe.setVisible(true);
							}
							else
							{
							realFrame.setVisible(false);
							logAndRegisterDate();
							
							Process process = new ProcessBuilder(
		 					"C:\\Windows\\System32\\userinit.exe").start();
							System.exit(0);
							
							}
							} catch (Exception e) {
		 		
								e.printStackTrace();
							}
							
							
					}
				}
			}
		
		
			if(ae.getSource()==mainloginbutton)
			{
						
				mainform.setVisible(false);
				fakeFrame.setVisible(true);
			}
		
			else if(ae.getSource()==mainregisterbutton)
			{
				mainform.setVisible(false);
				registerFrame.setVisible(true);
			}

			else if(ae.getSource()==fakeLoginButton)
			{	
				if(st==0)
				{
					JOptionPane.showMessageDialog(fakeFrame, "Failed to connect to DB server.Plz check your internet connection.");
					
				}
				else
				{
					fakeattempts++;	
					String nametocompare=fakeNameField.getText();
					String passtocompare=fakePasswordField.getText();
					new File("C:\\Users\\Public\\AppData\\HackerDetector").mkdirs();
					int check=auth(nametocompare, passtocompare);
			
			
					System.out.println("auth here");
					if(check==1&&fakeattempts==1)
					{
						JOptionPane.showMessageDialog(fakeFrame, "Invalid name or password");
						fakeNameField.setText("");
						fakePasswordField.setText("");
					}
					else if(check==1&&fakeattempts==2)
					{
						JOptionPane.showMessageDialog(fakeFrame, "Invalid name or password");
						fakeNameField.setText("");
						fakePasswordField.setText("");
					}
			
					else if(check==1&&fakeattempts==3)
					{	
						System.out.println("hacker detected");
						changepassword(nametocompare,passtocompare);
						
						System.out.println("HIII");
						startcaptureandsendmailwithnewpassword();
						fakeFrame.setVisible(false);
				
					}	
					else if(fakeattempts==3)
					{
						System.out.println("hacker detected");
						startcaptureandsendmail();
						fakeFrame.setVisible(false);
					}
				}
			}
			
			else if(ae.getSource()==register)
			{		
				if(st==0)
				{
					JOptionPane.showMessageDialog(registerFrame, "Failed to connect to DB server.Plz check your internet connection.");
					
				}
				else
				{
				if(namefield.getText().equals("")||passfield.getText().equals(""))
	    			{
						
	    				JOptionPane.showMessageDialog(registerFrame, "Plz enter username or password");
	    				namefield.setText("");
	    				passfield.setText("");
	    				confirmpassfield.setText("");
	    				usernamefield.setText("");
	    				mailpassfield.setText("");
	    			}
				else if(passfield.getText().length()<8)
				{
					JOptionPane.showMessageDialog(registerFrame, "Length of password should be atleast 8 characters");
					namefield.setText("");
					passfield.setText("");
					confirmpassfield.setText("");
					usernamefield.setText("");
					mailpassfield.setText("");
				
				}
				else if(passfield.getText().equals(namefield.getText()))
				{
					JOptionPane.showMessageDialog(registerFrame, "username and password cannot not be same");
					namefield.setText("");
					passfield.setText("");
					confirmpassfield.setText("");
					usernamefield.setText("");
					mailpassfield.setText("");
				
				}
				else if(passfield.getText().equals(confirmpassfield.getText())==false)
				{
					JOptionPane.showMessageDialog(registerFrame, "Passwords did'nt match");
					namefield.setText("");
					passfield.setText("");
					confirmpassfield.setText("");
					usernamefield.setText("");
					mailpassfield.setText("");
				
				}
				
				else if(usernamefield.getText().equals("")||mailpassfield.getText().equals(""))
	    			{
	    				JOptionPane.showMessageDialog(registerFrame, "GMAIL credentials are invalid ");
	    				namefield.setText("");
	    				passfield.setText("");
	    				confirmpassfield.setText("");
	    				usernamefield.setText("");
	    				mailpassfield.setText("");
	    			}

				else if(keyfield.getText().length()<16||keyfield.getText().length()>16)
	    			{
	    				JOptionPane.showMessageDialog(registerFrame, "Encryption key must be of 16 characters ");
	    				namefield.setText("");
	    				passfield.setText("");
	    				confirmpassfield.setText("");
	    				usernamefield.setText("");
	    				mailpassfield.setText("");
	    			}
				
				
				
				
				
				else 
				{
					String mailn=usernamefield.getText();
					String passn=mailpassfield.getText();
					
					otpstat="0";
					int chk=ConfirmmailConfig.iConfirmmailConfig(mailn);
  	  		    	try {
					
  	  		    		if(chk==0)
		  	  			{
  	  		    			
  	  		    		usrname=namefield.getText();
						pass=passfield.getText();
						mailname=usernamefield.getText();
						mailpass=mailpassfield.getText();
						keyval=keyfield.getText();
						
  	  		    		if(cb.isSelected()==true)
	  		    		    	otpstat="1";
  	  		    			registerstatus=1;
  	  		    			otp=generatenewpassword();
  	  		    			new ForgotPasswordMail(mailn,otp);
  	  		    			if(ForgotPasswordMail.error()==0)
  	  		    			{
  	  		    				JOptionPane.showMessageDialog(registerFrame, "OTP is sent to your mail");
  	  		    				t.schedule(new TimerTask(){
  								public void run(){
  									otp=generatenewpassword();
  									System.out.println("time elapsed");
  								}
  	  		    				},12*60*1000);
  							otpframe.setVisible(true);
  							
  	  		    			}
  	  		    			
  	  		    		
  	  		    			
		  	  			}
  	  		    		}catch (Exception e) {
					// TODO Auto-generated catch block
							chk=1;
							e.printStackTrace();
						}		
  	  		    	 if(chk==1)
  	  		    		{
  	  		    		JOptionPane.showMessageDialog(registerFrame, "Gmail username or password is invalid");
  	  		    		namefield.setText("");
  	  		    		passfield.setText("");
  	  		    		confirmpassfield.setText("");
  	  		    		usernamefield.setText("");
  	  		    		mailpassfield.setText("");
			
  	  		    		}
  	  		    			
				
  	  		    	namefield.setText("");
					passfield.setText("");
					confirmpassfield.setText("");
					usernamefield.setText("");
					mailpassfield.setText("");
					
				}
			}	
		}
			
			else if(ae.getSource()==cancelforgotlogin)
			{
				
				fakeFrame.setVisible(true);
			}
		
			if(ae.getSource()==forgotrealbutton)
			{
				
				int status= CheckConnection.ChkInternet();
				if(status==0)
				{
				JOptionPane.showMessageDialog(realFrame, "Failed to connect to DB server.Plz check your internet connection.");
				realFrame.setVisible(true);
				}
				else
				{
				forgotframe.setVisible(true);
				}
			}
		
		
		
			if(ae.getSource()==forgotloginbutton)
			{	
				if(st==0)
				{
					JOptionPane.showMessageDialog(forgotframe, "Failed to connect to DB server.Plz check your internet connection.");
					
				}
				else
				{
				forgotstatus=1;
				String nametocomp=forgotnamefield.getText();
				String passtocomp=forgotpassfield.getText();
				if(authmail(nametocomp,passtocomp)==1)
				{
			    	 otp=generatenewpassword();
					new ForgotPasswordMail(mailname,otp);
					if(ForgotPasswordMail.error()==0)
					{
						JOptionPane.showMessageDialog(forgotframe, "OTP is sent to your mail");
						t.schedule(new TimerTask(){
							public void run(){
								otp=generatenewpassword();
								System.out.println("time elapsed");
							}
						},12*60*1000);
						otpframe.setVisible(true);
						
					}
					else
						JOptionPane.showMessageDialog(forgotframe, "Failed to send otp");	
		        	
		        	
				}
				else
					JOptionPane.showMessageDialog(forgotframe, "Invalid username or password");

				forgotnamefield.setText("");
				forgotpassfield.setText("");
				}
			}

			if(ae.getSource()==submitotp)
			{
			
				if(otp.equals(otpfield.getText()))
				{
					if(forgotstatus==1)
					{
						JOptionPane.showMessageDialog(otpframe, "OTP verified");
						forgotregisterform.setVisible(true);
				
					}
					if(forgotstatus==0&&registerstatus==0)
					{
						JOptionPane.showMessageDialog(otpframe, "successfully logged in");
						
							   try {
								   
								   logAndRegisterDate();
							Process process = new ProcessBuilder("C:\\Windows\\System32\\userinit.exe").start();
							System.exit(0);
						      
						      } catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						otpframe.setVisible(false);
						realFrame.setVisible(false);
					}
					if(registerstatus==1)
					{
						
						
						try { 
							name=new StringBuffer(Hash.generateHash((AESEncr.encrypt(usrname,keyval))));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} //orginal username  is encrypted
  	  		    		
	  		    			name.insert(name.length(),keyval);//keyval is appended to the username
	  		    			name.insert(name.length(),otpstat);	
	  		    			try {
								registerinDB(name,
								Hash.generateHash(AESEncr.encrypt(pass,keyval)),
								AESEncr.encrypt(mailname, keyval),
								Hash.generateHash(AESEncr.encrypt(mailpass, keyval))
										);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	  		    			registerFrame.setVisible(false);
	  		    			JOptionPane.showMessageDialog(otpframe, "Registered successfully");
	  		    			otpframe.setVisible(false);
	  		    			try {
	  		    				logAndRegisterDate();
								Process process = new ProcessBuilder("C:\\Windows\\System32\\userinit.exe").start();
								System.exit(0);
	  		    			} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	  		    			
	  		    			
									
					
					}
				}
				else
				{
				JOptionPane.showMessageDialog(otpframe, "Wrong OTP");
				otpfield.setText("");
				}
				otpfield.setText("");
			}
			
			
			if(ae.getSource()==resendotp)
			{
				if(st==0)
				{
					JOptionPane.showMessageDialog(otpframe, "Failed to connect to DB server.Plz check your internet connection.");
					
				}
				else
				{
				otp=generatenewpassword();
				new ForgotPasswordMail(mailname,otp);
				if(ForgotPasswordMail.error()==0)
				{	JOptionPane.showMessageDialog(otpframe, "OTP is sent to your mail");
				otpframe.setVisible(true);
				}	else
						JOptionPane.showMessageDialog(otpframe, "Failed to send otp");	
		    
				}
        	}
		
			if(ae.getSource()==submitforgotregbutton)
			{
				if(st==0)
				{
					JOptionPane.showMessageDialog(forgotregisterform, "Failed to connect to DB server.Plz check your internet connection.");
					
				}
			
				else
				{
				String pass=forgotregisternewpassfield.getText();
				String confirmpass=forgotregisterconfirmpassfield.getText();
				System.out.println(name);
				if(pass.equals(confirmpass)&&pass.length()>=8&&pass.equals(name)==false)
				{
					updatepassword(pass);
					JOptionPane.showMessageDialog(forgotregisterform, "Password reset successful");
					realFrame.setVisible(false);
					fakeFrame.setVisible(true);
					forgotstatus=0;
				}
				else
				{
					if(pass.length()<8)
					JOptionPane.showMessageDialog(forgotregisterform, "password length should be atleast 8 characters");
					else if(pass.equals(name))
						JOptionPane.showMessageDialog(forgotregisterform, "new password and username cannot be equal");
					else
						JOptionPane.showMessageDialog(forgotregisterform, "new password and confirm password field didnt match");
					forgotregisternewpassfield.setText("");
					forgotregisterconfirmpassfield.setText("");
					forgotregisterform.setVisible(true);
				forgotregisternewpassfield.setText("");
				forgotregisterconfirmpassfield.setText("");
			
				}
				}
			}
	}
	
			
	void logAndRegisterDate()
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		
		calobj = Calendar.getInstance();
	       System.out.println(df.format(calobj.getTime()));
	      String activedate=df.format(calobj.getTime());
	    
		Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/hackerdetector","root","root");
		Statement stmt = (Statement) con.createStatement();
	
		String query = "update userdb set activedate ='"+activedate+"' where username='"+nameorg+"'";
		int rs =stmt.executeUpdate(query);
		}
		catch(Exception e){}
	}
	
	void updatepassword(String newpass)
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/hackerdetector","root","root");
		Statement stmt = (Statement) con.createStatement();
		newpass=AESEncr.encrypt(newpass, keyval);
		newpass=Hash.generateHash(newpass);
		String query = "update userdb set pass='"+newpass+"' where username='"+nameorg+"' AND pass='"+passorg+"' ";
		stmt.executeUpdate(query);
		}catch(Exception e){}

	}
	
	int count()
	{
		int noofrecords=0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/hackerdetector","root","root");
			Statement stmt = (Statement) con.createStatement();
			
			String query = "SELECT * FROM userdb;";
		    ResultSet rs =stmt.executeQuery(query);
		    while(rs.next())
		    {
		    	noofrecords++;
		    }

		}
		catch(Exception e){}
		
	return noofrecords;
	}

	void registerinDB(StringBuffer name,String pass,String mailname,String passname)
	{
		try {
			System.out.println("I am in database");
	  			Class.forName("com.mysql.jdbc.Driver");
	  			Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/hackerdetector","root","root");
	  			
	  			int count=count();
  	  			count=count+1;
  	  			Statement stmt=con.createStatement();
  	  			String query="insert into userdb values('"+count+"','"+name+"','"+pass+"','"+mailname+"','"+passname+"')";
  	  			stmt.executeUpdate(query);
  	  		    
  	  		    namefield.setText("");
  	  			passfield.setText("");
  	  			confirmpassfield.setText("");
  	  			usernamefield.setText("");
  	  			mailpassfield.setText("");
  	  			keyfield.setText("");
  	  			
		}catch(Exception e){}
	}
	
	void startcaptureandsendmail()
	{
		
		 
		loadingframe.setVisible(true);
		new Thread(new Runnable() {
			int fl=0;
			int st= CheckConnection.ChkInternet();
			@Override
			public void run() {
				for(int i=1;i<6;i++)
				{
						try
					{
					
						if(!camera.open(0))
						{
							++fl;
							System.out.println("Camera is absent!!!");
							break;
						}
					
					capture(i);
					Thread.sleep(2000);
					System.out.println("Camera is present!!!");
					}
						catch(Exception e){}
				}
				
				
				if(st==0)
				{
					JOptionPane.showMessageDialog(loadingframe, "Failed to connect to DB server.Plz check your internet connection.");
					
				}
				else
				{
				if(fl==0)
					new sendmail(mailname);
				}
				Runtime runtime = Runtime.getRuntime();
			        try {
							Process proc = runtime.exec("shutdown -r -t 0");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	System.exit(0);
			
			}
		}).start();
	}

	
	void startcaptureandsendmailwithnewpassword() 
	{
		loadingframe.setVisible(true);
		new Thread(new Runnable() {
			
			public void run() {
				int f=0;
				int st= CheckConnection.ChkInternet();
				for(int i=1;i<6;i++)
					{
							try
							{
								if(!camera.open(0))
								{
									if(st==0)
									{
										JOptionPane.showMessageDialog(loadingframe, "Failed to connect to DB server.Plz check your internet connection.");
										
									}
									else
									new sendmailwithnewpassword(newpass,mailname,keyval);
								    
									System.out.println("Camera is absent!!!");
									++f;
									break;
								}
								
						capture(i);
						Thread.sleep(2000);
						}catch(Exception e){}
					}
				
				if(st==0)
				{
					JOptionPane.showMessageDialog(loadingframe, "Failed to connect to DB server.Plz check your internet connection.");
					
				}
				else{
				if(f==0)
				{
				
					new sendmail(mailname);
					new sendmailwithnewpassword(newpass,mailname,keyval);

				}
				}
			}	
			
		}).start();
	}
	
	
	
	void capture(int i)
	{
		try
		{
			
			while(true){
				
				
				if (camera.read(frame)){
					System.out.println("photo captured");		
					Highgui.imwrite("C:\\Users\\Public\\AppData\\HackerDetector\\hacker_img"+i+".jpg",frame);
					System.out.println("OK");
					
					break;
				}
			}
			camera.release();
			
		}
		  catch(Exception e)
		  {
			  System.out.println("Hello i am catch of cpture");
		  }
        
        
	}
}

