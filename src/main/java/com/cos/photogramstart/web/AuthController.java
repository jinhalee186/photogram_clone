package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor  //final 이 걸려있는 모든 애들에 대한 생성자를 만듬 (DI)
@Controller //1. Ioc 등록    2 .File 리턴
public class AuthController {
	
	private final AuthService authService;
	
	//@RequiredArgConstructor 가 대신 만든 생성자 
	//public AuthController(AuthService authService){
    //   this. authService = authService;
	// }
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	//회원가입버튼
	@PostMapping("/auth/signup")
	//전처리 (입력값 Validation)를 위한 @Valid 어노테이션 - SignupDto 클래스에서 필요한 어노테이션 추가
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {//key=value(x-www-form-urlencoded)
		
		//log.info(signupDto.toString()); 오류있음
		
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
			throw new CustomValidationException("유효성 검사 실패함" , errorMap);
		}else {
			//User <-- SignupDto 넣기
			User user = signupDto.toEntity();
			
			User userEntity = authService.회원가입(user);
			System.out.println(userEntity);
			
			return "auth/signin";
		}
	}
	
	//회원 로그인컨트롤러는-> 스프링 시큐리티가 처리하기 때문에 작성 X 

}
		
