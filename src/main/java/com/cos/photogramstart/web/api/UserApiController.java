package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService;
	
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(
			@PathVariable int id, 
			@Valid UserUpdateDto userUpdateDto, 
			BindingResult bindingResult, // 반드시 @Valid 바로 다음 파라미터에 적어야 함
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("=========================");
				System.out.println(error.getDefaultMessage());
				System.out.println("=========================");
			}//for룹
			//return "오류처리 필요";
			//throw new RuntimeException("유효성 검사 실패함");--> RuntimeException 은 문자열만 받으므로 새로운 Exception 생성
			throw new CustomValidationApiException("유효성 검사 실패함" , errorMap);
		}else {
			//User <-- UserUpdateDto 넣기	 	
			User updatedUserEntity = userService.회원수정(id, userUpdateDto.toEntity());
			principalDetails.setUser(updatedUserEntity); //세션 정보 변경
			//System.out.println(updatedUserEntity);
			
			return  new CMRespDto<>(1, "회원수정완료", updatedUserEntity);
		}
		
	}
}
