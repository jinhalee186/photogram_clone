package com.cos.photogramstart.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {
	@NotBlank
	private String name;  //필수
	@NotBlank
	private String password; //필수
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	//UserUpdateDto 의 값을 User 에 넣어 반환 (builder 사용)
	public User toEntity() {
		
		//필수 필드가 아닌 항목을 entity 에 넣는것은 비추천 
		return User.builder()
				.name(name)  //필수 항목 미기재에 대한 Validation 체크 필요
				.password(password) 
				.website(website)
				.bio(bio)
				.phone(phone)
				.build();
	}
}
