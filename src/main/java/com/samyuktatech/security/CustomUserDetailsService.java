package com.samyuktatech.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.samyuktatech.comman.util.RestUtil;
import com.samyuktatech.model.User;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Value("${mysqlService.host}")
	private String mysqlServiceHost;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {			    
		
	    ResponseEntity<User> resp = restTemplate.getForEntity(mysqlServiceHost + "/user?email=" + username, 
	    		User.class);
	    
	    if (resp.getStatusCode() != HttpStatus.OK) {
	    	throw new UsernameNotFoundException("Username not found");
	    }
		User user = resp.getBody();
		return new SecurityUser(user);
	}

}
