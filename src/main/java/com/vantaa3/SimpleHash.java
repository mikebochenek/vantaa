package com.vantaa3;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SimpleHash {

	public final static String salt = "3xies83d#@<>1";
	
	public static String generate(String email) {
		String string = email + salt;
		StringBuffer result = new StringBuffer();
		
		try { //TODO we shouldn't init it every time...
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		    messageDigest.reset();
		    messageDigest.update(string.getBytes(Charset.forName("UTF8")));
		    final byte[] resultByte = messageDigest.digest();
		    
		    for (int i = 0; i < resultByte.length; ++i) {
		    	result.append(Integer.toHexString((resultByte[i] & 0xFF) | 0x100).substring(1,3));
		    }
		    
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	    
        return result.toString();

	}
}
