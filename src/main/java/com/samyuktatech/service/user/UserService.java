package com.samyuktatech.service.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.stream.Collectors;

import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.samyuktatech.model.User;

@RestController
@RequestMapping("/")
public class UserService {
	
	@Value("${mysqlService.host}")
	private String mysqlServiceHost;
	
	@Autowired
	private RestTemplate restTemplate;

	
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> save(@RequestBody User user) {
		
		HttpHeaders requestHeaders = new HttpHeaders();
	    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<User> httpEntity = new HttpEntity<>(user, requestHeaders);
		
		User respUser = restTemplate.postForObject(mysqlServiceHost, httpEntity, User.class);
				
		ResponseEntity<User> response = new ResponseEntity<>(respUser, HttpStatus.OK);
		
		return response;
		
	}
}
