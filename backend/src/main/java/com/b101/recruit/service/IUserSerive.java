package com.b101.recruit.service;

import com.b101.recruit.domain.entity.User;

public interface IUserSerive {
	
	User findByUserId(String userId);
}
