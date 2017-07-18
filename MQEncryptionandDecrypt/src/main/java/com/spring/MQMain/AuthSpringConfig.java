package com.spring.MQMain;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.spring.Logging.LogCode;
import  com.spring.key.*;

import com.spring.outbound.AuthSignEncrypt;;

@SpringBootApplication
@EnableJms
public class AuthSpringConfig {
	private static final Logger LOG = LoggerFactory.getLogger(AuthConfig.class);
	
	@Bean
	public AuthEncription AuthEncription(@Value"${mode}" String mode,AuthKey authKey,
										@Qualifier("Queue_out")JmsTemplate queueout,
										@Qualifier("queue_err")JmsTemplate queueErr){
		
		
		AuthEncription authFunction = null;
		switch(AuthMode.valueOf(mode)){
		case sign:
			AuthFunction = new AuthSign(authKey,queueOut,queueErr);
			break;
			
		case signedencrypt:
			AuthFunction = new AuthSignEncrypt(authKey,queueOut,queueErr);
			break;
			
		case verify:
				AuthFunction = new AuthVerify(authKey,queueOut,queueErr);
				break;
				
				
		case passthruIN:
				AuthFunction = new AuthPassThroughIN(authKey,queueOut,queueErr);
				break;
			default:
				//won't even reach because authmode.valueOf throw IllegalArgumentException
			
		}
		LOG.debug("Auth function has beed created ",authFunction.getClass().getSimpleName());
		StringBuffer message = new StringBuffer(74);
		message.append("AuthApplicationMde:").append(mode);
		LOG.info("{} start of application",LogCode.START_OF_APPLICATION.getCode());
	
	
	}

	


