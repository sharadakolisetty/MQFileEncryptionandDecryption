package com.spring.outbound;

import com.spring.MQMain.AuthEncription;
import com.spring.key.AuthKey;

public abstract class AbstractOutbound implements  AuthEncription{
	
	protected final AuthKey authkey;
	
	protected final JmsTemplate queueOut;
	protected final JmsTemplate queuerr;
	
	
	public AbstractOutbound(AuthKey key,JmsTemplate queueOut,JmsTemplate queueErr){
		this.authkey=authKey;
		this.queueOut=queueOut;
		this.queuerr=queuerr;
		
	}
	
	//@overide
	public String getGuid(Message message)throws JmsException{
		return message.getStringProperty(K_GUID);
	}
	public String getGuid(Message message)throws JmsException{
		return message.getStringProperty(TX_UID);
	}

}
