package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// 자동으로 빈으로 등록이 된다.
// @Repository 자동등록으로 인하여 생략가
public interface UserRepository extends JpaRepository<User, Integer> {

}

// 로그인을 위한 함수 JPA NAMEING 전략
//User findByUsernameAndPassword(String username, String password);

// @Query(value = "select * from user where username = ?1 and password = ?2", nativeQuery = true)
// User login(String username, String password);