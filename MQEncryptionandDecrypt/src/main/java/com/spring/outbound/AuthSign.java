package com.spring.outbound;

import com.spring.key.AuthKey;

public class AuthSign extends AuthSignEncrypt{
	
	public AuthSign(AuthKey key,JmsTemplate queueOut,JmsTemplate queueErr){
		super(key,queueOut,queueErr)
	}
	
	

}
