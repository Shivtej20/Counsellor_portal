package com.example.counsellorPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.counsellorPortal.entity.Enquiry;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {
//main
	@Query(value =// "select count (*) from user where userId =:userId"//
			"SELECT COUNT(*) FROM enquiry WHERE user_id = :userId", nativeQuery = true)
	public Long getEnquiries(Integer userId);
//user to enquiry
	@Query(value = //"select count (*) from user where userId =:userId and status =:status"//
			"SELECT COUNT(*) FROM Enquiry  WHERE user_Id = :userId AND status = :status", nativeQuery = true)
	public Long getEnquiriesBystatus(Integer userId, String status);
}
