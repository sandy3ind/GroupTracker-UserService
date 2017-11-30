package com.samyuktatech.service.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samyuktatech.model.User;

@RestController
@RequestMapping("/user")
public class UserService {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<User> get() {
		
		User user = new User();
		user.setId(22);
		user.setFirstName("sandeep");
		user.setLastName("sharma");
		user.setEmail("ss@gmail.com");
		user.setPassword("32323");
		
		ResponseEntity<User> response = new ResponseEntity<>(user, HttpStatus.OK);
		
		return response;
		
	}
}
