package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import org.springframework.data.domain.Sort;


@RestController
public class DummyControllerTest {

	@Autowired // 의존성주입(di)
	private UserRepository userRepository;
	
//	@PostMapping("/dummy/join")
//	public String join(String username, String password, String email) {
//		System.out.println("username : " + username);
//		System.out.println("password : " + password);
//		System.out.println("email : " + email);
//		return "success join!!!";
//	}

	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "fail... not found user id :" + id ;
		}
		return "success delete id : " + id ;
	}
	
	
//	@PutMapping("/dummy/user/{id}")
//	public User updateUser(@PathVariable int id, @RequestBody User RequestUser) {
//		System.out.println("id :" + id);
//		System.out.println("password :" + RequestUser.getPassword());
//		System.out.println("password :" + RequestUser.getEmail());
//		
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("update faill");
//		});
//		
//		user.setPassword(RequestUser.getPassword());
//		user.setEmail(RequestUser.getEmail());
//		userRepository.save(user);
//		
//		return null;
//	}

	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User RequestUser) {
		System.out.println("id :" + id);
		System.out.println("password :" + RequestUser.getPassword());
		System.out.println("password :" + RequestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("update faill");
		});
		
		user.setPassword(RequestUser.getPassword());
		user.setEmail(RequestUser.getEmail());
	
		// userRepository.save(user);
		// 더티 체킹
		return user;
	}	
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}

	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("not found user. id : " + id);
			}
		});
		return user;
	}
	
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("id : " + user.getId());
		System.out.println("role : " + user.getRole());

		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "success join!!!";
	} 
	
	
	
}
