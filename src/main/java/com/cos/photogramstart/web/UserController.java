package com.cos.photogramstart.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;

@Controller
public class UserController {
	
	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id) {
		return "/user/profile";
	}
	
	@GetMapping("/image/upload")
	public String upload() {
		return "/image/upload";
	}
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		//세션사용자정보 접근 1. @AuthenticationPrincipal 을 사용한 간소화 방법
		System.out.println("세션정보: " + principalDetails.getUser());
		
		//세션사용자정보 접근 2. 직접 찾는 방법 - 비효율적임
		Authentication auth  = SecurityContextHolder.getContext().getAuthentication();
		PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
		System.out.println("직접찾은세션정보:" +mPrincipalDetails.getUser());
		
		
		//모델에 세션(사용자 principalDetails) 정보를 담아서 jsp 로 전달 
		//--> 시큐리티 태그 라이브러리 사용해 생략 <--
		//model.addAttribute("principal",mPrincipalDetails.getUser());
		return "/user/update";
	}
}
