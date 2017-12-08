package com.samyuktatech.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.samyuktatech.model.User;

public class SecurityUser extends User implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Parameterized constructor to convert application user into security user
	 * 
	 * @param user
	 */
	public SecurityUser(User user) {
		this.setId(user.getId());
		this.setName(user.getName());
		this.setEmail(user.getEmail());
		this.setPhone(user.getPhone());
		this.setPassword(user.getPassword());
	}
	
	
	// Get current user.
	public static User getUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		/*if (this.getUserRoles() != null) {
			for (Role role : this.getUserRoles()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
			}
		}*/
				
		return authorities;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
