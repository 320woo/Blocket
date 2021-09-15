package com.b101.recruit.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.b101.recruit.domain.entity.User;
import com.b101.recruit.domain.entity.UserRecruit;


public class CustomUserDetails implements UserDetails{

	@Autowired
	User user;
	boolean accountNonExpired;
	boolean accountNonLocked;
	boolean credentialNonExpired;
	boolean enabled = false;
	List<GrantedAuthority> roles = new ArrayList<>();
	

	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}

	public String getName() {
		return this.user.getName();
	}

	public String getUserEmail() {
		return this.user.getEmail();
	}
	
	public String getUserBelong() {
		return this.user.getBelong();
	}
	
	public int getUserBrn() {
		return this.user.getBrn();
	}
	public String getUserPhoneNumber() {
		return this.user.getPhoneNumber();
	}
	public int getUserType() {
		return this.user.getType();
	}
	
	public List<UserRecruit> getUserJobPosting() {
		return this.user.getJobPosting();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
