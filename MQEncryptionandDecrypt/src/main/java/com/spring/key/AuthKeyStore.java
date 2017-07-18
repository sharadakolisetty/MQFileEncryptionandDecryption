package com.spring.key;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStore.ProtectionParameter;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import com.spring.Logging.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.MQMain.AuthConfig;

public class AuthKeyStore {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthKeyStore.class); 
	
	private static final String KEY_STORE_TYPE = "JKAJS";
	private static final String ALGORITHM = "JKAJS_128";
	
	private final SecureConfig secureConfig;
	
	public AuthKeyStore(SecureConfig secureConfig)throws IOException{
		this.secureConfig=secureConfig;
	}
	
	public String getKeyString(String alias) throws GeneralSecurityException,IOException{
		ProtectionParameter keyProtectionParam = KeyStore.PasswordProtection(secureConfig.getkeyStorepass().toCharArray());
		
		try{
			KeyStore keyStore = loadKeyStore();
			
			//note:alias are stored in lower case in java keystore
			SecretKeyEntry keyEntry = (SecretKeyEntry)keyStore.getEntry(alias.toLowerCase(), keyProtectionParam);
			
			SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
			PBEKeySpec keySpec = (PBEKeySpec) factory.getKeySpec(keyEntry.getSecretKey(), PBEKeySpec.class);
			char[] password = keySpec.getPassword();
			
			return new String(password);
		}
		catch(KeyStoreException|NoSuchAlgorithmException|UnrecoverableEntryException|InvalidKeySpecException e){
			String errorMessage = "Error occured when get key string for entry with alias name:"+alias;
			LogUtil.logError(LOGGER,LogCode.LOADING_SECURE_XML_ERROR,errorMessage,e);
			throw e;
		}
		
	}
	
	private KeyStore loadKeyStore() throws GeneralSecurityException,IOException{
		try(FileInputStream fin = new FileInputStream(secureConfig.getKeyStore()) ){
			KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE);
			keyStore.load(fin, secureConfig.getkeyStorepass().toCharArray());
			
			return keyStore;
					
			
		}catch(KeyStoreException|IOException|CertificateException|NoSuchAlgorithmException e){
			String errorMessage ="cannot find key store file at path-"+secureConfig.getKeyStore();
			LogUtil.logError(LOGGER, LogCode.KEY_STORE_FILE_NOT_FOUND,errorMessage,e);
			throw e;
		}
	
	}
	

}
