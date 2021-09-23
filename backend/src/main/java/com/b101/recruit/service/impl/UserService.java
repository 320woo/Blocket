package com.b101.recruit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.b101.recruit.domain.entity.User;
import com.b101.recruit.domain.repository.JpaUserRepository;
import com.b101.recruit.domain.repository.UserRepository;
import com.b101.recruit.request.UserRegisterPostReq;
import com.b101.recruit.service.IUserSerive;
import com.google.common.base.Optional;

@Service("userService")
public class UserService implements IUserSerive {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JpaUserRepository jpaUserRepository;

	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User findByUserId(String userId) {
		Optional<User> user = userRepository.findByEmail(userId);
		if(user.isPresent()) return user.get();
		return null;
	}
	
	@Override
	public void createUser(UserRegisterPostReq registerInfo) {
		User user = new User();
		user.setEmail(registerInfo.getEmail());
		user.setName(registerInfo.getName());
		user.setPassword(passwordEncoder.encode(registerInfo.getPassword()));
		user.setName(registerInfo.getName());
		user.setPhoneNumber(registerInfo.getPhoneNumber());
		user.setBelong(registerInfo.getBelong());
		user.setBrn(registerInfo.getBrn());
		user.setType(registerInfo.getType());
		userRepository.save(user);
	}
	
	@Override
	public long updatePassword(String userId, String newPassword) {
		long result = jpaUserRepository.updatePassword(userId, passwordEncoder.encode(newPassword));
		return result;
	}

}
