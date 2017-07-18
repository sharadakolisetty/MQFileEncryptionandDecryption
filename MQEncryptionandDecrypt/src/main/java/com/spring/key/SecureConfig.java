package com.spring.key;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//suppress reason:PMD plains that workpath can be made final,but compiler complains may not initalized
@SuppressWarnings("PMD")
public class SecureConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecureConfig.class);
	
	private String keyStorepass;
	private String keyStore;
	
	public String getKeyStore() {
		return keyStore;
	}

	public void setKeyStore(String keyStore) {
		this.keyStore = keyStore;
	}

	public String getkeyStorepass(){
		return keyStorepass;
	
	}
	public void setkeyStorepass(String keyStorepass){
		 this.keyStorepass=keyStorepass;
	}
}
