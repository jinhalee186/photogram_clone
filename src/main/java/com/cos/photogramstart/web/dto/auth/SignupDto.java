package com.cos.photogramstart.web.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data //Getter, Setter generate
public class SignupDto {
	
	//validation 을 위해 추가 -@Valid 어노테이션종류 - https://wildeveloperetrain.tistory.com/25
	@Size(min=3, max=20)
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	
	//signupDto 의 값을 User 에 넣어 반환 (builder 사용)
	public User toEntity() {
		
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
