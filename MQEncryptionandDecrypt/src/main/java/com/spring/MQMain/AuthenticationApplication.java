package com.spring.MQMain;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;

public final class AuthenticationApplication {
	
	private AuthenticationApplication(){
		
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if(args.length!=2){
			System.console().printf("Usage:<file.yml> log_suffix");
			return;
		}
		System.setProperty("suffix",String.valueOf(args[1]));
		String configFile = "--Spring.config.location=file:"+args[0];
		SpringApplication app = new SpringApplication(AuthSpringConfig.class);
		
		app.setBannerMode(Banner.Mode.OFF);
		app.run(configFile);
		

	}

}
