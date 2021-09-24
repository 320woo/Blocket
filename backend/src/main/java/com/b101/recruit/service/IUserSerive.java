package com.b101.recruit.service;

import com.b101.recruit.domain.entity.User;
import com.b101.recruit.request.UserRegisterPostReq;
import com.b101.recruit.request.UserUpdatePatchReq;

public interface IUserSerive {
	
	User findByUserEmail(String userEmail);
	void createUser(UserRegisterPostReq registerInfo);
	long updatePassword(String userEmail, String newPassword);
	long deleteUser(String userEmail);
	long updateUser(String userEmail, UserUpdatePatchReq UserUpdatePatchReq);
	boolean confirmUserEmail(String userEmail);
}
