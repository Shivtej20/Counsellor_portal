package com.example.counsellorPortal.service;

import java.util.List;

import com.example.counsellorPortal.DTO.Dashboard;
import com.example.counsellorPortal.entity.Enquiry;

public interface EnquiryService {
	
	public Dashboard getDashboardInfo(Integer userId); 
	public boolean addEnquiry (Enquiry enquiry,Integer userId);
	public List<Enquiry> getEnquiries(Enquiry enquiry,Integer userId);
	public Enquiry getEnquiry(Integer enquiryId);
	public void deleteEnquiry(Integer enquiryId);

}
