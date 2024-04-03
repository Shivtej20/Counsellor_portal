package com.example.counsellorPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.counsellorPortal.DTO.Dashboard;
import com.example.counsellorPortal.entity.User;
import com.example.counsellorPortal.service.Impl.EnquiryServiceImpl;
import com.example.counsellorPortal.service.Impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class userController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private EnquiryServiceImpl enquiryServiceImpl;

	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}

	@PostMapping("/login")
	public String handlelogin(User user, HttpServletRequest httpServletRequest, Model model) {
		User u = userServiceImpl.getUser(user.getEmail(), user.getPwd());
		if (u == null) {
			model.addAttribute("emsg", "Invalid credantials");
			return "index";
		} else {
			// session - To save the user ID
			HttpSession session = httpServletRequest.getSession(true);// always provide new session
			session.setAttribute("uid", u.getUserId());

			Dashboard info = enquiryServiceImpl.getDashboardInfo(u.getUserId());
			model.addAttribute("dashbord", info);
			return "dashboard";
		}
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "registerview";
	}

	@PostMapping("/register")
	public String handleRegister(User user, Model model) {
		boolean saveCounsellor = userServiceImpl.saveCounsellor(user);
		if (saveCounsellor) {
			model.addAttribute("smsg", "Consellor registered successfully");
		} else {
			model.addAttribute("emsg", "Failed to save");
		}
		return "registerview";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		session.invalidate();

		return "redirect:/";
	}

}
