package com.example.counsellorPortal.service;

import com.example.counsellorPortal.entity.User;

public interface UserService {
	
	//Registration
	public boolean saveCounsellor(User user);
	//Login
	public User getUser(String email, String password);
	

}
