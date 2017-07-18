package com.spring.outbound;

import com.spring.MQMain.AuthPayLoad;

public class AuthSignEncrypt extends AbstractOutbound{
	
	private static final java.lang.String PRE_FIX="BK_";
	
	public AuthSignEncrypt(AuthKey key,JmsTemplate queueout,JmsTemplate queueErr){
		super(key,queueOut,queueErr)
	}
	
	public void run(Message message,String txuid,String guid)throws JMSException,GeneralSecurityException{
		ByteMessage receivedMessage = (ByteMessage)message;
		
		AuthPayLoad signedPayLoad = getSignedPayLoad(receivedMessage,txuid,guid);
		
		queueOut.send(session->{
			ByteMessage result = session.createByteMessage();
			result.writeBytes(signedPayLoad.getMqMessageLoad());
			result.setStringProperty(PAG_LMAC,signedPayLoad.getLmacProperty());
			setProperties(result,receivedMessage);
			return result;
		});
		
		
	}
	
	private AuthPayLoad getSignedPayLoad(BytesMessage receivedMessage,String txuid,String guid)throws JMSException,GeneralSecurityException{
		int length =(int) receivedMessage.getBodyLength();
		Byte[] MessageInBytes = new Byte[length];
		receivedMessage.readBytes(messagesBytes,length);
		
		byte[] encryptedPayLoad= encrypt(messageInBytes);
		
	}

}
