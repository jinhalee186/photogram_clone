package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당 파일로 시큐리티를 활성화
@Configuration //Ioc 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super 삭제 - 기존 시큐리티 기능 비활성화 
		http.csrf().disable();
		http.authorizeRequests()
		    //아래 정의된 주소 접속시 인증 필요
			.antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**").authenticated()
			.anyRequest().permitAll() // 그외 전부 허용
			.and()
			.formLogin() //
			.loginPage("/auth/signin") // 폼 로그인 페이지는 요거임- GET 요청일때
			.loginProcessingUrl("/auth/signin") // POST 요청일때
			.defaultSuccessUrl("/"); // 정상처리 후 / 로 이동
			
		    
	}
}
