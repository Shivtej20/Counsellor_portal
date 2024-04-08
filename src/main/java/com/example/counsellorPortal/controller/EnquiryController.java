package com.example.counsellorPortal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.counsellorPortal.entity.Enquiry;
import com.example.counsellorPortal.service.Impl.EnquiryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	@Autowired
	private EnquiryServiceImpl enquiryServiceImpl;

	@GetMapping("/enquiry")
	public String getEnquiry(Model model) {
		model.addAttribute("enq", new Enquiry());
		return "addEnq";
	}

	@PostMapping("/enquiry")
	public String saveEnquiry(Enquiry enquiry, Model model, HttpServletRequest httpSession) {
		HttpSession session = httpSession.getSession(false);
		Integer uid = (Integer) session.getAttribute("uid");

		boolean status = enquiryServiceImpl.addEnquiry(enquiry, uid);
		if (status) {
			model.addAttribute("emsg", "Enquiry not saved");
		} else {
			model.addAttribute("smsg", "Enquiry saved succeessfully");
		}
		model.addAttribute("enq", new Enquiry());
		return "addEnq";
	}

	@GetMapping("/enquiries")
	public String getEnquiries(Model model, HttpServletRequest httpSession) {

		HttpSession session = httpSession.getSession(false);
		Integer uid = (Integer) session.getAttribute("uid");

		List<Enquiry> list = enquiryServiceImpl.getEnquiries(new Enquiry(), uid);
		model.addAttribute("enqs", list);
		
		model.addAttribute("enq", new Enquiry());
		return "viewEnq";
	}

	@PostMapping("/filter")
	public String filterEnq(@ModelAttribute("enq") Enquiry enquiry , Model model,  HttpServletRequest httpSession) {

		HttpSession session = httpSession.getSession(false);
		Integer uid = (Integer) session.getAttribute("uid");

		List<Enquiry> list = enquiryServiceImpl.getEnquiries(enquiry, uid);
		model.addAttribute("enqs", list);

		return "viewEnq";

	}

	@GetMapping("/edit")
	public String editEnquiry(Model model, @RequestParam("enquiryId") Integer enquiryId) {
		Enquiry enquiry = enquiryServiceImpl.getEnquiry(enquiryId);
		model.addAttribute("enq", enquiry);

		return "addEnq";
	}
	
	@DeleteMapping("/delete")
	public String deleteEnquiry (Model model ,@RequestParam("enquiryId") Integer enquiryId) {
		enquiryServiceImpl.deleteEnquiry(enquiryId);
		return "viewEnq";
	}

}
