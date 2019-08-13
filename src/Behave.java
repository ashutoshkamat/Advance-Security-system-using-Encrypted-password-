import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Behave extends JFrame implements ActionListener{
	JFrame jf;
	JTable jt;
	JButton b;
	public Behave() throws Exception
	{	jf=new JFrame();
		jf.setVisible(true);
		
		setLayout(new BorderLayout());
		b=new JButton("Refresh");
		jf.add(b,BorderLayout.SOUTH);
		b.addActionListener(this);
			}

	Container con;
	public static void main(String[] args) throws Exception  {
		new Behave();
		
	}
	public void getRecord()throws Exception
	{
				
	
		}
	@Override
	public void actionPerformed(ActionEvent e)  {
		// TODO Auto-generated method stub
		try
		{
		if(e.getSource()==b)
		{

			Vector rows=new Vector();
			Vector colheads= new Vector();
			Vector ro= new Vector();
			
			Vector count=new Vector();
			Vector mailn=new Vector();
			Vector date=new Vector();
			StringBuffer username=null;
			String keyval=null;
			int c=0;
			String dates=null;
			String mailname=null;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/hackerdetector","root","root");
			Statement stmt = (Statement) con.createStatement();
		
			String query = "SELECT count, username, mailname, activedate FROM userdb;";
			ResultSet rs =stmt.executeQuery(query);
	    
			while(rs.next())
			{
				username=new StringBuffer(rs.getString("username"));  //contents of username in database is stored in name(Stringbuffer)
		    	username.setLength(username.length()-1);//length reducedby 1
		    	keyval=username.substring(username.length()-16);//last 16 bits of username i.e. AES key is stored in keyval 
		    	
		    	 c=rs.getInt("count");
				dates=rs.getString("activedate");
				mailname=rs.getString("mailname");
				mailname=AESEncr.decrypt(mailname, keyval);
				
				count.addElement(c);
				mailn.addElement(mailname);
				date.addElement(dates);
				
			}
			colheads.addElement("count");
			colheads.addElement("mail");
			colheads.addElement("date");
			
				for( int i=0; i<count.size(); i++)
				{
					rows=new Vector();
					
					Object cnt= count.elementAt(i);
					mailname=(String) mailn.elementAt(i);
					dates=(String) date.elementAt(i);
					rows.add(0, cnt);
					rows.add(1, mailname);
					rows.add(2, dates);

					System.out.println(rows.elementAt(0));
					ro.addElement(rows);
					
				}
				
			
				jt=new JTable(ro, colheads);
		
			
			JScrollPane jsp=new JScrollPane(jt);
			
			jf.add(jt);
			jf.setVisible(true);
			
		}
	}catch(Exception ae){}
	}

}