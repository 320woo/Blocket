package com.b101.recruit.service;

import com.b101.recruit.domain.entity.User;
import com.b101.recruit.request.UserRegisterPostReq;

public interface IUserSerive {
	
	User findByUserId(String userId);
	void createUser(UserRegisterPostReq registerInfo);
	long updatePassword(String userId, String newPassword);
}
