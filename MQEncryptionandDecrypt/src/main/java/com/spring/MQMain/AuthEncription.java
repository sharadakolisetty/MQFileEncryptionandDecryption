package com.spring.MQMain;
import javax.jms.Message;
import javax.jms.JMSException;

public interface AuthEncription {
	
	String  TXUID="TX_UID";
	String K_GUID ="K_GUID";
	String PAG_LMAC = "PAG_LMAC";
	
	void run(Message message,String txuid,String guid) throws Exception;
	
	String getGuid(Message message) throws JMSException;
	
	String getTxuid(Message message) throws JMSException;;
	
	
	

}
