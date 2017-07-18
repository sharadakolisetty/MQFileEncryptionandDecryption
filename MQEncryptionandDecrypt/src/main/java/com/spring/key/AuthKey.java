package com.spring.key;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import com.spring.Logging.LogCode;
import com.spring.Logging.LogUtil;

public class AuthKey {
	public static final String PRE_FIX = "key";
	private static final Logger LOG = LoggerFactory.getLogger(AuthKey.class);
	private final String authKeyName;
	private final String authSignKeyLeftPart;
	private final String authSignKeyRightPart;
	
	public AuthKey(String authKeyName,String workpath) throws GeneralSecurityException, IOException{
		this.authKeyName = authKeyName;
		
		Path secureXMlPath = Paths.get(workpath,"security","secure.yml");
		
		try{
			InputStream secureXml = Files.newInputStream(secureXMlPath);
			SecureConfig secureConfig = new Yaml().loadAs(secureXml,SecureConfig.class);
			AuthKeyStore keyStore = new AuthKeyStore(secureConfig);
			
			
			LOG.info("{} authKeyName",this.authKeyName);
			
			this.authSignKeyLeftPart = keyStore.getKeyString(this.authKeyName+"1");
			this.authSignKeyRightPart = keyStore.getKeyString(this.authKeyName+"2");
			
			
		}catch(Exception e){
			String errorMessage ="Error occured when oading secure.xml from"+secureXMlPath;
			LogUtil.logError(LOG, LogCode.LOADING_SECURE_XML_ERROR,errorMessage,e);
		throw e;
		}
		
		}
		
		
		public String getauthKeyName(){
			return this.authKeyName;
		}
		/*
		 Derives the encryption key from the specified Auth Signature key
		 *The encryption key is calulated once and kept for reuse as long as the signature key does not change
		 *
		 *@return Encryption key
		 */
		
		
		public byte[] getEncrypionKey() throws GeneralSecurityException{
			try{
				return AuthUtils.deriveKey(authSignKeyLeftPart.getBytes(StandardCharsets.US_ASCII), authSignKeyRightPart.getBytes(StandardCharsets.US_ASCII));
			}catch(Exception e){
				throw e;
			}
		}
	}
	
	


