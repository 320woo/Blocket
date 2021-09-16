package com.b101.recruit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b101.recruit.domain.entity.User;
import com.b101.recruit.domain.repository.UserRepository;
import com.b101.recruit.service.IUserSerive;
import com.google.common.base.Optional;

@Service("userService")
public class UserService implements IUserSerive {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findByUserId(String userId) {
		Optional<User> user = userRepository.findByEmail(userId);
		if(user.isPresent()) return user.get();
		return null;
	}

}
