import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.*;
import java.util.Base64;


public class AESEncr
{
	
	
 private static final String ALGO = "AES";
    static byte[] keyValue;


    public static String encrypt(StringBuffer Datab,String keyvals) throws Exception {
        String Data=Datab.toString();
        keyValue=keyvals.getBytes();
     	Key key = generateKey();
         Cipher c = Cipher.getInstance(ALGO);
         c.init(Cipher.ENCRYPT_MODE, key);
         byte[] encVal = c.doFinal(Data.getBytes());
         Base64.Encoder encoder = Base64.getEncoder();
         String encryptedValue = encoder.encodeToString(encVal);
       
         return encryptedValue;
 	 
    }
			
		
         
    
    public static String encrypt(String Data,String keyvals) throws Exception {
       
    	keyValue=keyvals.getBytes();
    	
    	
	Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedValue = encoder.encodeToString(encVal);
        return encryptedValue;
	
       }
    
    public static String decrypt(String encryptedData,String keyvals) throws Exception {
    	keyValue=keyvals.getBytes();
    	Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        Base64.Decoder decoder = Base64.getDecoder();
		byte[] decordedValue = decoder.decode(encryptedData);
        c.init(Cipher.DECRYPT_MODE, key);

        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        
        return decryptedValue;
    }
   
    public static String decrypt(StringBuffer encryptedDatab,String keyvals) throws Exception {
    	String encryptedData=encryptedDatab.toString();
    	keyValue=keyvals.getBytes();
    	Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        Base64.Decoder decoder = Base64.getDecoder();
		byte[] decordedValue = decoder.decode(encryptedData);
        c.init(Cipher.DECRYPT_MODE, key);

        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        
        
        return decryptedValue;
    }
 

    
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue,ALGO );
        return key;
}

}
