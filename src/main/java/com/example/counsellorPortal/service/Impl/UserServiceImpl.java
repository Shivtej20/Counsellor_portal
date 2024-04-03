package com.example.counsellorPortal.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.counsellorPortal.entity.User;
import com.example.counsellorPortal.repository.UserRepo;
import com.example.counsellorPortal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public boolean saveCounsellor(User user) {
		User email = userRepo.findByEmail(user.getEmail());
		if (email != null) {
			return false;
		}
		User saved = userRepo.save(user);
		return saved.getUserId() != null;
	}

	@Override
	public User getUser(String email, String password) {
		return userRepo.findByEmailAndPwd(email, password);

	}

}
