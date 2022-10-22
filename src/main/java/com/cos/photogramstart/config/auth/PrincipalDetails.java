package com.cos.photogramstart.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data  //getter setter for user
public class PrincipalDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
		
// Collection 으로 리턴 받는 이유 - 권한: 한개 아닐 수 있음 (3개 이상의 권한)-> 이코드로 구현 x 
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// user 권한 주는 함수 
		
		Collection<GrantedAuthority> collector = new ArrayList<>();
		//자바는 함수를 파라미터로 사용할 수 없어서 람다식 사용 (인터페이스, 오브젝트 등등으로 넘겨야 함)  
		collector.add(()->{ return user.getRole();	    });
		return collector;
	} 
	
	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정만료여부
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
