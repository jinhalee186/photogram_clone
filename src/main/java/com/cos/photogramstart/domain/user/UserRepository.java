package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션 없이도 IoC등록이 자동으로 됨 (JPArepository 를 상속해서)
public interface UserRepository extends JpaRepository<User, Integer>{

		//**  중요 ** JPA query creation using keyword (and, or 등 )  -> JPA 를 통해 제공됨 
		User findByUsername(String username);
}
