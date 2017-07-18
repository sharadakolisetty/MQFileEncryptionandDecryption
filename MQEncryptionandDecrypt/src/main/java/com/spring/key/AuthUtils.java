package com.spring.key;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/*
 * Helper class implementing various functions related to Authentication signature and Encryption
 */

public final class AuthUtils {
	
	private static final int AES_IV_LENGTH = 16;
	
	private static final int AES_IV_LENGTH1 = 16;
	
	private static final byte[] CRYPT_NONCE_1 = DatatypeConverter.parseHexBinary("89283293829");
	private static final byte[] CRYPT_NONCE_2 = DatatypeConverter.parseHexBinary("89283293829");
	
	private static final String HMAC_SHA256_ALGORITHM ="HmacSHA256";
	
	private AuthUtils(){
		
	}
	
	/*
	 * create an HMAC -256 MAC using the given encryption key
	 * @param hmackey HMAC encryption key;will be padded with zeros if less than 32 bytes
	 * 
	 */
	
	private static Mac hmacSha256(byte[] hmacKey)throws NoSuchAlgorithmException,InvalidKeyException{
		SecretKeySpec secretKeySpec = new SecretKeySpec(hmacKey,HMAC_SHA256_ALGORITHM);
		Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
		return mac;
				
	}
	
	
	public static byte[] deriveKey(byte[] authKeyLeftPart,byte[] authKeyRightPart)throws InvalidKeyException,NoSuchAlgorithmException{
		//Generate two HMAC-SHA 256 from left and right parts
		//note each part is 16 bytes long while a key for HMAC-SHA256 is expected to be 32 bytes 
		//the underlying implemantation to fill the missing bytes with zeros
		
		Mac mack1 = hmacSha256(authKeyLeftPart);
		Mac mack2 = hmacSha256(authKeyRightPart);
		
		//R1 =HMAC (K1,C1)-result is truncated to keep the first 16 bytes
		byte[] r1 = lpad16(mack1.doFinal(CRYPT_NONCE_1));
		
		byte[] r2 = lpad16(mack2.doFinal(CRYPT_NONCE_2));
		
		return xor(lpad16(mack1.doFinal(r2)),lpad16(mack2.doFinal(r1)));
		
	}
	
	/*
	 * compute the XOR of 2 bytes arrays.Note that the result is stored in the first array provided.
	 */
	
	private static byte[] xor(byte[] array1,byte[] array2){
		if(array1== null){
			throw new IllegalArgumentException("array1 can't be null");
		}
		if(array2 == null){
			throw new IllegalArgumentException("array2 can't be null");
			
		}
		for(int i =0;i<array1.length;i++){
			array1[i]=(byte)(array1[i]^array2[i]);
		}
		return array1;
	}
	
	/*
	 * truncate the specified array to keep only the first 16 bytes of it
	 */
	private static byte[] lpad16(byte[] in){
		return Arrays.copyOfRange(in, 0, 16);
	}
	
}
