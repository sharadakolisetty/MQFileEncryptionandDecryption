package com.spring.MQMain;

public enum AuthMode {
	sign("sign"),
	signencrypt("signencrypt"),
	verify("verify"),
	verifydecrypt("verifydecript"),
	passthruIN("passthruIN"),
	passthruOUT("passthruOUT");
	
	private String mode;
	
	AuthMode(String mode){
		this.mode= mode;
	}
	
	public String getModeString(){
		return this.mode;
	}
	

}
