package com.cos.blog.service;

import com.cos.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional // 데이터에 유효성
	public void 회원가입(User user) {
		userRepository.save(user);
	}

//	@Transactional(readOnly = true)// select 트랜젝션 시작
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}


}