package com.example.counsellorPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.counsellorPortal.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByEmail(String email);

	public User findByEmailAndPwd(String email, String password);

}
