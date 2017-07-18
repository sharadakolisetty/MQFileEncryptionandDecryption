package com.spring.Logging;

public enum LogCode {
	START_OF_APPLICATION(1000,"start of application"),
	END_OF_APPLICATION(1004,"end of application"),
	
	APPLICATION_CONFIG_INFO(8000,"Application config info"),
	MESSAGE_RECEIVED(8001,"message received"),
	//processing info codes
	MESSAGE_ENCRYPTED(8002,"Message encrypted"),
	MESSAGE_SIGNED(8003,"Message signed"),
	MESSAGE_DECRYPTED(8005,"Message decrypted"),
	MESSAGE_VERIFIED(8004,"Message verified"),
	
	//from 8021 are erros
	LOADING_SECURE_XML_ERROR(8024,"error occred loading secure xml"),
	KEY_STORE_FILE_NOT_FOUND(8025,"keystore file not found");
	
	
	private final int code;
	private String description;
	
	LogCode(int code,String description){
		this.code= code;
		this.description=description;
		
	}
	
	public int getCode(){
		return this.code;
	}
	
	public String getDescription(){
		return this.description;
	}
	

}
