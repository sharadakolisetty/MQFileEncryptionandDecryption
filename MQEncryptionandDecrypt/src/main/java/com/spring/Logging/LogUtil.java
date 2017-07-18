package com.spring.Logging;

import org.slf4j.Logger;

public final class LogUtil {
	private LogUtil(){
		
	}

	public static void logInfo(Logger logger ,LogCode logCode,String guid,String txuid){
		logger.info("{}{},GUID:{},TXUID:{}",
				logCode.getCode(),logCode.getDescription(),guid,txuid);
	}
	public static void logInfo(Logger logger ,LogCode logCode,String guid,String txuid,String extraInfo){
		logger.info("{}{},GUID:{},TXUID:{}",
				logCode.getCode(),logCode.getDescription(),guid,txuid,extraInfo);
	}
	public static void logError(Logger logger ,LogCode logCode,String guid,String txuid){
		logger.info("{}{},GUID:{},TXUID:{}",
				logCode.getCode(),logCode.getDescription(),guid,txuid);
	}
	public static void logError(Logger logger ,LogCode logCode,String guid,String txuid,String errorMessage){
		logger.info("{}{},GUID:{},TXUID:{}",
				logCode.getCode(),logCode.getDescription(),guid,txuid,errorMessage);
	}
	public static void logError(Logger logger ,LogCode logCode,String guid,String txuid,Throwable cause){
		logger.info("{}{},GUID:{},TXUID:{}",
				logCode.getCode(),logCode.getDescription(),guid,txuid,cause);
	}
	
	public static void logError(Logger logger ,LogCode logCode){
		logger.info("{} {}",
				logCode.getCode(),logCode.getDescription());
	}
	public static void logError(Logger logger ,LogCode logCode,Throwable cause){
		logger.info("{} {}-{}",
				logCode.getCode(),logCode.getDescription(),cause);
	}
	public static void logError(Logger logger ,LogCode logCode,String extraInfo,Throwable cause){
		logger.info("{} {}-{}",
				logCode.getCode(),logCode.getDescription(),extraInfo,cause);
	}
	
	
	
}
