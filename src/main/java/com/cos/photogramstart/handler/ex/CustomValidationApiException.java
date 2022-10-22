package com.cos.photogramstart.handler.ex;

import java.util.Map;

//RuntimeException 을 상속하는 유효성검사 예외를 만듬
public class CustomValidationApiException extends RuntimeException{
	
	//serialVersionID : 객체 구분할때 사용  - JVM 관련
	private static final long serialVersionID=1L;  
	
	//private String message;
	private Map<String,String> errorMap;
	
	public CustomValidationApiException(String message, Map<String,String> errorMap) {
		super(message);
		//this.message = message;
		this.errorMap = errorMap;
	}

	public Map<String, String> getErrorMap() {
		return errorMap;
	}


}
