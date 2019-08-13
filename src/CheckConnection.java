
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.swing.JOptionPane;
 
public class CheckConnection {
	
	
	public  static  int ChkInternet() {
		try {
			URL url=new URL("https://www.google.co.in");
			URLConnection connect=url.openConnection();
			connect.connect();
			System.out.println("Connected to internet");
			return 1;
			
		} catch (Exception e) {
			System.out.println(" NOT Connected to internet");

			return 0;
			
			//return "Error "+e;
		}

	}
}

