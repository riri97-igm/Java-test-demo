package team6.java.ca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	@GetMapping("/admin")
	public String showAdminLandingPage(HttpSession session) {
		if ((Boolean)session.getAttribute("admin") == true) {
			return "admin-lp";
		}
		return "redirect:/employee";
	}

}
