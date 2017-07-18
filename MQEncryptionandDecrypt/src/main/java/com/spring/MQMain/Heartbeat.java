package com.spring.MQMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Heartbeat {
	private static final Logger LOG = LoggerFactory.getLogger(Heartbeat.class);
	
	private final String qManager;
		private final String inputQueue;
	private final String outputQueue;
	
	public Heartbeat(@Value"${queues.manager}") String qManager,@Value("${queues.input}") String input,
					@Value("${queues.output}" String output){
		this.qManager = qManager;
		this.inputQueue=input;
		this.outputQueue=output;
		
		
	}
	@Scheduled(fixedDelayString ="${heartbeat.period}000")
	public void writeHeartBeat(){
		LOG.info("HeartBeat info == QueueManager-{},Input Queue - {}",qManager,input,output);
		
	}
	

}
