package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//JPA - Java Persistence Application
@Builder
@AllArgsConstructor 
@NoArgsConstructor 
@Data // getter,setter by lombok
@Entity // db 에 테이블을 생성
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)// 번호증가 전략이 데이터베이스를 따라감  
	private Integer id;  // 큰 서비스가 아닐 경우 int 로 만들어도 무방
	
	@Column(length = 20, unique=true)
	private String username;
	@Column(nullable= false)
	private String password;
	@Column(nullable= false)
	private String name;
	private String website;
	private String bio;
	@Column(nullable= false)
	private String email;
	
	@Column(unique=true) // 향후 nullable=false 추가 ( 가입 시 입력) 
	private String phone;
	private String gender;
	
	private String profileImageUrl;
	private String role;
	
	private LocalDateTime createDate;
	
	@PrePersist // db 에 insert 되기 직전에 실행
	private void createDate() {
		this.createDate= LocalDateTime.now();
	}
	
}
