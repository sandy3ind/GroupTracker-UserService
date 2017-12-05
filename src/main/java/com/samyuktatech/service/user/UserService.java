package com.samyuktatech.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.samyuktatech.model.User;

@RestController
@RequestMapping("/user")
public class UserService {
	
	@Value("${mysqlService.host}")
	private String mysqlServiceHost;
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<User> get() {
		
		User user = new User();
		
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
