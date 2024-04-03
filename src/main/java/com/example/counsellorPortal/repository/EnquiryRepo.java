package com.example.counsellorPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.counsellorPortal.entity.Enquiry;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {

	@Query(value = "select count (*) from user where userId =:userId", nativeQuery = true)
	public Long getEnquiries(Integer userId);

	@Query(value = "select count (*) from user where userId =:userId and status =:status", nativeQuery = true)
	public Long getEnquiriesBystatus(Integer userId, String status);
}
