package com.cos.photogramstart.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController  //data 를 리턴하게 하기위해 
@ControllerAdvice //
public class ControllerExceptionHandler {

	/*
	 =====CMRespDto, Script 두개 예외 처리 비교 =====
		1. 클라이언트에게 응답할때는 Script 가 좋음
		2. Ajax 통신 - CMRespDto
		3. Android 통신 - CMRespDto
	 */
	
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
	//방법 1:  public CMRespDto<Map<String, String>> validationException(CustomValidationException e) {
		//return new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap());
		return Script.back(e.getErrorMap().toString());
	}
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
	
		 //데이터 응답
		return new ResponseEntity<> (new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()) , HttpStatus.BAD_REQUEST);
		
	}


}
