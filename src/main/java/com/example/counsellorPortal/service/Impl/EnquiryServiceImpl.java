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
		//User user = userRepo.findById(userId).orElseThrow();
		User user = new User();
		user.setUserId(userId);
		
		//Addi8ng filter values to entity 
		Enquiry Search = new Enquiry();
		Search.setUser(user);
		
		/*
		 * Here we are getting null pointer exception to avoid this we change code from
		 * !enquiry.getCourse().equals("") To this !"".equals(enquiry.getCourse())
		 */
		
		if(null!=enquiry.getCourse() && !"".equals(enquiry.getCourse())) {
			Search.setCourse(enquiry.getCourse());
		}
		if(null!=enquiry.getMode() && !"".equals(enquiry.getMode())) {
			Search.setMode(enquiry.getMode());
		}
		if(null!= enquiry.getStatus() && !"".equals(enquiry.getStatus())) {
			Search.setStatus(enquiry.getStatus());
		}
		
		//Dynamic query creation
		Example<Enquiry> of =Example.of(Search);
		return enquiryRepo.findAll(of);
	}

	@Override
	public Enquiry getEnquiry(Integer enquiryId) {
		return enquiryRepo.findById(enquiryId).orElseThrow();
	}

	@Override
	public void deleteEnquiry(Integer enquiryId) {
		enquiryRepo.deleteById(enquiryId);
		
	}

}
