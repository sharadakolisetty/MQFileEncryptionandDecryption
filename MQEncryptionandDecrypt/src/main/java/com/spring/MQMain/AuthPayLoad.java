package com.spring.MQMain;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AuthPayLoad {
	private final byte[] mqMessageload;
	private final String lmacProperty;
	
	public AuthPayLoad(byte[] mqMessageload,String lmacProperty){
		this.mqMessageload= mqMessageload;
		this.lmacProperty = lmacProperty;
	}
	public AuthPayLoad(byte[] pLoad){
		this.lmacProperty = new String(Arrays.copyOfRange(pLoad, 0,25),StandardCharsets.UTF_8);
		this.mqMessageload = Arrays.copyOfRange(pLoad, 25,pLoad.length);
	}
	
	public AuthPayLoad(ByteMessage msg,String signature) throws JMSException{
		int length =(int)msg.getBodyLength();
		byte[] b = new byte[length];
		msg.readBytes(b,length);
		this.mqMessageload =b;
		this.lmacProperty = signature;
		
	}
	public byte[] getMqMessageLoad(){
		return mqMessageload;
	}
	public String getLmacProperty(){
		return lmacProperty;
	}

}
