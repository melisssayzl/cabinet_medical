package ui.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public final class StringUtils {
	private StringUtils() {
	}
	
	public static String sha1(String input) {
		String hashtext = null;
		
		try {
			// getInstance() method is called with algorithm SHA-1
	        MessageDigest md = MessageDigest.getInstance("SHA-1");

	        // digest() method is called
	        // to calculate message digest of the input string
	        // returned as array of byte
	        byte[] messageDigest = md.digest(input.getBytes());

	        // Convert byte array into signum representation
	        BigInteger no = new BigInteger(1, messageDigest);

	        // Convert message digest into hex value
	        hashtext = no.toString(16);

	        // Add preceding 0s to make it 32 bit
	        while (hashtext.length() < 40) {
	            hashtext = "0" + hashtext;
	        }

	        // return the HashText
		} catch (Exception e) {
			// TODO: handle exception
		}
		
        return hashtext;
	}
}
