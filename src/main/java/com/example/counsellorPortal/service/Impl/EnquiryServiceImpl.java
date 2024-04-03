package com.example.counsellorPortal.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.counsellorPortal.DTO.Dashboard;
import com.example.counsellorPortal.entity.Enquiry;
import com.example.counsellorPortal.entity.User;
import com.example.counsellorPortal.repository.EnquiryRepo;
import com.example.counsellorPortal.repository.UserRepo;
import com.example.counsellorPortal.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService{
	 
	@Autowired
	private EnquiryRepo enquiryRepo;
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public Dashboard getDashboardInfo(Integer userId) {
		Long totalEnquiries = enquiryRepo.getEnquiries(userId);
		Long openEnquiries = enquiryRepo.getEnquiriesBystatus(userId, "Open");
		Long lostEnquiries = enquiryRepo.getEnquiriesBystatus(userId, "Lost");
		Long enrolledEnquiries = enquiryRepo.getEnquiriesBystatus(userId, "Enrolled");
		
		Dashboard d=new Dashboard();
		d.setTotalEnquiries(totalEnquiries);
		d.setOpenEnquiries(openEnquiries);
		d.setLostEnquiries(lostEnquiries);
		d.setEnrolledEnquiries(enrolledEnquiries);
		return d;
	}

	@Override
	public boolean addEnquiry(Enquiry enquiry,Integer userId) {
		//Association mapping for foreign key
		User user = userRepo.findById(userId).orElseThrow();
		enquiry.setUser(user);
		enquiryRepo.save(enquiry);
		return false;
	}

	@Override
	public List<Enquiry> getEnquiries(Enquiry enquiry, Integer userId) {
		// Association mapping for foreign key
		User user = userRepo.findById(userId).orElseThrow();
		enquiry.setUser(user);
		
		Example<Enquiry> of =Example.of(enquiry);
		return enquiryRepo.findAll(of);
	}

	@Override
	public Enquiry getEnquiry(Integer enquiryId) {
		return enquiryRepo.findById(enquiryId).orElseThrow();
	}

}
