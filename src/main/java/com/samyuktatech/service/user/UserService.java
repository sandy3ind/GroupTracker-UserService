package com.samyuktatech.service.user;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samyuktatech.model.User;

@RestController
@RequestMapping("/")
public class UserService {

	
	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody User user, Errors errors) {
		
		if (errors.hasErrors()) {			
			// get all errors
            String msg = (errors.getAllErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(msg);
		}
		else {
			//TODO Save object
		}
		
		return ResponseEntity.ok(user);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<User> get() {
		
		User user = new User();
		user.setId(22l);
		user.setName("sandeep sharma");		
		user.setEmail("ss@gmail.com");
		user.setPassword("32323");
		
		ResponseEntity<User> response = new ResponseEntity<>(user, HttpStatus.OK);
		
		return response;
		
	}
}
