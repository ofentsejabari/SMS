package mysqldriver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author O'JAY
 */
public class MD5Hash {

    public static String HashPassword(String pwd){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());
            
            byte byteData[] = md.digest();

            StringBuilder hexString = new StringBuilder();
            for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            System.out.println("Digest(in hex format):: " + hexString.toString());
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException ex) {
          return null;
        }
    }
}