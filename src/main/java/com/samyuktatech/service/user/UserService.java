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

import com.samyuktatech.comman.util.RestUtil;
import com.samyuktatech.model.User;

@RestController
@RequestMapping("/")
public class UserService {
	
	@Value("${mysqlService.host}")
	private String mysqlServiceHost;
	
	@Value("${searchService.host}")
	private String searchServiceHost;
	
	@Autowired
	private RestTemplate restTemplate;
	

	/**
	 * Save new user
	 * 
	 * @param user
	 * @param errors
	 * @return
	 */
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
			
		    HttpEntity<User> httpEntity = RestUtil.getHttpEntityJson(user);			
		    // Save User into Mysql database
		    ResponseEntity<User> resp = restTemplate.postForEntity(mysqlServiceHost + "/user", httpEntity, User.class);		    
		    if (resp.getStatusCode() == HttpStatus.CREATED) {
		    	// Save User into Elasticsearch
		    	ResponseEntity<String> resp1 = restTemplate.postForEntity(searchServiceHost, httpEntity, String.class);
		    	if (resp1.getStatusCode() == HttpStatus.CREATED) {
		    		return ResponseEntity.ok(resp.getBody());
		    	}
		    }		    
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
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
